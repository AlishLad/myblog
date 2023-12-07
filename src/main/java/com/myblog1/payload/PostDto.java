package com.myblog1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor@NoArgsConstructor
public class PostDto {

    private long id;
    @NotEmpty
    private  String title;
    @NotEmpty
    @Size(min = 10,message = "Description Should Contain Atleast 10 letters")
    private  String description;
    @NotEmpty
    private String content;

}
