package io.github.kaangurler.review_service.repository;

import io.github.kaangurler.review_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

	List<Review> findByUserId(UUID userId);
	List<Review> findByMovieId(UUID movieId);
}
