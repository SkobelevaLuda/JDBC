package dao;

import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    Optional<Employee> create(Employee employee);

    Optional<Employee> readById(long id);

    List<Employee> readAll();

    Optional<Employee> updateById(Employee employee);

    Optional<Employee> deleteById(long id);
}

