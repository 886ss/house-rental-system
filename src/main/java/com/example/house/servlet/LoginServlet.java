package com.example.house.servlet;

import com.example.house.dao.UserDAO;
import com.example.house.javabean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", username);

            // 根据用户角色跳转到不同的页面
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin/admin_index.jsp");
            } else {
                response.sendRedirect("user/user_index.jsp");
            }
        } else {
            request.setAttribute("error", "用户名或密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin/admin_index.jsp");
            } else {
                response.sendRedirect("user/user_index.jsp");
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}