package net.engineeringdigest.journalApp.dto;

import net.engineeringdigest.journalApp.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for User API responses - excludes sensitive password field.
 */
public class UserResponse {

    private String id;
    private String username;

    public UserResponse() {
    }

    public UserResponse(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponse from(User user) {
        if (user == null) {
            return null;
        }
        String idStr = user.getId() != null ? user.getId().toHexString() : null;
        return new UserResponse(idStr, user.getUserName());
    }

    public static List<UserResponse> fromList(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }
        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
