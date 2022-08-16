package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts/{id}/comment")
    public List<Comment> addComment(@PathVariable("id") String postId, @RequestBody Comment comment, @RequestParam("userId") String userId){
        return postService.addComment(postId,comment, userId);
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsByPost(@PathVariable("id") String postId){
        return postService.getCommentsByPost(postId);
    }

}
