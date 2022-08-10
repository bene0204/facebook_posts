package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/users/login")
    public User loginUser(@RequestBody String username) {
        return userService.loginUser(username);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsByUser(@PathVariable("id") String userId){
        return userService.getPostsByUser(userId);
    }

    @GetMapping("/users/{id}/comments")
    public List<Comment> getCommentsByUser(@PathVariable("id") String userId){
        return userService.getCommentsByUser(userId);
    }

}
