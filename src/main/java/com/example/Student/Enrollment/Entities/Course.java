package com.example.Student.Enrollment.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, message = "Title should be at least 3 characters long")
    @Column(name = "course_title", nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, message = "Description should be at least 10 characters long")
    @Column(name = "course_description", nullable = false)
    private String description;

    @NotBlank(message = "Category cannot be empty")
    @Size(min = 3, message = "Category should be at least 3 characters long")
    @Column(name = "course_category", nullable = false)
    private String category;

    @Min(1)
    @Column(name = "course_capacity", nullable = false)
    private int capacity;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Course(boolean active, int capacity, String category, String description, String title) {
        this.active = active;
        this.capacity = capacity;
        this.category = category;
        this.description = description;
        this.title = title;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
