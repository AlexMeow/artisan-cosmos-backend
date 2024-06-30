package com.example.artisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artisan.model.po.User;
import com.example.artisan.model.po.UserFollowing;

import java.util.List;
import java.util.Optional;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {
    Optional<UserFollowing> findByUserIdAndFollowingId(Long userId, Long followingId);

	List<UserFollowing> findByFollowingId(Long followingId);

	List<UserFollowing> findByUserId(Long userId);

	List<UserFollowing> findByFollowing(User following);

	List<UserFollowing> findByUser(User user);
}