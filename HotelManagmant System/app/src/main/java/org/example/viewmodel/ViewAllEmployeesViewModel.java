package org.example.viewmodel;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;

import java.util.List;

public class ViewAllEmployeesViewModel {
    private final EmployeeDAO employeeDAO;

    public ViewAllEmployeesViewModel() {
        this.employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
