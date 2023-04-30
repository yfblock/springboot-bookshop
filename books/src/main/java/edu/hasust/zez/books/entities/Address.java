package edu.hasust.zez.books.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    Integer id;
    String title;
    String address;
    String phone;
    String people;
    Integer userId;
}
