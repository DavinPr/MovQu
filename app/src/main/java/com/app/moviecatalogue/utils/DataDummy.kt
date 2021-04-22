package com.app.moviecatalogue.utils

import com.app.moviecatalogue.data.Movie
import com.app.moviecatalogue.data.TvShow

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
                399566,
                113,
                listOf(
                    "Action",
                    "Science Fiction"
                ),
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "One Will Fall"
            )
        )

        movie.add(
            Movie(
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373,
                242,
                listOf(
                    "Action",
                    "Adventure",
                    "Fantasy",
                    "Science Fiction"
                ),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                ""
            )
        )

        movie.add(
            Movie(
                "Chaos Walking",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "/5NxjLfs7Bi07bfZCRl9CCnUw7AA.jpg",
                "2021-02-24",
                7.5,
                412656,
                109,
                listOf(
                    "Science Fiction",
                    "Action",
                    "Adventure",
                    "Thriller"
                ),
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "Chaos Walking"
            )
        )
        movie.add(
            Movie(
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg",
                "2021-03-03",
                8.3,
                527774,
                107,
                listOf(
                    "Animation",
                    "Adventure",
                    "Fantasy",
                    "Family",
                    "Action"
                ),
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "A quest to save her world."
            )
        )
        movie.add(
            Movie(
                "Tom & Jerry",
                "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                "2021-02-11",
                7.3,
                587807,
                101,
                listOf(
                    "Comedy",
                    "Family",
                    "Animation"
                ),
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "Best of enemies. Worst of friends."
            )
        )
        movie.add(
            Movie(
                "Below Zero",
                "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "/6TPZSJ06OEXeelx1U1VIAt0j9Ry.jpg",
                "2021-01-29",
                6.4,
                587996,
                106,
                listOf(
                    "Action",
                    "Crime",
                    "Thriller"
                ),
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                "Escape is not the exit."
            )
        )
        movie.add(
            Movie(
                "Cherry",
                "/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                "/uQtqiAu2bBlokqjlURVLEha6zoi.jpg",
                "2021-02-26",
                7.6,
                544401,
                140,
                listOf(
                    "Crime",
                    "Drama"
                ),
                "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
                "Life leaves a mark."
            )
        )
        movie.add(
            Movie(
                "Monster Hunter",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                "2020-12-03",
                7.6,
                458576,
                104,
                listOf(
                    "Fantasy",
                    "Action",
                    "Adventure"
                ),
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "Behind our world, there is another."
            )
        )
        movie.add(
            Movie(
                "Wonder Woman 1984",
                "/8AQRfTuTHeFTddZN4IUAqprN8Od.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "2020-12-16",
                6.8,
                464052,
                151,
                listOf(
                    "Fantasy",
                    "Action",
                    "Adventure"
                ),
                "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                "A new era of wonder begins."
            )
        )
        movie.add(
            Movie(
                "Sentinelle",
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "/gzJnMEMkHowkUndn9gCr8ghQPzN.jpg",
                "2021-03-05",
                6.1,
                793723,
                151,
                listOf(
                    "Thriller",
                    "Action",
                    "Drama"
                ),
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                ""
            )
        )
        return movie
    }

    fun generateDummyTv(): List<TvShow> {
        val tv = ArrayList<TvShow>()

        tv.add(
            TvShow(
                numberOfEpisodes = 6,
                backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                genres = listOf(
                    "Sci-Fi & Fantasy",
                    "Action & Adventure",
                    "Drama",
                    "War & Politics"
                ),
                id = 88396,
                numberOfSeasons = 1,
                firstAirDate = "2021-03-19",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                voteAverage = 7.8,
                name = "The Falcon and the Winter Soldier",
                tagline = "Honor the shield.",
                episodeRunTime = listOf(50),
                lastAirDate = "2021-04-16"
            )
        )

        tv.add(
            TvShow(
                numberOfEpisodes = 71,
                backdropPath = "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                genres = listOf(
                    "Drama"
                ),
                id = 71712,
                numberOfSeasons = 4,
                firstAirDate = "2017-09-25",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                voteAverage = 8.6,
                name = "The Good Doctor",
                tagline = "His mind is a mystery, his methods are a miracle.",
                episodeRunTime = listOf(43),
                lastAirDate = "2021-04-19"
            )
        )

        tv.add(
            TvShow(
                numberOfEpisodes = 142,
                backdropPath = "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                genres = listOf(
                    "Drama",
                    "Sci-Fi & Fantasy"
                ),
                id = 60735,
                numberOfSeasons = 7,
                firstAirDate = "2014-10-07",
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                voteAverage = 7.7,
                name = "The Flash",
                tagline = "The fastest man alive.",
                episodeRunTime = listOf(44),
                lastAirDate = "2021-04-13"
            )
        )

        tv.add(
            TvShow(
                numberOfEpisodes = 87,
                backdropPath = "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                genres = listOf(
                    "Drama",
                    "Sci-Fi & Fantasy"
                ),
                id = 69050,
                numberOfSeasons = 5,
                firstAirDate = "2017-01-26",
                overview = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                posterPath = "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                voteAverage = 7.7,
                name = "Riverdale",
                tagline = "Small town. Big secrets.",
                episodeRunTime = listOf(44),
                lastAirDate = "2021-03-31"
            )
        )

        tv.add(
            TvShow(
                numberOfEpisodes = 376,
                backdropPath = "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                genres = listOf(
                    "Drama"
                ),
                id = 1416,
                numberOfSeasons = 17,
                firstAirDate = "2005-03-27",
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                posterPath = "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                voteAverage = 8.2,
                name = "Grey's Anatomy",
                tagline = "The life you save may be your own.",
                episodeRunTime = listOf(43),
                lastAirDate = "2021-04-15"
            )
        )
        tv.add(
            TvShow(
                numberOfEpisodes = 8,
                backdropPath = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                genres = listOf(
                    "Animation",
                    "Action & Adventure",
                    "Drama"
                ),
                id = 95557,
                numberOfSeasons = 1,
                firstAirDate = "2021-03-26",
                overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                posterPath = "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                voteAverage = 8.9,
                name = "Invincible",
                tagline = "",
                episodeRunTime = listOf(45),
                lastAirDate = "2021-04-16"
            )
        )
        tv.add(
            TvShow(
                numberOfEpisodes = 11,
                backdropPath = "/pLG4ihU1d2XkQbASQDjsFu9U7d9.jpg",
                genres = listOf(
                    "Drama",
                    "Crime",
                    "Mystery"
                ),
                id = 120168,
                numberOfSeasons = 2,
                firstAirDate = "2021-03-24",
                overview = "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                posterPath = "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                voteAverage = 7.8,
                name = "Who Killed Sara?",
                tagline = "",
                episodeRunTime = listOf(40),
                lastAirDate = "2021-03-24"
            )
        )
        tv.add(
            TvShow(
                numberOfEpisodes = 83,
                backdropPath = "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                genres = listOf(
                    "Crime",
                    "Sci-Fi & Fantasy"
                ),
                id = 63174,
                numberOfSeasons = 5,
                firstAirDate = "2016-01-25",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                voteAverage = 8.5,
                name = "Who Killed Sara?",
                tagline = "It's good to be bad.",
                episodeRunTime = listOf(45),
                lastAirDate = "2020-08-21"
            )
        )
        tv.add(
            TvShow(
                numberOfEpisodes = 154,
                backdropPath = "/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                genres = listOf(
                    "Action & Adventure",
                    "Drama",
                    "Sci-Fi & Fantasy"
                ),
                id = 1402,
                numberOfSeasons = 11,
                firstAirDate = "2010-10-31",
                overview = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                posterPath = "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                voteAverage = 8.1,
                name = "The Walking Dead",
                tagline = "Fight the dead. Fear the living.",
                episodeRunTime = listOf(42, 60, 45),
                lastAirDate = "2021-04-04"
            )
        )
        tv.add(
            TvShow(
                numberOfEpisodes = 7,
                backdropPath = "/TXaR5xmz2ohHgnpf0YPWtZPlCO.jpg",
                genres = listOf(
                    "Action & Adventure",
                    "Sci-Fi & Fantasy"
                ),
                id = 95057,
                numberOfSeasons = 1,
                firstAirDate = "2021-02-23",
                overview = "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                posterPath = "/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                voteAverage = 8.3,
                name = "Superman & Lois",
                tagline = "Saving the world starts at home.",
                episodeRunTime = listOf(64, 44),
                lastAirDate = "2021-03-23"
            )
        )
        return tv
    }
}