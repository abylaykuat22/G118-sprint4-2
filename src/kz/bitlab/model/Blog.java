package kz.bitlab.model;

import java.time.LocalDateTime;

public class Blog {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime postDate;
  private User user;

  public Blog(String title, String content, User user) {
    this.title = title;
    this.content = content;
    this.user = user;
  }

  public Blog() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
}
