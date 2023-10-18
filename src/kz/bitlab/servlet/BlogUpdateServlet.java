package kz.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kz.bitlab.db.DBManager;
import kz.bitlab.model.Blog;
import kz.bitlab.model.User;

@WebServlet("/blog-update")
public class BlogUpdateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Long blogId = Long.parseLong(req.getParameter("blog_id"));
    String title = req.getParameter("blog_title");
    String content = req.getParameter("blog_content");
    User user = (User) req.getSession().getAttribute("currentUser");
    Blog blog = DBManager.getBlogById(blogId);
    blog.setTitle(title);
    blog.setContent(content);
    blog.setUser(user);
    DBManager.updateBlog(blog);
    resp.sendRedirect("/blogs");
  }
}
