package com.benem.facebook_posts.comments;

import java.util.List;

public interface CommentService {
    Comment writeCommentToPost(Comment comment, String postId, String authorId);

    Comment writeCommentToComment(Comment comment, String parentId, String authorId);

    Comment findCommentById(String id);

    List<Comment> getChildrenComments(String commentId);
}
