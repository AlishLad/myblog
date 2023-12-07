package com.myblog1.service.impl;

import com.myblog1.entity.Post;
import com.myblog1.exception.ResourceNotFound;
import com.myblog1.payload.PostDto;
import com.myblog1.payload.PostResponse;
import com.myblog1.repository.PostRepository;
import com.myblog1.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository repository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }



    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = maptoEntity(postDto);
        Post saved = repository.save(post);
        PostDto dto = maptoDto(saved);
        return dto;
    }

    @Override
    public void deletePostById(long id) {
        Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found by Id :" + id));
        repository.deleteById(id);
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found by Id :" + id));
        PostDto dto = maptoDto(post);
        return dto;
    }

    @Override
    public PostDto updatePostById(long id, PostDto dto) {
        Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found by Id :" + id));

        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        Post saved = repository.save(post);
        PostDto postDto = maptoDto(saved);
        return postDto;
    }

    @Override
    public PostResponse getPost(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest request = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = repository.findAll(request);
        List<Post> list = page.getContent();
        List<PostDto> dtos = list.stream().map(e -> maptoDto(e)).collect(Collectors.toList());
        PostResponse response = new PostResponse();
        response.setDtos(dtos);
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }

    Post maptoEntity(PostDto dto){

        Post post = modelMapper.map(dto, Post.class);

//        Post post = new Post();
//        post.setTitle(dto.getTitle());
//        post.setDescription(dto.getDescription());
//        post.setContent(dto.getContent());
        return post;
    }

    PostDto maptoDto(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }
}
