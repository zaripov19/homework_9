package com.example.homework_9.servlets;

import com.example.homework_9.entity.Course;
import com.example.homework_9.entity.Module;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/module/add")
public class AddModuleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("courseId");

        // Validate the input parameters
        if (name == null || name.trim().isEmpty() || id == null || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            return;
        }

        try (EntityManager entityManager = emf.createEntityManager()) {
            // Parse courseId
            Integer courseId = null;
            try {
                courseId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid course ID format");
                return;
            }

            // Find the course entity
            Course course = entityManager.find(Course.class, courseId);
            if (course == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
                return;
            }

            // Begin transaction, persist module
            entityManager.getTransaction().begin();
            try {
                Module module = new Module(
                        null,
                        name,
                        course
                );
                entityManager.persist(module);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw e;  // Rethrow the exception to be handled outside
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }

        resp.sendRedirect("/course.jsp");
    }
}
