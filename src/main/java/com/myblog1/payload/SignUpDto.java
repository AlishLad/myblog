package com.myblog1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private Long id;

    private String name;


    private String email;


    private String password;

    private Set<String> role;


    private String username;

}
