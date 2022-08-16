package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Builder.Default
    private String postId = UUID.randomUUID().toString();

    @Column(
            nullable = false,
            length = 2500
    )
    private String content;

    private Date createdAt = new Date();


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "userId",nullable = false)
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
