package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.Item;
import kz.bitlab.model.User;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    var currentUser = (User) req.getSession().getAttribute("currentUser");
    if (currentUser == null) {
      req.getRequestDispatcher("sign-in.jsp").forward(req, resp);
    }
    var items = DBManager.getItems().stream()
        .sorted(Comparator.comparing(Item::getPrice))
        .toList();
    var cities = DBManager.getCities();
    req.setAttribute("items", items);
    req.setAttribute("cities", cities);
    req.getRequestDispatcher("home.jsp").forward(req, resp);
  }
}
