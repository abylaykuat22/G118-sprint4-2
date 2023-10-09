package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.City;
import kz.bitlab.model.Item;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Item> items = DBManager.getItems();
    List<City> cities = DBManager.getCities();
    req.setAttribute("items", items);
    req.setAttribute("cities", cities);
    req.getRequestDispatcher("home.jsp").forward(req, resp);
  }
}
