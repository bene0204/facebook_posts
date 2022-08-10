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
    public Post writePost(Post post,String authorId) {

        var user = userService.findUserById(authorId);

        post.setAuthor(user);

       return postRepository.save(post);
    }

    @Override
    public List<Comment> getCommentsByPost(String postId) {
        var post = findPostById(postId);

        return post.getComments();
    }

    @Override
    public Post findPostById(String postId) {
        return postRepository.findById(postId).get();
    }




}
