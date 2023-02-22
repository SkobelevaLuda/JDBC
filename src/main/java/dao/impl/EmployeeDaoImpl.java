package dao.impl;

import dao.CityDao;
import dao.EmployeeDAO;
import gdbc.ConnectinManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDAO {
    private static final String INSERT = "INSERT INTO employee (name, surname, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_LAST_EMPLOYEE = "SELECT * FROM employee ORDER BY id DESC LIMIT ";
    private static final String FIND_BY_ID = "SELECT * FROM employee WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM employee WHERE id = ?";
    private static final String UPDATE = "UPDATE employee SET name=?, surname = ?, gender= ?, age = ?, city_id = ? WHERE id = ?";
    private static final String DELITE = "DELITE * FROM employee WHERE id = ?";
    private final CityDao cityDao = new CityDaoImpl();

    @Override
    public Optional<Employee> create(Employee employee) {
        long cityId = 0;
        if (employee.getCity() != null && cityDao.findById(employee.getCity().getCityId()).isPresent()) {
            cityId = employee.getCity().getCityId();
        }

        try (Connection connection = ConnectinManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setInt(5, employee.getAge());
            preparedStatement.setObject(5, cityId);
            if (preparedStatement.executeUpdate() != 0) {
                try (Statement findLastStatement = connection.createStatement();
                     ResultSet resultSet = findLastStatement.executeQuery(FIND_LAST_EMPLOYEE)) {
                    if (resultSet.next()) {
                        return Optional.of(readEmployee(resultSet));
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> readById(long id) {
        try (Connection connection = ConnectinManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(readEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectinManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                employees.add(readEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public Optional<Employee> updateById(Employee employee) {
        long cityId = 0;
        if (employee.getCity() != null && cityDao.findById(employee.getCity().getCityId()).isPresent()) {
            cityId = employee.getCity().getCityId();
        }
        try (Connection connection = ConnectinManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setObject(5, cityId);
            preparedStatement.setObject(6, employee.getId());
            if (preparedStatement.executeUpdate() != 0) {
                return create(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> deleteById(Employee id) {
        Optional<Employee> optionalEmployee = create(id);
        if (optionalEmployee.isPresent()) {
            try (Connection connection = ConnectinManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELITE)) {
                preparedStatement.setObject(1, id);
                if (preparedStatement.executeUpdate() != 0) {
                    return optionalEmployee;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return Optional.empty();
    }

        private Employee readEmployee (ResultSet resultSet) throws SQLException {
            Long cityId = resultSet.getObject("city_id", Long.class);
            City city = null;
            if (cityId != null) {
                city = cityDao.findById(cityId).orElse(null);
            }
            return new Employee(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("gender"),
                    resultSet.getInt("age"),
                    city);

        }
    }
