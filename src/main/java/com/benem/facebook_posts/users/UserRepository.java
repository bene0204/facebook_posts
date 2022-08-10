package com.benem.facebook_posts.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsUserByUserName(String userName);

    User findUserByUserName(String userName);
}
