package com.myblog1.service;

import com.myblog1.payload.CommentDto;

import java.util.List;

public interface CommentsService {


    CommentDto saveComments(long pId, CommentDto commentDto);

    void deleteComments(long pId, long cId);

    List<CommentDto> getCommentsById(long pId);

    CommentDto updateCommentsById(long cId, CommentDto commentDto);
}
