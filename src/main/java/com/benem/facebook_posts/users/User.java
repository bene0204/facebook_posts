package com.benem.facebook_posts.users;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.posts.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @Builder.Default
    private String userId = UUID.randomUUID().toString();

    @Column(
            name = "user_name",
            length = 50,
            unique = true
    )
    @Length(max = 50)
    private String userName;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "follower_map",
            joinColumns = @JoinColumn(
                    name = "follower_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "followed_id",
                    referencedColumnName = "userId"
            )
    )
    private List<User> followList = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addToFollowList(User user)  {
        followList.add(user);
    }
}
