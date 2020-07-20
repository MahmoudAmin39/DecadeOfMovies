package com.mahmoud.decadeofmovies

import com.mahmoud.decadeofmovies.data.MoviesRepository
import com.mahmoud.decadeofmovies.data.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesRepositoryTest {

    @Test
    fun givenFiveMoviesInList_whenFilterMoviesCalled_onlyMoviesContainTheTermWillBeAdded() {
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie(1, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(
            Movie(
                2,
                "Another title",
                2009,
                3,
                mutableListOf("Action Cast"),
                emptyList()
            )
        )
        moviesList.add(
            Movie(
                3,
                "Another title",
                2009,
                3,
                emptyList(),
                mutableListOf("Action Genre")
            )
        )
        moviesList.add(
            Movie(
                4,
                "Another title",
                2009,
                3,
                mutableListOf("Another Cast"),
                emptyList()
            )
        )
        moviesList.add(
            Movie(
                5,
                "Another title",
                2009,
                3,
                emptyList(),
                mutableListOf("Another genre")
            )
        )

        MoviesRepository.movies = moviesList
        val filteredMovies = MoviesRepository.filterMovies("action")
        // Assert the Size
        assertEquals(3, filteredMovies.size)
        // Assert the expected movies to be added are the ones that were actually added
        assertEquals(1, filteredMovies[0].id)
        assertEquals(2, filteredMovies[1].id)
        assertEquals(3, filteredMovies[2].id)
    }

    @Test
    fun givenFiveMoviesAlreadyInFilteredList_whenFilterMoviesCalled_newMoviesWontAddUp() {
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie(1, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(2, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(3, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(4, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(5, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(6, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(7, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(8, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(9, "Action title", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(10, "Action title", 2010, 3, emptyList(), emptyList()))

        MoviesRepository.movies = moviesList
        val filteredMovies = MoviesRepository.filterMovies("action")
        // Assert the size
        assertEquals(6, filteredMovies.size)
        // Assert the expected movies to be added are the ones that were actually added
        assertEquals(1, filteredMovies[0].id)
        assertEquals(2, filteredMovies[1].id)
        assertEquals(3, filteredMovies[2].id)
        assertEquals(4, filteredMovies[3].id)
        assertEquals(5, filteredMovies[4].id)
        assertEquals(10, filteredMovies[5].id)
    }

    @Test
    fun givenUnSortedMoviesList_WhenSortMoviesCalled_thenListIsSorted() {
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie(1, "", 2011, 2, emptyList(), emptyList()))
        moviesList.add(Movie(2, "", 2015, 1, emptyList(), emptyList()))
        moviesList.add(Movie(3, "", 2013, 3, emptyList(), emptyList()))
        moviesList.add(Movie(4, "", 2010, 2, emptyList(), emptyList()))
        moviesList.add(Movie(5, "", 2009, 1, emptyList(), emptyList()))
        moviesList.add(Movie(6, "", 2015, 3, emptyList(), emptyList()))
        moviesList.add(Movie(7, "", 2010, 3, emptyList(), emptyList()))
        moviesList.add(Movie(8, "", 2011, 3, emptyList(), emptyList()))
        moviesList.add(Movie(9, "", 2009, 3, emptyList(), emptyList()))
        moviesList.add(Movie(10, "", 2013, 5, emptyList(), emptyList()))

        MoviesRepository.sortList(moviesList)

        assertEquals(9, moviesList[0].id)
        assertEquals(5, moviesList[1].id)
        assertEquals(7, moviesList[2].id)
        assertEquals(4, moviesList[3].id)
        assertEquals(8, moviesList[4].id)
        assertEquals(1, moviesList[5].id)
        assertEquals(10, moviesList[6].id)
        assertEquals(3, moviesList[7].id)
        assertEquals(6, moviesList[8].id)
        assertEquals(2, moviesList[9].id)
    }
}