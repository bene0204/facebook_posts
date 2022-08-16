package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        var user = userRepository.findUserByUserName(userName);

        if(user == null) {
            throw new EntityNotFoundException();
        }

        return user;
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
    public Post addPost(String userId, Post post) {
        var user = findUserById(userId);

        post.setAuthor(user);

        user.addPost(post);

        userRepository.save(user);

        return post;
    }

    @Override
    public String followSomeOne(String userId, String followedId) {
        var user = findUserById(userId);
        var targetUser = findUserById(followedId);

        user.addToFollowList(targetUser);
        userRepository.save(user);

        return followedId;
    }

    @Override
    public List<User> getFollowList(String userId) {
        var user = findUserById(userId);

        return user.getFollowList();
    }

    @Override
    public List<Post> getFeed(String userId) {
        var user = findUserById(userId);
        var follows = user.getFollowList();
        List<Post> feed = new ArrayList<>();

        for ( User followedUser :  follows) {

            for(Post post : followedUser.getPosts()){
                if(wasCreatedInGivenRange(post.getCreatedAt(), 3L)){
                    feed.add(post);
                }
            }
        }

        return feed;
    }


    @Override
    public boolean takenUserName(String userName) {
        return userRepository.existsUserByUserName(userName);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Long getDaysInMilliSec(Long days) {
        Long MILLISECSINASECOND = 1000L;
        Long SECONDSINAMINUTE = 60L;
        Long MINUTESINANHOUR = 60L;
        Long HOURSINADAY = 24L;

        return MILLISECSINASECOND * SECONDSINAMINUTE * MINUTESINANHOUR * HOURSINADAY * days;
    }

    @Override
    public boolean wasCreatedInGivenRange(Date createdAt, Long days) {
        Long daysInMilliSec = getDaysInMilliSec(days);

        Long timeStamp = new Date().getTime() - daysInMilliSec;

        Date dateOfEndRange = new Date(timeStamp);

        return !createdAt.before(dateOfEndRange);
    }


}
