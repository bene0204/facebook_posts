package com.benem.facebook_posts.comments;

import com.benem.facebook_posts.posts.PostService;
import com.benem.facebook_posts.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Override
    public Comment writeCommentToPost(Comment comment, String postId, String authorId) {

        var post = postService.findPostById(postId);
        var author = userService.findUserById(authorId);

        comment.setAuthor(author);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Override
    public Comment writeCommentToComment(Comment comment, String parentId, String authorId) {
        var parentComment = findCommentById(parentId);
        var author = userService.findUserById(authorId);

        comment.setParent(parentComment);
        comment.setAuthor(author);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getChildrenComments(String commentId) {
        var parentComment = findCommentById(commentId);

        return parentComment.getChildren();
    }

    @Override
    public Comment findCommentById(String id) {
        return commentRepository.findById(id).get();
    }




}
