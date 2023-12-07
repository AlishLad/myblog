package com.myblog1.repository;

import com.myblog1.entity.Comments;
import com.myblog1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {

    List<Comments> findByPostId(long postId);

    Post findByid(long cId);

}
