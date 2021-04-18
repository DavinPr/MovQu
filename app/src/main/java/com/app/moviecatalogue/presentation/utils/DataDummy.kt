package com.app.moviecatalogue.presentation.utils

import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.MovieDetail
import com.app.moviecatalogue.core.domain.usecase.model.TvDetail
import com.app.moviecatalogue.core.domain.usecase.model.TvShow

object DataDummy {

    fun generateEntityDummyDiscoverMovie(): List<DiscoverMovieEntity> {
        val movie = ArrayList<DiscoverMovieEntity>()

        movie.add(
            DiscoverMovieEntity(
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                8.3,
                399566
            )
        )

        movie.add(
            DiscoverMovieEntity(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373
            )
        )

        movie.add(
            DiscoverMovieEntity(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "2021-02-24",
                7.5,
                412656
            )
        )
        return movie
    }

    fun generateEntityDummyNowPlayingMovie(): List<NowPlayingMovieEntity> {
        val movie = ArrayList<NowPlayingMovieEntity>()

        movie.add(
            NowPlayingMovieEntity(
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                8.3,
                399566
            )
        )

        movie.add(
            NowPlayingMovieEntity(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373
            )
        )

        movie.add(
            NowPlayingMovieEntity(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "2021-02-24",
                7.5,
                412656
            )
        )
        return movie
    }

    fun generateEntityDummyUpcomingMovie(): List<UpcomingMovieEntity> {
        val movie = ArrayList<UpcomingMovieEntity>()

        movie.add(
            UpcomingMovieEntity(
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                8.3,
                399566
            )
        )

        movie.add(
            UpcomingMovieEntity(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373
            )
        )

        movie.add(
            UpcomingMovieEntity(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "2021-02-24",
                7.5,
                412656
            )
        )
        return movie
    }

    fun generateEntityDummyDiscoverTv(): List<DiscoverTvShowEntity> {
        val tv = ArrayList<DiscoverTvShowEntity>()

        tv.add(
            DiscoverTvShowEntity(
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                voteAverage = 7.8,
                firstAirDate = "2021-03-19",
                name = "The Falcon and the Winter Soldier",
                id = 88396
            )
        )

        tv.add(
            DiscoverTvShowEntity(
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                voteAverage = 8.6,
                firstAirDate = "2017-09-25",
                name = "The Good Doctor",
                id = 71712
            )
        )

        tv.add(
            DiscoverTvShowEntity(
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                voteAverage = 7.7,
                firstAirDate = "2014-10-07",
                name = "The Flash",
                id = 60735
            )
        )

        return tv
    }

    fun generateEntityDummyAiringTodayTv(): List<AiringTodayTvShowEntity> {
        val tv = ArrayList<AiringTodayTvShowEntity>()

        tv.add(
            AiringTodayTvShowEntity(
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                voteAverage = 7.8,
                firstAirDate = "2021-03-19",
                name = "The Falcon and the Winter Soldier",
                id = 88396
            )
        )

        tv.add(
            AiringTodayTvShowEntity(
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                voteAverage = 8.6,
                firstAirDate = "2017-09-25",
                name = "The Good Doctor",
                id = 71712
            )
        )

        tv.add(
            AiringTodayTvShowEntity(
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                voteAverage = 7.7,
                firstAirDate = "2014-10-07",
                name = "The Flash",
                id = 60735
            )
        )

        return tv
    }

    fun generateEntityDummyOnTheAirTv(): List<OnTheAirTvShowEntity> {
        val tv = ArrayList<OnTheAirTvShowEntity>()

        tv.add(
            OnTheAirTvShowEntity(
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                voteAverage = 7.8,
                firstAirDate = "2021-03-19",
                name = "The Falcon and the Winter Soldier",
                id = 88396
            )
        )

        tv.add(
            OnTheAirTvShowEntity(
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                voteAverage = 8.6,
                firstAirDate = "2017-09-25",
                name = "The Good Doctor",
                id = 71712
            )
        )

        tv.add(
            OnTheAirTvShowEntity(
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                voteAverage = 7.7,
                firstAirDate = "2014-10-07",
                name = "The Flash",
                id = 60735
            )
        )

        return tv
    }

    fun generateDummyMovie(): List<Movie> {
        val movie = ArrayList<Movie>()

        movie.add(
            Movie(
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                8.3,
                399566
            )
        )

        movie.add(
            Movie(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373
            )
        )

        movie.add(
            Movie(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "2021-02-24",
                7.5,
                412656
            )
        )
        return movie
    }

    fun generateDummyTv(): List<TvShow> {
        val tv = ArrayList<TvShow>()

        tv.add(
            TvShow(
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                voteAverage = 7.8,
                firstAirDate = "2021-03-19",
                name = "The Falcon and the Winter Soldier",
                id = 88396
            )
        )

        tv.add(
            TvShow(
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                voteAverage = 8.6,
                firstAirDate = "2017-09-25",
                name = "The Good Doctor",
                id = 71712
            )
        )

        tv.add(
            TvShow(
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                voteAverage = 7.7,
                firstAirDate = "2014-10-07",
                name = "The Flash",
                id = 60735
            )
        )

        return tv
    }

    fun generateDummyDetailMovie(): MovieDetail =
        MovieDetail(
            title = "Godzilla vs Kong",
            backdrop_path = "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
            id = 412656,
            poster_path = "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
            release_date = "2021-02-24",
            vote_average = 7.5
        )

    fun generateDummyDetailTv(): TvDetail =
        TvDetail(
            posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            voteAverage = 7.8,
            firstAirDate = "2021-03-19",
            name = "The Falcon and the Winter Soldier",
            id = 88396
        )
}