# PopularMovies

Android app that displays top rated and popular movies. 

# Overview

The app launchs a movie grid displaying movie poster thumbnails.  These can be sorted by the top rated or most popular. Sorting is available by use of two tabs or swiping. Movies are fetched from TheMovieAPI. On clicking a movie poster the app opens a movie detail view displaying more details about the movie, such as the title, the synopsis, the rating and the release date.

# Usage

- Download and then import the project with Android Studio.
- Replace all text entries matching %API_KEY% by your API key in the class RequestInterface.java, which is located under app\src\main\java\coexample\android\popularmovies\network.
- Run the app with emulator or android device running Nexus 5 API 26 or above.

# Aknowledgements

This app was developed to meet specification of a project assigment for the [Android Developer Nanodegree Program](https://eu.udacity.com/course/android-developer-nanodegree-by-google--nd801) covering three main tasks (1) Design UI layouts for multiple Activities; (2) Start these Activities via Intent; (3) Display movies data fetched TheMovieDB API.
