package com.benem.facebook_posts.comments;

import com.benem.facebook_posts.posts.Post;
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
@Table(name = "comments")
public class Comment {

    @Id
    @Builder.Default
    private String commentId = UUID.randomUUID().toString();

    @Column(nullable = false,length = 500)
    private String content;

    private Date createdAt = new Date();


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "userId",nullable = false)
    private User author;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "commentId")
    private Comment parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;

    public void addComment(Comment comment) {
        children.add(comment);
    }
}
