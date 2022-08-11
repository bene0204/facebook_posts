package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;
import com.benem.facebook_posts.users.dtos.LoginDTO;
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
    public User loginUser(@RequestBody LoginDTO dto) {
        return userService.loginUser(dto.getUserName());
    }

    @PostMapping("/users/{id}/post")
    public Post addPost(@PathVariable("id") String userId, @RequestBody Post post) {
        return userService.addPost(userId, post);
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsByUser(@PathVariable("id") String userId){
        return userService.getPostsByUser(userId);
    }

    @GetMapping("/users/{id}/comments")
    public List<Comment> getCommentsByUser(@PathVariable("id") String userId){
        return userService.getCommentsByUser(userId);
    }

    @PatchMapping("/users/{id}/follow")
    public String followSomeOne(@PathVariable("id") String userId,@RequestParam("followedId") String followedId) {
        return userService.followSomeOne(userId, followedId);
    }

    @GetMapping("/users/{id}/follows")
    public List<User> getFollowList(@PathVariable("id") String userId){
        return userService.getFollowList(userId);
    }

}
