package kz.bitlab.model;

public class Item {
  private Long id;
  private String name;
  private String description;
  private Double price;
  private City city;

  public Item(Long id, String name, String description, Double price, City city) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.city = city;
  }

  public Item() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
