package org.klozevitz.classwork.servlets.task2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task2;
import org.klozevitz.classwork.model.Notepad;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/all")
public class All extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        completeRequest(request);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/templates/all.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) {
        task2 dao = new DbDao();
        List<Notepad> all = dao.all();
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("headers",
                Arrays.stream(Notepad.class.getDeclaredFields()).map(Field::getName).toList());
        request.setAttribute("all", all);
    }

    public void destroy() {
    }
}