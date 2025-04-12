package io.github.kaangurler.review_service.repository;

import io.github.kaangurler.review_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
