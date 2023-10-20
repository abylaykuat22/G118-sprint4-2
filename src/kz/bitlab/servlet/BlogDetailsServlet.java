package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.Blog;
import kz.bitlab.model.Comment;

@WebServlet("/blog-details")
public class BlogDetailsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long id = Long.parseLong(req.getParameter("id"));
    Blog blog = DBManager.getBlogById(id);
    req.setAttribute("blog", blog);
    List<Comment> comments = DBManager.getCommentsByBlogId(id);
    req.setAttribute("comments", comments);
    req.getRequestDispatcher("blog-details.jsp").forward(req, resp);
  }
}
