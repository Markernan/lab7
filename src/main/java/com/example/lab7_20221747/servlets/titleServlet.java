package com.example.lab7_20221747.servlets;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "TitleServlet", value = "/titles")
public class TitleServlet extends HttpServlet {
    private TitleDao titleDao;

    @Override
    public void init() {
        titleDao = new TitleDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteTitle(request, response);
                break;
            default:
                listTitles(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTitle(request, response);
        } else if ("update".equals(action)) {
            updateTitle(request, response);
        } else {
            listTitles(request, response);
        }
    }

    private void listTitles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("titles", titleDao.getCurrentTitles());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/titles/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/titles/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empNo = Integer.parseInt(request.getParameter("id"));
        Title existingTitle = titleDao.getTitleByEmpNo(empNo);
        request.setAttribute("title", existingTitle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/titles/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void createTitle(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Title title = new Title();
            title.setEmpNo(Integer.parseInt(request.getParameter("emp_no")));
            title.setTitle(request.getParameter("title"));
            title.setFromDate(parseDate(request.getParameter("from_date")));
            title.setToDate(parseDate(request.getParameter("to_date")));

            titleDao.createTitle(title);
            response.sendRedirect("titles");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/titles/create.jsp").forward(request, response);
        }
    }

    private void updateTitle(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Title title = new Title();
            title.setEmpNo(Integer.parseInt(request.getParameter("emp_no")));
            title.setTitle(request.getParameter("title"));
            title.setFromDate(parseDate(request.getParameter("from_date")));
            title.setToDate(parseDate(request.getParameter("to_date")));

            titleDao.updateTitle(title);
            response.sendRedirect("titles");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/titles/edit.jsp").forward(request, response);
        }
    }

    private void deleteTitle(HttpServletRequest request, HttpServletResponse response)
          //
    }

