package com.example.homework_9.servlets;

import com.example.homework_9.entity.AppGroup;
import com.example.homework_9.entity.AppModule;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/group/add")
public class AddGroupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String id = req.getParameter("moduleId");

        // Validate the input parameters
        if (name == null || name.trim().isEmpty() || id == null || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Group name and module ID must be provided");
            return;
        }

        try {
            // Parse the moduleId
            Integer moduleId = null;
            try {
                moduleId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid module ID format");
                return;
            }

            // Find the module entity
            AppModule appModule = findModuleById(moduleId);
            if (appModule == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Module not found");
                return;
            }

            // Begin transaction and persist the group
            try (EntityManager entityManager = emf.createEntityManager()) {
                entityManager.getTransaction().begin();
                AppGroup appGroup = new AppGroup(null, name, appModule);  // Adjust constructor according to your AppGroup class
                entityManager.persist(appGroup);
                entityManager.getTransaction().commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }

        resp.sendRedirect("/group.jsp");  // Redirect back to the group list page
    }

    private AppModule findModuleById(Integer moduleId) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.find(AppModule.class, moduleId);
        }
    }
}
