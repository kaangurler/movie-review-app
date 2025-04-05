package io.github.kaangurler.movie_service.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortType {

	RELEASE,
	TITLE,
	RATING,
	CREATED;

	public static SortType getByName(String name) {
		return switch (name) {
			case "release", "title", "rating", "created" -> SortType.valueOf(name.toUpperCase());
			default -> SortType.TITLE;
		};
	}

	public String getNameLowerCase() {
		return name().toLowerCase();
	}
}
