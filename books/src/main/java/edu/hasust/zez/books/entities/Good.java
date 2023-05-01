package edu.hasust.zez.books.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    Integer id;
    String name;
    Integer owner;
    String descript;
    String cover;
    Integer type;
    BigDecimal price;
    Integer approved;
    Integer status;
    LocalDateTime createTime;
}
