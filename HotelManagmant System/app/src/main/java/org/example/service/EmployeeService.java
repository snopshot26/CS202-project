
package org.example.service;

import java.util.List;

import org.example.dao.EmployeeDAO;
import org.example.model.Employee;
import org.example.model.Hotel;
import org.example.model.Housekeeping;
import org.example.enums.EmployeeRole;

public class EmployeeService {
    private final EmployeeDAO EmployeeDAO = new EmployeeDAO();

    public Employee getEmployee(long EmployeeId){
        return this.EmployeeDAO.getEmployeeById(EmployeeId);
    }

    public List<Employee> getAllEmployees(){
        return this.EmployeeDAO.getAllEmployees();
    }

    public Hotel getHotelForEmployee(long employeeId) {
        return this.EmployeeDAO.getHotelByEmployeeId(employeeId);
    }

    public List<Employee> getEmployeesByHotelIdAndRole(long hotelId, EmployeeRole role) {
        return this.EmployeeDAO.getEmployeesByHotelIdAndRole(hotelId, role);
    }

     public List<Housekeeping> getHousekeepingByHotelId(long hotelId) {
        return this.EmployeeDAO.getHousekeepingByHotelId(hotelId);
    }
}
