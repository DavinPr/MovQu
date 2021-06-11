package com.moviecatalogue.favorite

import com.moviecatalogue.core.domain.usecase.model.Favorite

object DataDummy {
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
}