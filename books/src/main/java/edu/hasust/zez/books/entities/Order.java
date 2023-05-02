package edu.hasust.zez.books.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Integer id;
    Integer goodId;
    Integer userId;
    Integer addressId;
    LocalDateTime createTime;
}
