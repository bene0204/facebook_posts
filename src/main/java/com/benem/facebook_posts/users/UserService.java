package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);

    User loginUser(String userName);

    boolean takenUserName(String userName);

    User findUserById(String id);

    List<Post> getPostsByUser(String userId);

    List<Comment> getCommentsByUser(String userId);


}
