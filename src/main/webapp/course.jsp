<%@ page import="com.example.homework_9.entity.Course" %>
<%@ page import="com.example.homework_9.CourseRepo" %>
<%@ page import="java.util.List" %>
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
            background-color: #f4f4f9;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .form-container {
            background-color: #ffffff;
            padding: 25px;
            border-radius: 15px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        .form-container h3 {
            color: #333333;
        }

        .table-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        table {
            border-radius: 15px;
            overflow: hidden;
            border-collapse: collapse;
        }

        th {
            background-color: #0056b3;
            color: #ffffff;
            text-align: center;
        }

        tr {
            transition: background-color 0.3s ease;
        }

        tr:hover {
            background-color: #e9ecef;
        }

        tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .btn {
            transition: all 0.3s ease;
        }

        .btn-dark {
            background-color: #0056b3;
            color: #ffffff;
            border: none;
        }

        .btn-dark:hover {
            background-color: #003f88;
        }

        .btn-danger {
            background-color: #dc3545;
            color: #ffffff;
            border: none;
        }

        .btn-danger:hover {
            background-color: #b21f2d;
        }

        .btn-warning {
            background-color: #ffc107;
            color: #ffffff;
            border: none;
        }

        .btn-warning:hover {
            background-color: #e0a800;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Form to add a new course -->
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="form-container">
                <h3 class="text-center mb-4">Add New Course</h3>
                <form action="/course" method="post" class="d-flex">
                    <input class="form-control me-2" type="text" name="name" placeholder="Enter course name..." required>
                    <button class="btn btn-dark" type="submit">Add</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Button to add module -->
    <div class="row justify-content-center mt-4">
        <div class="col-md-6 text-center">
            <a class="btn btn-warning btn-lg" href="AddModule.jsp">Add Module</a>
        </div>
    </div>

    <!-- Table to display courses -->
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <div class="table-container">
                <table class="table table-hover text-center">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Go to Module</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Course> courses = CourseRepo.getAllCourses();
                        if (courses != null && !courses.isEmpty()) {
                            for (Course cours : courses) {
                    %>
                    <tr>
                        <td><%= cours.getId() %></td>
                        <td><%= cours.getName() %></td>
                        <td>
                            <a href="/module.jsp?courseId=<%=cours.getId()%>" class="btn btn-dark btn-sm">Go</a>
                        </td>
                        <td>
                            <form action="/course/delete" method="post" class="d-inline">
                                <input type="hidden" name="courseId" value="<%= cours.getId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-muted">No courses available</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+qtm1D9Lv0PDxOe0chY+aX1b12lK9"
        crossorigin="anonymous"></script>
</body>
</html>
