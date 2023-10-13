package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.User;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email = req.getParameter("user_email");
    String password = req.getParameter("user_password");
    User currentUser = DBManager.getUserByEmail(email);
    String redirect = "/?emailError";
    if (currentUser != null) {
      redirect = "/?passwordError";
      if (currentUser.getPassword().equals(password)) {
        redirect = "/";
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", currentUser);
      }
    }
    resp.sendRedirect(redirect);
  }
}
