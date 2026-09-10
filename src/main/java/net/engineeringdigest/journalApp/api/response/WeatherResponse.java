package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public static class Current {
        private Integer temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private Integer feelslike;
    }
}

