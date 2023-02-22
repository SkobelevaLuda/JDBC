package dao.impl;

import dao.EmployeeDAO;
import gdbc.ConnectinManager;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    @Override
    public void create(Employee employee){
        // Формируем запрос к базе с помощью PreparedStatement
        try(PreparedStatement statement = ConnectinManager.getConnection().prepareStatement(
                "INSERT INTO employee (name, surname, age) VALUES ((2), (3), (5))")) {
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getSurname());
            statement.setInt(5, employee.getAge());

            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Employee readById(int id){
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN surname ON book.author_id=author.author_id AND book_id=(?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, id);

            // Делаем запрос к базе и результат кладем в ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while(resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                // и присваиваем их полим объекта
                book.setId(Integer.parseInt(resultSet.getString("book_id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(new Author(resultSet.getInt("author_id"),
                        resultSet.getString("name_author")));
                book.setAmount(Integer.parseInt(resultSet.getString("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;

    }

    @Override
    public List<Employee> readAll() {
        return null;
    }

    @Override
    public void updateById(int id) {

    }

    @Override
    public void deleteById(int id) {

    }
}
