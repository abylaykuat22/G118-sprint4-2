package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.User;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String email = req.getParameter("user_email");
    String password = req.getParameter("user_password");
    String rePassword = req.getParameter("user_re_password");
    String fullName = req.getParameter("user_full_name");
    LocalDate birthDate = LocalDate.parse(req.getParameter("user_birth_date"));
    User user = DBManager.getUserByEmail(email);
    String redirect = "/?regErrorEmail";
    if (user == null) {
      redirect = "/?regErrorPasswords";
      if (password.equals(rePassword)) {
        redirect = "/?success";
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBirthDate(birthDate);
        DBManager.addUser(user);
      }
    }
    resp.sendRedirect(redirect);
  }
}
