package com.ainewsaggregator.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    public String getUsername() {
        return username; // or you can transform/validate before returning
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
