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

@WebServlet("/course/delete")
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = emf.createEntityManager()) {
            String courseId = req.getParameter("courseId");
            Integer id = Integer.parseInt(courseId);
            Course course = entityManager.find(Course.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            resp.sendRedirect("/course.jsp");
        }
    }
}
