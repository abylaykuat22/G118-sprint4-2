package kz.bitlab.model;

import java.time.LocalDateTime;

public class Comment {
  private Long id;
  private String description;
  private LocalDateTime postDate;
  private User user;
  private Blog blog;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getPostDate() {
    return postDate;
  }

  public void setPostDate(LocalDateTime postDate) {
    this.postDate = postDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }
}
