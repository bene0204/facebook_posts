package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        boolean isUserNameTaken = takenUserName(user.getUserName());

        if(isUserNameTaken){
            return null;
        }

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public List<Post> getPostsByUser(String userId) {
       var user = findUserById(userId);

       return user.getPosts();
    }

    @Override
    public List<Comment> getCommentsByUser(String userId) {
        var user = findUserById(userId);

        return user.getComments();
    }
    

    @Override
    public boolean takenUserName(String userName) {
        return userRepository.existsUserByUserName(userName);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).get();
    }


}
