
package org.example.service;

import java.util.List;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;

public class EmployeeService {
    private final EmployeeDAO EmployeeDAO = new EmployeeDAO();

    public Employee getEmployee(long EmployeeId){
        return this.EmployeeDAO.getEmployeeById(EmployeeId);
    }

    public List<Employee> getAllEmployees(){
        return this.EmployeeDAO.getAllEmployees();
    }
}
