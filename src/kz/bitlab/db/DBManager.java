package kz.bitlab.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kz.bitlab.model.Blog;
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
        user.setBirthDate(resultSet.getObject("birth_date", LocalDate.class));
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public static User getUserById(Long id) {
    User user = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM sprintfortwo.users "
              + "WHERE id = ?"
      );
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        user = new User();
        user.setId(id);
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("full_name"));
        user.setBirthDate(resultSet.getObject("birth_date", LocalDate.class));
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
          "INSERT INTO sprintfortwo.users(email, password, full_name, birth_date) "
              + "VALUES (?, ?, ?, ?)"
      );
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFullName());
      statement.setObject(4, user.getBirthDate());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<Blog> getBlogs() {
    List<Blog> blogs = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM sprintfortwo.blogs ORDER BY post_date DESC"
      );
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Blog blog = new Blog();
        blog.setId(resultSet.getLong("id"));
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        blog.setPostDate(resultSet.getObject("post_date", LocalDateTime.class));
        Long userId = resultSet.getLong("user_id");
        blog.setUser(getUserById(userId));
        blogs.add(blog);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blogs;
  }

  public static void addBlog(Blog blog) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO sprintfortwo.blogs(title, content, post_date, user_id) "
              + "VALUES(?, ?, now(), ?)"
      );
      statement.setString(1, blog.getTitle());
      statement.setString(2, blog.getContent());
      statement.setLong(3, blog.getUser().getId());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Blog getBlogById(Long id) {
    Blog blog = null;
    try {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT b.*, u.email, u.password, u.full_name, u.birth_date FROM sprintfortwo.blogs b "
              + "INNER JOIN sprintfortwo.users u ON b.user_id = u.id "
              + "WHERE b.id = ?"
      );
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        blog = new Blog();
        blog.setId(id);
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        blog.setPostDate(resultSet.getObject("post_date", LocalDateTime.class));

        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("full_name"));
        blog.setUser(user);
      }
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blog;
  }

  public static void updateBlog(Blog blog) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE sprintfortwo.blogs "
              + "SET title = ?, content = ?, user_id = ? "
              + "WHERE id = ?"
      );
      statement.setString(1, blog.getTitle());
      statement.setString(2, blog.getContent());
      statement.setLong(3, blog.getUser().getId());
      statement.setLong(4, blog.getId());
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
