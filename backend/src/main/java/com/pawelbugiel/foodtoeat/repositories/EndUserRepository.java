package com.pawelbugiel.foodtoeat.repositories;

import com.pawelbugiel.foodtoeat.models.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {
    Optional<EndUser> findByEmail(String email);
}