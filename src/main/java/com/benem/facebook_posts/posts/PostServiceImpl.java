package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Comment> getCommentsByPost(String postId) {
        var post = findPostById(postId);

        return post.getComments();
    }

    @Override
    public List<Comment> addComment(String postId, Comment comment, String userId) {
        var post = findPostById(postId);
        var user = userService.findUserById(userId);

        comment.setPost(post);
        comment.setAuthor(user);

        post.addComment(comment);

        postRepository.save(post);

        return post.getComments();
    }

    @Override
    public Post findPostById(String postId) {
        return postRepository.findById(postId).get();
    }




}
