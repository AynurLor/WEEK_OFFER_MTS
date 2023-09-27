package ru.mts.tariff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tariff")
public class Tariff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "Description")
  private String Description;

  @Column(name = "Prive")
  private String price;

  @Column(name = "tariff")
  private String tariff;

  @Override
  public String toString() {
    return "Tariff{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", Description='"
        + Description
        + '\''
        + ", price="
        + price
        + '}';
  }

  public Tariff(String name, String description, String price, String tariff) {
    this.name = name;
    Description = description;
    this.price = price;
    this.tariff = tariff;
  }
}
