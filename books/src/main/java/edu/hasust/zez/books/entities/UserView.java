package edu.hasust.zez.books.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    Integer id;
    String username;
    @JsonIgnore
    String password;
    String avatar;
    String email;
    String phone;
}
