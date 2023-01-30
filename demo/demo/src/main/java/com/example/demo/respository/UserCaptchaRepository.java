package com.example.demo.respository;

import com.example.demo.model.UserCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCaptchaRepository extends JpaRepository<UserCaptcha, Integer> {
}
