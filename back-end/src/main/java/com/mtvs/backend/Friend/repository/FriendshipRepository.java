package com.mtvs.backend.Friend.repository;

import com.mtvs.backend.Friend.domain.Friendship;
import com.mtvs.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRequesterAndIsAccepted(User requester, boolean isAccepted);
    List<Friendship> findByReceiverAndIsAccepted(User requester, boolean isAccepted);

    @Query("SELECT f FROM Friendship f WHERE f.isAccepted = :isAccepted")
    List<Friendship> findAllFriendship(@Param("isAccepted") Boolean isAccepted);
}
