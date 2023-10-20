package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.User;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String commentDescription = req.getParameter("comment_description");
    Long blogId = Long.parseLong(req.getParameter("comment_blog_id"));
    var user = (User) req.getSession().getAttribute("currentUser");
    DBManager.addComment(commentDescription, blogId, user.getId());
    resp.sendRedirect("/blog-details?id="+blogId);
  }
}
