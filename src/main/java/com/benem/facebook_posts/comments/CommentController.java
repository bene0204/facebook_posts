package com.benem.facebook_posts.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/post")
    public Comment writeCommentToPost(
            @RequestBody Comment comment,
            @RequestParam("postId") String postId,
            @RequestParam("authorId") String authorId
    ){
        return commentService.writeCommentToPost(comment, postId, authorId);
    }

    @PostMapping("/comments/comment")
    public Comment writeCommentToComment(
            @RequestBody Comment comment,
            @RequestParam("parentId") String parentId,
            @RequestParam("authorId") String authorId
    ){
        return commentService.writeCommentToComment(comment, parentId, authorId);
    }

    @GetMapping("/comments/{id}/children")
    public List<Comment> getChildrenComments(@PathVariable("id") String commentId){
        return commentService.getChildrenComments(commentId);
    }
}
