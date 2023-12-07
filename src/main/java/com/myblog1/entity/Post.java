package com.myblog1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title" , unique = true)
    private  String title;
    @Column(name = "description" , unique = true)
    private  String description;
    @Column(name = "content" , unique = true)
    private String content;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "post")
    List<Comments> comments = new ArrayList<>();


}
