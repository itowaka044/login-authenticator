package com.login.login_authenticator.repositories;

import com.login.login_authenticator.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
