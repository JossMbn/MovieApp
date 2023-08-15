package com.jmabilon.mymovietheater.domain.model

data class Trending(
    val id: Int? = null,
    val adult: String? = null,
    val backDropPath: String? = null,
    val title: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val mediaType: String? = null,
    val genreId: List<Int>? = null,
    val popularity: Float? = null,
    val releaseDate: String? = null,
    val video: Boolean = false,
    val voteAverage: Float? = null,
    val voteCount: Float? = null,
)
