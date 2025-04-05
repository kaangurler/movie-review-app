package io.github.kaangurler.movie_service.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortDirection {

	ASC,
	DESC;

	public static SortDirection getByName(String name) {
		return name.equals("desc") ? SortDirection.DESC : SortDirection.ASC;
	}

	public String getNameLowerCase() {
		return name().toUpperCase();
	}
}
