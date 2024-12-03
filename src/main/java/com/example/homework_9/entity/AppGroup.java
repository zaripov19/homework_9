package com.example.homework_9.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private AppModule appModule;

    public AppGroup(String name, String s, AppModule appModule) {
        this.name = name;
        this.appModule = appModule;
    }
}
