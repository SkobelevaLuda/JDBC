import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {



        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (1)")) {

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = "Имя: " + resultSet.getString("name");
                String surnamme = "Фамилия: " + resultSet.getString("surname");
                //String city = "Город: " + resultSet.getString("city");


                System.out.println(name);
                System.out.println(surnamme);
                //System.out.println(city);

            }
        }
    }

}
