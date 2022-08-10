package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;

import java.util.List;

public interface PostService {
    Post writePost(Post post, String id);

    Post findPostById(String postId);

    List<Comment> getCommentsByPost(String postId);
}
