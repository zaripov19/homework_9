package com.example.homework_9;

import com.example.homework_9.entity.Course;
import com.example.homework_9.entity.Module;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static com.example.homework_9.MyListener.emf;

public class ModuleRepo {

    public static List<Module> getAllModules() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Module> selectCFromCourseC = entityManager.createQuery("select m from Module m", Module.class);
            return selectCFromCourseC.getResultList();
        }

    }
}
