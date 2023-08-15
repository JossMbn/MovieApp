package com.jmabilon.mymovietheater.data.mapper

import com.jmabilon.mymovietheater.data.datasource.remote.reponse.TrendingResultsDTO
import com.jmabilon.mymovietheater.domain.model.Trending

class TrendingMapper : DomainMapper<TrendingResultsDTO, Trending> {

    override fun toDomain(value: TrendingResultsDTO): Trending {
        return Trending(
            id = value.id,
            adult = value.adult,
            backDropPath = value.backDropPath,
            title = value.title,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            posterPath = value.posterPath,
            mediaType = value.mediaType,
            genreId = value.genreIds,
            popularity = value.popularity,
            releaseDate = value.releaseDate,
            video = value.video,
            voteAverage = value.voteAverage,
            voteCount = value.voteCount,
        )
    }
}