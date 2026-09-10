package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;
    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String apiTemplate = appCache.appCache != null ? appCache.appCache.get(AppCache.keys.WEATHER_API.toString()) : null;
            if (apiTemplate == null) {
                log.warn("WEATHER_API config is missing from AppCache for city {}", city);
                return null;
            }
            String safeApiKey = apiKey == null ? "" : apiKey;
            String finalAPI = Objects.requireNonNull(apiTemplate.replace(Placeholders.CITY, city)
                    .replace(Placeholders.API_KEY, safeApiKey));
            try {
                ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
                WeatherResponse body = response.getBody();
                if (body != null && body.getCurrent() != null) {
                    redisService.set("weather_of_" + city, body, 300l);
                } else {
                    log.warn("Weather API returned an empty payload for city {}", city);
                }
                return body;
            } catch (RestClientResponseException ex) {
                log.error("Weather API failed for city {} with status {} and response {}", city,
                        ex.getRawStatusCode(), ex.getResponseBodyAsString());
                return null;
            } catch (Exception ex) {
                log.error("Weather API call failed for city {}", city, ex);
                return null;
            }
        }

    }
}

