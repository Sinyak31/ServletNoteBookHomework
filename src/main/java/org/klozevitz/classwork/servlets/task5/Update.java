package org.klozevitz.classwork.servlets.task5;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.klozevitz.classwork.db.DbDao;
import org.klozevitz.classwork.db.task4;
import org.klozevitz.classwork.db.task5;
import org.klozevitz.classwork.model.Notepad;


import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/update")
public class Update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            completeRequest(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/update.jsp");
        dispatcher.forward(request, response);
    }

    private void completeRequest(HttpServletRequest request) throws SQLException {
        task5 dao = new DbDao();
        request.setAttribute("types", dao.pageTypes());
        request.setAttribute("covers", dao.covers());
        dao.closeConnection();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Notepad notepadToUpdate = new Notepad();
        response.setContentType("text/html");
        task5 dao = new DbDao();
        dao.update(request);
//       request.setAttribute("notepadToUpdate", notepadToUpdate);
//        Enumeration<String> c = request.getParameterNames();
//        StringBuilder builder = new StringBuilder();
//        while (c.hasMoreElements())
//        {
//
//            builder.append(c.nextElement()+",");
//        }
//        builder.replace(builder.length() - 1, builder.length(), "");
        System.out.println(Util.completeQuery(request));
//        System.out.println(request.getParameterMap());
        try {
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
