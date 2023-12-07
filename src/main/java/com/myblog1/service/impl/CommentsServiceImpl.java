package com.myblog1.service.impl;

import com.myblog1.entity.Comments;
import com.myblog1.entity.Post;
import com.myblog1.exception.ResourceNotFound;
import com.myblog1.payload.CommentDto;
import com.myblog1.repository.CommentRepository;
import com.myblog1.repository.PostRepository;
import com.myblog1.service.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {


    private CommentRepository commentRepository;


    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public CommentsServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto saveComments(long pId, CommentDto commentDto) {
        Post post = postRepository.findById(pId).orElseThrow(() -> new ResourceNotFound("Post not find by Id: "));
        Comments comments = maptoEntity(commentDto);
        comments.setPost(post);
        Comments save = commentRepository.save(comments);
        CommentDto dto = maptoDto(save);

        return dto;
    }

    @Override
    public void deleteComments(long pId, long cId) {
        Post post = postRepository.findById(pId).orElseThrow(() -> new ResourceNotFound("Post not find by Id: "));
        commentRepository.deleteById(cId);
    }

    @Override
    public List<CommentDto> getCommentsById(long pId) {
        List<Comments> comments = commentRepository.findByPostId(pId);
        List<CommentDto> dtos = comments.stream().map(e -> maptoDto(e)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public CommentDto updateCommentsById(long cId, CommentDto commentDto) {

        Comments comments = maptoEntity(commentDto);
        comments.setId(cId);
        Comments save = commentRepository.save(comments);
        CommentDto dto = maptoDto(save);
        return dto;

    }



    Comments maptoEntity(CommentDto dto){
        Comments comments = modelMapper.map(dto, Comments.class);
//        Comments comments = new Comments();
//        comments.setBody(dto.getBody());
//        comments.setEmail(dto.getEmail());
//        comments.setName(dto.getName());
        return comments;
    }

    CommentDto maptoDto(Comments comment){
        CommentDto commentsDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentsDto = new CommentDto();
//        commentsDto.setId(comment.getId());
//        commentsDto.setBody(comment.getBody());
//        commentsDto.setEmail(comment.getEmail());
//        commentsDto.setName(comment.getName());
        return commentsDto;
    }
}
