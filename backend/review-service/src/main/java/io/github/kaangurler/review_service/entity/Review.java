package io.github.kaangurler.review_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(updatable = false)
	private UUID userId;
	@Column(updatable = false)
	private UUID movieId;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String comment;
	@Column(nullable = false)
	private Boolean spoiler;
	private LocalDateTime created;
	private LocalDateTime updated;
}
