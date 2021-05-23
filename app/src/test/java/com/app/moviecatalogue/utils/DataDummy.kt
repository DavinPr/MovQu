package com.app.moviecatalogue.utils

import com.moviecatalogue.core.domain.usecase.model.*

object DataDummy {

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

    fun generateDummyFavorite(): List<Favorite> {
        val movie = ArrayList<Favorite>()

        movie.add(
            Favorite(
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "movie",
                399566
            )
        )

        movie.add(
            Favorite(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "movie",
                791373
            )
        )

        movie.add(
            Favorite(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "movie",
                412656
            )
        )
        return movie
    }

    fun generateDummyDetailMovie(): MovieDetail =
        MovieDetail(
            title = "Godzilla vs Kong",
            backdrop_path = "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
            id = 399566,
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