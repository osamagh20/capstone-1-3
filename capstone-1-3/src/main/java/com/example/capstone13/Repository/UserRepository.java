package com.example.capstone13.Repository;

import com.example.capstone13.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
