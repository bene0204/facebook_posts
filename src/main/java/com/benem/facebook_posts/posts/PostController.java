package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public Post writePost(@RequestBody Post post, @RequestParam("authorId") String authorId) {
        return postService.writePost(post,authorId);
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsByPost(@PathVariable("id") String postId){
        return postService.getCommentsByPost(postId);
    }
}
