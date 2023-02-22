package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee);
    Employee readById(int id);
    List<Employee> readAll();
    void updateById(int id);
    void deleteById(int id);
}

