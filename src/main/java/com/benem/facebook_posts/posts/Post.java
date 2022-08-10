package com.benem.facebook_posts.posts;

import com.benem.facebook_posts.comments.Comment;
import com.benem.facebook_posts.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonBackReference(value = "PostAuthorRef")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "userId",nullable = false)
    private User author;

    @JsonManagedReference(value = "PostCommentRef")
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
