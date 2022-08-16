package com.benem.facebook_posts.comments;

import java.util.List;

public interface CommentService {

    List<Comment> addComment(Comment comment, String parentId, String authorId);

    Comment findCommentById(String id);

    List<Comment> getChildrenComments(String commentId);
}
