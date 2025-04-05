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
	SCI_FI("Sci-Fi"),
	THRILLER("Thriller");

	private final String title;

	public static List<Category> getAllCategories() {
		return Arrays.stream(Category.values()).toList();
	}

	public static List<String> getTitles(List<Category> categories) {
		return categories.stream().map(Category::getTitle).toList();
	}

	public static Category getByTitle(String title) {
		return switch (title) {
			case "action" -> Category.ACTION;
			case "adventure" -> Category.ADVENTURE;
			case "animation" -> Category.ANIMATION;
			case "biography" -> Category.BIOGRAPHY;
			case "comedy" -> Category.COMEDY;
			case "crime" -> Category.CRIME;
			case "drama" -> Category.DRAMA;
			case "documentary" -> Category.DOCUMENTARY;
			case "family" -> Category.FAMILY;
			case "fantasy" -> Category.FANTASY;
			case "history" -> Category.HISTORY;
			case "horror" -> Category.HORROR;
			case "musical" -> Category.MUSICAL;
			case "mystery" -> Category.MYSTERY;
			case "sci-fi" -> Category.SCI_FI;
			case "thriller" -> Category.THRILLER;
			default -> throw new IllegalStateException("Unexpected value: " + title);
		};
	}
}
