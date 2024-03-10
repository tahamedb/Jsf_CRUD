package com.example.devoir_jsf.service;
import com.example.devoir_jsf.dao.EmployeeDAO;
import com.example.devoir_jsf.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }
    public void ajouterEmploye(Employee employee) throws SQLException {
        employeeDAO.AjouterUtilisateur(employee);
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        System.out.println("dkhl l  employee dyal service");

        employeeDAO.deleteEmployee(employee.getId());
        System.out.println("khrj l  employee dyal service");

    }

    public void updateEmployee(Employee employee) throws SQLException {
        employeeDAO.updateEmployee(employee);
    }


}
