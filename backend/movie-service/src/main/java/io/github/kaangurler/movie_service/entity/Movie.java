package io.github.kaangurler.movie_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true)
	private String title;

	@ElementCollection(targetClass = Category.class)
	@CollectionTable(name = "movie_category", joinColumns = @JoinColumn(name = "movie_id"))
	@Enumerated(EnumType.STRING)
	private List<Category> categories;

	@ElementCollection
	@CollectionTable(name = "movie_casts", joinColumns = @JoinColumn(name = "movie_id"))
	private List<UUID> casts;
	private Integer releaseYear;
	private Integer duration;
	private double averageRating;
	@Lob
	@Column(columnDefinition = "TEXT")
	private String storyline;
	private byte[] image;
	private LocalDateTime created;
	private LocalDateTime updated;
}
