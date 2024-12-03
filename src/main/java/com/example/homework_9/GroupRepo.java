package com.example.homework_9;

import com.example.homework_9.entity.AppGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static com.example.homework_9.MyListener.emf;

public class GroupRepo {

    public static List<AppGroup> getAllGroups() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            TypedQuery<AppGroup> selectCFromCourseC = entityManager.createQuery("select m from AppGroup m", AppGroup.class);
            return selectCFromCourseC.getResultList();
        }

    }
}
