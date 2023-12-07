package com.myblog1.controller;

import com.myblog1.payload.CommentDto;
import com.myblog1.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentsService service;

    public CommentController(CommentsService service) {
        this.service = service;
    }
    @PostMapping()
    public ResponseEntity<CommentDto> createComments(
      @RequestParam("pId") long pId,
      @RequestBody CommentDto commentDto

    ){
      CommentDto dto=service.saveComments(pId,commentDto);
      return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteComments(
            @RequestParam("pId") long pId,
            @RequestParam("cId") long cId

    ){
        service.deleteComments(pId,cId);
        return new ResponseEntity<>("Comment Deleted !!", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getCommentsById(
            @RequestParam("pId") long pId
    ){
        List<CommentDto> dtos = service.getCommentsById(pId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{cId}")
    public ResponseEntity<CommentDto> updateComments(
            @PathVariable("cId") long cId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto=service.updateCommentsById(cId,commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
