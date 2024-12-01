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
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .form-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        table {
            background-color: #ffffff;
            border-radius: 10px;
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

        .btn {
            transition: all 0.3s ease;
        }

        .btn-danger:hover {
            background-color: #b20000;
        }

        .btn-dark:hover {
            background-color: #333333;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Form to add a new course -->
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="form-container">
                <h3 class="text-center mb-3">Add New Course</h3>
                <form action="/course" method="post" class="input-group">
                    <input class="form-control" type="text" name="name" placeholder="Enter course name..." required>
                    <button class="btn btn-dark">Add</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Table to display courses -->
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Go to module</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Course> courses = CourseRepo.getAllCourses();
                    for (Course cours : courses) {
                %>
                <tr>
                    <td><%= cours.getId() %>
                    </td>
                    <td><%= cours.getName() %>
                    </td>
                    <td>

                    </td>
                    <td>
                        <form action="/course/delete" method="post" class="d-inline">
                            <input type="hidden" name="courseId" value="<%= cours.getId() %>">
                            <button class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
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
