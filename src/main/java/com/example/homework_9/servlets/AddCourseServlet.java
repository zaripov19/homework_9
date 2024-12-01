package com.example.homework_9.servlets;

import com.example.homework_9.entity.Course;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/course")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = emf.createEntityManager()) {
            String name = req.getParameter("name");
            Course course = new Course(name);
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            resp.sendRedirect("/course.jsp");
        }
    }
}
