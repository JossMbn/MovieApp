package com.jmabilon.mymovietheater.data.datasource.remote.reponse

import com.google.gson.annotations.SerializedName

data class TrendingDTO(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<TrendingResultsDTO>? = null
)

data class TrendingResultsDTO(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("adult")
    val adult: String? = null,

    @SerializedName("backdrop_path")
    val backDropPath: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("media_type")
    val mediaType: String? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,

    @SerializedName("popularity")
    val popularity: Float? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Float? = null,

    @SerializedName("vote_count")
    val voteCount: Float? = null,
)
