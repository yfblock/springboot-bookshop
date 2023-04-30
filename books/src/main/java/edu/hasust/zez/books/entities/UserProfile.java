package edu.hasust.zez.books.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    Integer id;
    String phone;
    String email;
    String avatar;
    Integer userId;
}
