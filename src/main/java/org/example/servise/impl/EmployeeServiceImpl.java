package org.example.servise.impl;

import org.example.dao.EmployeeDao;
import org.example.dao.Impl.EmployeeDaoImpl;
import org.example.models.Employee;
import org.example.models.Job;
import org.example.servise.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
employeeDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
employeeDao.addEmployee(employee);
    }

    @Override
    public void dropTable() {
employeeDao.dropTable();
    }

    @Override
    public void cleanTable() {
employeeDao.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
employeeDao.updateEmployee(id,employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        employeeDao.getAllEmployees();
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        employeeDao.findByEmail(email);
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        employeeDao.getEmployeeById(employeeId);
        return null;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        employeeDao.getEmployeeByPosition(position);
        return null;
    }
}
