package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;

import java.util.List;

public interface PostService {

    Post findPostById(String postId);

    List<Comment> getCommentsByPost(String postId);

    List<Comment> addComment(String postId, Comment comment, String userId);
}
