package com.example.homework_9.servlets;

import com.example.homework_9.entity.Module;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/module/delete")
public class DeleteModuleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = emf.createEntityManager()) {
            // Fetch the moduleId parameter from the request
            String moduleId = req.getParameter("moduleId");
            Integer id = Integer.parseInt(moduleId); // Convert moduleId to Integer

            // Find the module entity by its ID
            Module module = entityManager.find(Module.class, id);

            if (module != null) {
                // Begin transaction
                entityManager.getTransaction().begin();

                // Remove the module
                entityManager.remove(module);

                // Commit the transaction to complete deletion
                entityManager.getTransaction().commit();

                // Redirect to the page with updated module list
                resp.sendRedirect("/module.jsp");
            } else {
                // If module does not exist, send error
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Module not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting module.");
        }
    }
}
