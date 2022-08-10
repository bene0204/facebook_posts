package com.benem.facebook_posts.comments;

import com.benem.facebook_posts.posts.Post;
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
@Table(name = "comments")
public class Comment {

    @Id
    @Builder.Default
    private String commentId = UUID.randomUUID().toString();

    @Column(nullable = false,length = 500)
    private String content;

    @JsonBackReference(value = "CommentAuthorRef")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "userId",nullable = false)
    private User author;


    @JsonBackReference(value = "ParentChildRef")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "commentId")
    private Comment parent;

    @JsonManagedReference(value = "ParentChildRef")
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Comment> children = new ArrayList<>();


    @JsonBackReference(value = "PostCommentRef")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;
}
