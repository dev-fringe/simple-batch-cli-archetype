package dev.fringe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fringe.model.UserS;

@Repository
public interface UserSRepository extends JpaRepository<UserS, Long> {
}
