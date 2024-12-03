package com.example.homework_9.servlets;

import com.example.homework_9.entity.AppGroup;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.homework_9.MyListener.emf;

@WebServlet("/group/delete")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the groupId parameter from the request
        String id = req.getParameter("groupId");

        // Validate if the id is null or empty
        if (id == null || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing group ID.");
            return;
        }

        try (EntityManager entityManager = emf.createEntityManager()) {
            Integer groupId = null;

            // Try to parse the groupId
            try {
                groupId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid group ID format.");
                return;
            }

            // Find the group entity (change AppGroup if necessary)
            AppGroup appGroup = entityManager.find(AppGroup.class, groupId);
            if (appGroup != null) {
                // Begin transaction and delete the group
                entityManager.getTransaction().begin();
                entityManager.remove(appGroup);
                entityManager.getTransaction().commit();

                resp.sendRedirect("/group.jsp");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Group not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting group: " + e.getMessage());
        }
    }
}
