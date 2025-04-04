package io.github.kaangurler.movie_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class MovieResponse {
	private UUID id;
	private String title;
	private List<String> categories;
	private List<UUID> casts;
	private Integer releaseYear;
	private Integer duration;
	private String storyline;
	private Double rating;
	private byte[] image;
}
