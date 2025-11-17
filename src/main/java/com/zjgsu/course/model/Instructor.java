package com.zjgsu.course.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 教师实体类
 */
public class Instructor {
    @NotBlank(message = "Instructor ID is required")
    private String id;

    @NotBlank(message = "Instructor name is required")
    private String name;

    @NotBlank(message = "Instructor email is required")
    @Email(message = "Instructor email should be valid")
    private String email;

    public Instructor() {
    }

    public Instructor(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
