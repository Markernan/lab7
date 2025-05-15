package com.example.lab7_20221747.servlets;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EmployeeServlet", value = "/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;

    @Override
    public void init() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createEmployee(request, response);
        } else if ("update".equals(action)) {
            updateEmployee(request, response);
        } else {
            listEmployees(request, response);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("employees", employeeDao.getAllEmployees());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employees/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("nextEmpNo", employeeDao.getNextEmployeeId());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employees/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empNo = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDao.getEmployeeById(empNo);
        request.setAttribute("employee", existingEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employees/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Employee emp = new Employee();
            emp.setEmpNo(Integer.parseInt(request.getParameter("emp_no")));
            emp.setBirthDate(parseDate(request.getParameter("birth_date")));
            emp.setFirstName(request.getParameter("first_name"));
            emp.setLastName(request.getParameter("last_name"));
            emp.setGender(request.getParameter("gender"));
            emp.setHireDate(parseDate(request.getParameter("hire_date")));

            employeeDao.createEmployee(emp);
            response.sendRedirect("employees");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/employees/create.jsp").forward(request, response);
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Employee emp = new Employee();
            emp.setEmpNo(Integer.parseInt(request.getParameter("emp_no")));
            emp.setBirthDate(parseDate(request.getParameter("birth_date")));
            emp.setFirstName(request.getParameter("first_name"));
            emp.setLastName(request.getParameter("last_name"));
            emp.setGender(request.getParameter("gender"));
            emp.setHireDate(parseDate(request.getParameter("hire_date")));

            employeeDao.updateEmployee(emp);
            response.sendRedirect("employees");
        } catch (ParseException e) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("/employees/edit.jsp").forward(request, response);
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
    //todavia no nos enseñan a eliminar
}


}