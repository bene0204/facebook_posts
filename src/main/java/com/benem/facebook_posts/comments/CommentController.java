package com.benem.facebook_posts.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/{id}/comment")
    public List<Comment> addComment(
            @RequestBody Comment comment,
            @PathVariable("id") String parentId,
            @RequestParam("authorId") String authorId
    ){
        return commentService.addComment(comment, parentId, authorId);
    }

    @GetMapping("/comments/{id}/children")
    public List<Comment> getChildrenComments(@PathVariable("id") String commentId){
        return commentService.getChildrenComments(commentId);
    }
}
