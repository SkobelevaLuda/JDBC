import dao.EmployeeDAO;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        City kazan = new City(6,"Казань");
        City podolsk = new City(7,"Подольск");

        employeeDAO.create(new Employee("Марфа", "Васильева", "жен", 50, kazan))
                .ifPresent(employee -> System.out.println("Добавлен сотрудник" +employee));
        Optional<Employee> employeeOptional=employeeDAO.create(new Employee
                ("Иван", "Ванин", "муж", 40, podolsk));
      //  System.out.println(employeeDAO.create(new Employee("Марфа","Васильева", "жен",55)));

        System.out.println("Все сотрудники");
        employeeDAO.readAll().forEach(System.out::println);

        if (employeeOptional.isPresent()){
            employeeDAO.readById(employeeOptional.get().getId())
                    .ifPresent(employee -> System.out.println("Найден сотрудник"+employee));
        }
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setAge(25);
            employee.setName("Ольга");
            employeeDAO.updateById(employee)
                    .ifPresent(emp -> System.out.println("Обновленный сотрудник"+emp));
        }

        if (employeeOptional.isPresent()){
            employeeDAO.deleteById(employeeOptional.get().getId())
                    .ifPresent(emp -> System.out.println(" Удаленный сотрудник"+emp));
        }

    }

}
