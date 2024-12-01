package com.example.homework_9;

import com.example.homework_9.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static com.example.homework_9.MyListener.emf;

public class CourseRepo {

    public static List<Course> getAllCourses() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<Course> selectCFromCourseC = entityManager.createQuery("select c from Course c", Course.class);
            return selectCFromCourseC.getResultList();
        }
    }
}
