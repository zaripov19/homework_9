<%@ page import="com.example.homework_9.entity.AppModule" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.homework_9.ModuleRepo" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Add Group</title>
</head>
<body>
<%
    List<AppModule> modules = ModuleRepo.getAllModules();
%>
<div class="row">
    <div class="col-6 offset-3">
        <div class="card">
            <div class="card-header">
                Add Group
            </div>
            <div class="card-body">
                <form action="/group/add" method="post">
                    <div class="form-group">
                        <label for="groupName">Group Name:</label>
                        <input class="form-control" type="text" name="name" id="groupName" placeholder="Group Name" required>
                    </div>

                    <div class="form-group">
                        <label for="moduleSelect">Select Module:</label>
                        <select id="moduleSelect" name="moduleId" class="form-control" required>
                            <option selected disabled value="">Select Module</option>
                            <% if (modules != null && !modules.isEmpty()) { %>
                            <% for (AppModule module : modules) { %>
                            <option value="<%= module.getId() %>"><%= module.getName() %></option>
                            <% } %>
                            <% } else { %>
                            <option value="" disabled>No modules available</option>
                            <% } %>
                        </select>
                    </div>

                    <button class="btn btn-success mt-3" type="submit">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
