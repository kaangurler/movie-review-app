package io.github.kaangurler.movie_service.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum Category {
	ACTION("Action"),
	ADVENTURE("Adventure"),
	ANIMATION("Animation"),
	BIOGRAPHY("Biography"),
	COMEDY("Comedy"),
	CRIME("Crime"),
	DRAMA("Drama"),
	DOCUMENTARY("Documentary"),
	FAMILY("Family"),
	FANTASY("Fantasy"),
	HISTORY("History"),
	HORROR("Horror"),
	MUSICAL("Musical"),
	MYSTERY("Mystery"),
	ROMANCE("Romance"),
	SCIFI("SciFi"),
	THRILLER("Thriller");

	private final String title;

	public static List<Category> getAllTitles() {
		return Arrays.stream(Category.values()).toList();
	}
}
