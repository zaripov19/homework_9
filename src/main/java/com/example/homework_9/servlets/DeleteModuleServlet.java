package com.example.homework_9.servlets;

import com.example.homework_9.entity.AppModule;
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
        String moduleId = req.getParameter("moduleId");
        if (moduleId == null || moduleId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Module ID is required.");
            return;
        }

        Integer id;
        try {
            id = Integer.parseInt(moduleId);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid module ID.");
            return;
        }

        try (EntityManager entityManager = emf.createEntityManager()) {
            AppModule appModule = entityManager.find(AppModule.class, id);
            if (appModule != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(appModule);
                entityManager.getTransaction().commit();

                resp.sendRedirect("/module.jsp");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Module not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting module: " + e.getMessage());
        }
    }
}
