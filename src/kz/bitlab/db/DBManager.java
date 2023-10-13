package kz.bitlab.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.bitlab.model.City;
import kz.bitlab.model.Item;
import kz.bitlab.model.User;

public class DBManager {

  private static Connection connection;

  static {
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/G118?currentSchema=sprintfortwo",
          "postgres",
          "postgres"
      );
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Item> getItems() {
    List<Item> items = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT i.id, i.name, i.description, i.price, i.city_id, "
              + "c.name as city_name, c.code FROM sprintfortwo.items i "
              + "INNER JOIN sprintfortwo.cities c on i.city_id = c.id "
              + "ORDER BY i.price");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Item item = new Item();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getDouble("price"));

        City city = new City();
        city.setId(resultSet.getLong("city_id"));
        city.setName(resultSet.getString("city_name"));
        city.setCode(resultSet.getString("code"));

        item.setCity(city);
        items.add(item);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return items;
  }

  public static List<City> getCities() {
    List<City> cities = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM sprintfortwo.cities"
      );
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        City city = new City();
        city.setId(resultSet.getLong("id"));
        city.setName(resultSet.getString("name"));
        city.setCode(resultSet.getString("code"));
        cities.add(city);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cities;
  }

  public static void addItem(Item item) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO sprintfortwo.items(name, description, price, city_id) "
              + "VALUES (?, ?, ?, ?)"
      );
      statement.setString(1, item.getName());
      statement.setString(2, item.getDescription());
      statement.setDouble(3, item.getPrice());
      statement.setLong(4, item.getCity().getId());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static City getCityById(Long id) {
    City city = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM sprintfortwo.cities WHERE id = ?"
      );
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        city = new City();
        city.setId(id);
        city.setName(resultSet.getString("name"));
        city.setCode(resultSet.getString("code"));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return city;
  }

  public static void deleteItemById(Long id) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM sprintfortwo.items WHERE id = ?"
      );
      statement.setLong(1, id);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static User getUserByEmail(String email) {
    User user = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM sprintfortwo.users "
              + "WHERE email = ?"
      );
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(email);
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("full_name"));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public static void addUser(User user) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO sprintfortwo.users(email, password, full_name) "
              + "VALUES (?, ?, ?)"
      );
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFullName());
      statement.executeUpdate();
      statement.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
