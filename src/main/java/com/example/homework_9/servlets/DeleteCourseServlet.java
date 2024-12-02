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
import java.util.List;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/course/delete")
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = emf.createEntityManager()) {
            String courseId = req.getParameter("courseId");
            Integer id = Integer.parseInt(courseId);
            Course course = entityManager.find(Course.class, id);

            // Check for dependent modules
            List<Module> modules = entityManager.createQuery("SELECT m FROM Module m WHERE m.course.id = :courseId", Module.class)
                    .setParameter("courseId", id)
                    .getResultList();

            // Delete dependent modules if they exist
            for (Module module : modules) {
                entityManager.remove(module);
            }

            // Now delete the course
            entityManager.getTransaction().begin();
            entityManager.remove(course);
            entityManager.getTransaction().commit();

            resp.sendRedirect("/course.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting course.");
        }
    }
}
