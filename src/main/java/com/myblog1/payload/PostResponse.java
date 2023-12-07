package com.myblog1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {


    private List<PostDto> dtos;

    private int pageNo;

    private int pageSize;

    private int totalPage;

    private boolean lastPage;

}
