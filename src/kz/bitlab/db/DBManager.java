package kz.bitlab.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.bitlab.model.Item;

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
          "SELECT * FROM sprintfortwo.items");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Item item = new Item();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getDouble("price"));
        items.add(item);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return items;
  }
}
