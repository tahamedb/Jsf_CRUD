package com.example.devoir_jsf.dao;



import com.example.devoir_jsf.model.Employee;
import com.example.devoir_jsf.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    Connection connection=DbUtil.getConnection() ;

    PreparedStatement statement = null;
    ResultSet rs = null;


    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String ADD_EMPLOYE = "insert into employees (name,email,date_of_birth,department) values(?,?,?,?)";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employees WHERE id = ?";
     private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employees SET name = ?, email = ?, date_of_birth = ?, department = ? WHERE id = ?";


    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try {

            statement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("date_of_birth");
                String department = rs.getString("department");
                employees.add(new Employee(id, name, email, dob, department));
            }
        } catch (SQLException e) {
            // Handle SQL exception
        }
        return employees;
    }
    public void AjouterUtilisateur(Employee employee) {
        try {
            statement = connection.prepareStatement(ADD_EMPLOYE);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setDate(3, new java.sql.Date(employee.getDob().getTime()));
            statement.setString(4, employee.getDepartment());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // Assurez-vous de fermer les ressources JDBC correctement dans le bloc finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void deleteEmployee(int id)
            throws SQLException
    {
        String query
                = "delete from employees where id =?";
        PreparedStatement ps
                = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        System.out.println("dkhlt l update dyal dao");
        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setDate(3, new java.sql.Date(employee.getDob().getTime()));
            statement.setString(4, employee.getDepartment());
            statement.setInt(5, employee.getId());

            return statement.executeUpdate() > 0;
        }
    }


}




