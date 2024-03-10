package com.example.devoir_jsf.bean;


import com.example.devoir_jsf.dao.EmployeeDAO;
import com.example.devoir_jsf.model.Employee;
import com.example.devoir_jsf.service.EmployeeService;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean

public class EmployeeBean implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date dob;
    private String searchQuery;
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String department;
    private Employee employee ;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private List<Employee> employees;
    private EmployeeService employeeService = new EmployeeService();


    public EmployeeBean() throws SQLException {
        employees = employeeService.getAllEmployees();
        employee = new Employee();
    }
    public void searchEmployees() {
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            // If the search query is empty, show all employees
            try {
                employees = employeeService.getAllEmployees();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Filter the list of employees by the search query
            employees = employees.stream()
                    .filter(e -> e.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Email Address is Not Valid");
            throw new ValidatorException(message);
        }
    }
    public void saveEmployee() {
        try {
            employee = new Employee(employee.getName(), employee.getEmail(), employee.getDob(), employee.getDepartment());

            employeeService.ajouterEmploye(employee);

            employees = employeeService.getAllEmployees();
            System.out.println("employee: " + employee);
            System.out.println("helo");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }




    public void deleteEmployee(Employee employee) throws SQLException {
        System.out.println("dkhl l delete employee dyal bean");
        EmployeeDAO empl=new EmployeeDAO();
        empl.deleteEmployee(employee.getId());
        System.out.println("khrj l delete employee dyal bean");

        employees.remove(employee);
    }

    public void editEmployee(Employee employee) {
        employee.setEditable(true);
    }

    public void saveChanges(Employee employee) {
        System.out.println("Attempting to update employee with ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("DOB: " + employee.getDob());
        System.out.println("Department: " + employee.getDepartment());
        try {
            System.out.println("dkhlt l update dyal bean");

            EmployeeDAO emp=new EmployeeDAO();
            emp.updateEmployee(employee);
            // Optionally, refresh the list of employees to reflect the update
            employees = emp.getAllEmployees();

        } catch (SQLException e) {
            // Handle exception, e.g., log it or show an error message
        }
    }


}
