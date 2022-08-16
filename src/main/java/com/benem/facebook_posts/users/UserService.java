package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;

import java.util.Date;
import java.util.List;

public interface UserService {
    User registerUser(User user);

    User loginUser(String userName);

    boolean takenUserName(String userName);

    User findUserById(String id);

    Long getDaysInMilliSec(Long days);

    boolean wasCreatedInGivenRange(Date createdAt, Long days);

    List<Post> getPostsByUser(String userId);

    List<Comment> getCommentsByUser(String userId);


    Post addPost(String userId, Post post);

    String followSomeOne(String userId, String followedId);

    List<User> getFollowList(String userId);

    List<Post> getFeed(String userId);
}
