package com.example.homework_9;

import com.example.homework_9.entity.AppModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static com.example.homework_9.MyListener.emf;

public class ModuleRepo {

    public static List<AppModule> getAllModules() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<AppModule> selectCFromCourseC = entityManager.createQuery("select m from AppModule m", AppModule.class);
            return selectCFromCourseC.getResultList();
        }

    }
}
