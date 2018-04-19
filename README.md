# PopularMovies

Android app that displays top rated and popular movies. 
It allows users to save favourite movies, play trailers and read reviews.

# Overview

The app launchs a movie grid displaying movie poster thumbnails.  These can be sorted by the top rated or most popular. Sorting is available by use of two tabs or swiping. A third tab is also available, on which the user's favourite movies are displayed. Movies data are fetched from the API [TheMovieDB](https://www.themoviedb.org/). On clicking a movie poster the app opens a movie detail view displaying more details about the movie, such as the title, the synopsis, the rating and the release date.In addition, it displays a list of trailers and a list of reviews. The movie detail view allows the user to mark and unmark the current movie as favourite.

# Usage

- Download and then import the project with Android Studio.
- Replace 'API_KEY' by your API key* in the file gradle.properties.java, which is located under the project root.
- Run the app with emulator or android device running Nexus 5 API 26 or above.

# Aknowledgements

This app was developed to meet specification of a project assigment for the [Android Developer Nanodegree Program](https://eu.udacity.com/course/android-developer-nanodegree-by-google--nd801) covering four main tasks: (1) Design UI layouts for multiple Activities; (2) Start these Activities via Intent; (3) Display movies data fetched TheMovieDB API; (4) Support data persitance using internal storage.


# Notes

*In order to get an API key for TheMovieDB API:
- Create an account at https://www.themoviedb.org/account/signup.
- Go to your account settings and click the "API" link on the left sidebar.
- For more details please see the [API FAQ](https://www.themoviedb.org/faq/api).
