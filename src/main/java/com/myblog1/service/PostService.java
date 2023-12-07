package com.myblog1.service;

import com.myblog1.payload.PostDto;
import com.myblog1.payload.PostResponse;

public interface PostService {
    PostDto savePost(PostDto postDto);

    void deletePostById(long id);

    PostDto getPostById(long id);

    PostDto updatePostById(long id, PostDto dto);

    PostResponse getPost(int pageNo, int pageSize, String sortBy, String sortOrder);
}
