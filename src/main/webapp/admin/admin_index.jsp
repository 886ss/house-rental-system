<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.house.javabean.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理员首页 - 房屋租赁系统</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .nav {
            background-color: #343a40;
            padding: 10px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
        }

        .nav a:hover {
            background-color: #495057;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
        }

        .welcome {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>房屋租赁系统 - 管理员平台</h1>
        <p>欢迎您，<%= session.getAttribute("username") %>（管理员）</p>
    </div>

    <div class="nav">
        <a href="admin_index.jsp">首页</a>
        <a href="../login?action=logout">退出登录</a>
    </div>

    <div class="container">
        <div class="welcome">
            <h2>管理员功能即将推出</h2>
            <p>这是一个管理员专属页面，您可以在这里管理用户、房源和租赁信息。</p>
        </div>
    </div>
</body>
</html>