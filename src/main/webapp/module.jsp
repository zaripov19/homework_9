<%@ page import="java.util.List" %>
<%@ page import="com.example.homework_9.ModuleRepo" %>
<%@ page import="com.example.homework_9.entity.Module" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .table {
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        th {
            background-color: #007bff;
            color: #ffffff;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        td {
            vertical-align: middle;
        }

        .btn {
            transition: all 0.3s ease;
        }

        .btn-danger:hover {
            background-color: #d9534f;
        }

        .btn-dark:hover {
            background-color: #343a40;
        }

        .no-data {
            text-align: center;
            color: #6c757d;
            font-style: italic;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
        }

        .action-buttons .btn {
            width: 48%;
        }
    </style>
</head>
<body>
<%
    String id = request.getParameter("courseId");
    Integer courseId = Integer.parseInt(id);
%>
<div class="container">
    <!-- Table to display courses -->
    <div class="row justify-content-center mt-5">
        <div class="col-md-10">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Course Name</th>
                    <th>Go to Group</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Module> modules = ModuleRepo.getAllModules();
                    if (modules != null && !modules.isEmpty()) {
                        for (Module module : modules) {
                            if (module.getCourse().getId() == (courseId)) { // Corrected condition
                %>
                <tr>
                    <td><%= module.getId() %>
                    </td>
                    <td><%= module.getName() %>
                    </td>
                    <td><%= module.getCourse().getName() %>
                    </td> <!-- Display course name -->
                    <td>
                        <a href="/group.jsp?moduleId=<%= module.getId() %>" class="btn btn-dark">Go</a>
                    </td>
                    <td>
                        <form action="/module/delete" method="post" class="d-inline">
                            <input type="hidden" name="moduleId" value="<%= module.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                } else {
                %>
                <tr>
                    <td colspan="5" class="no-data">No modules available</td> <!-- Fixed column count -->
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+qtm1D9Lv0PDxOe0chY+aX1b12lK9"
        crossorigin="anonymous"></script>
</body>
</html>