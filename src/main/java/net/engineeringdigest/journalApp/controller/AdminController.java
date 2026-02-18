package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        // #region agent log
        try {
            String logPath = "d:\\SelfBuilding\\Software Development\\JavaSpring\\journalApp\\.cursor\\debug.log";
            String logEntry = String.format("{\"id\":\"log_%d\",\"timestamp\":%d,\"location\":\"AdminController.java:22\",\"message\":\"getAllUsers endpoint called\",\"data\":{},\"runId\":\"run1\",\"hypothesisId\":\"A\"}\n", System.currentTimeMillis(), System.currentTimeMillis());
            Files.write(Paths.get(logPath), logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {}
        // #endregion
        List<User> all = userService.getAll();
        // #region agent log
        try {
            String logPath = "d:\\SelfBuilding\\Software Development\\JavaSpring\\journalApp\\.cursor\\debug.log";
            String logEntry = String.format("{\"id\":\"log_%d\",\"timestamp\":%d,\"location\":\"AdminController.java:30\",\"message\":\"Users retrieved from service\",\"data\":{\"userListIsNull\":%s,\"userListSize\":%d},\"runId\":\"run1\",\"hypothesisId\":\"B\"}\n", System.currentTimeMillis(), System.currentTimeMillis(), (all == null ? "true" : "false"), (all == null ? 0 : all.size()));
            Files.write(Paths.get(logPath), logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {}
        // #endregion
        if(all != null && !all.isEmpty()){
            // #region agent log
            try {
                String logPath = "d:\\SelfBuilding\\Software Development\\JavaSpring\\journalApp\\.cursor\\debug.log";
                String logEntry = String.format("{\"id\":\"log_%d\",\"timestamp\":%d,\"location\":\"AdminController.java:43\",\"message\":\"Returning OK - list has users\",\"data\":{\"userCount\":%d},\"runId\":\"post-fix\",\"hypothesisId\":\"C\"}\n", System.currentTimeMillis(), System.currentTimeMillis(), all.size());
                Files.write(Paths.get(logPath), logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (Exception e) {}
            // #endregion
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        // #region agent log
        try {
            String logPath = "d:\\SelfBuilding\\Software Development\\JavaSpring\\journalApp\\.cursor\\debug.log";
            String logEntry = String.format("{\"id\":\"log_%d\",\"timestamp\":%d,\"location\":\"AdminController.java:51\",\"message\":\"Returning NOT_FOUND - no users\",\"data\":{\"reason\":\"listIsNullOrEmpty\"},\"runId\":\"post-fix\",\"hypothesisId\":\"D\"}\n", System.currentTimeMillis(), System.currentTimeMillis());
            Files.write(Paths.get(logPath), logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {}
        // #endregion
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
