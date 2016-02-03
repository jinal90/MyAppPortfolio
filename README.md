# MyAppPortfolio
This project is the parent project for Android Nanodegree.
Buttons on landing screen will navigate to respective sub apps inside project.
Button "Popular Movies: Stage1" will navigate to this stage 1 project.

#### ScreenShots

![Movie List](/screenshots/landing.png?raw=true "Movie List")
![Movie Details](/screenshots/movie_detail.png?raw=true "Movie Details")

#### Tech

This App uses a number of open source projects to work properly:

* [RETROFIT] - Retrofit 2.0 by Square
* [Butterknife] - To bind the views with annotations.
* [Picasso] - Picasso allows for hassle-free image loading in your application—often in one line of code!
* [Google GSON] - To convert Json to java pojos or vice versa.
* [MOVIE DB API] - To Fetch Popular Movies and its Description, Reviews, Ratings etc.
* Material Design Support, Color Palette, RecyclerView Cards and CoordinatorLayout.


### Basic Functionality
* Get the list of popular Movies
* Sort them based on Voting and Popularity
* See the Synopsis, Rating of Movie with detailed View

### Build and Run Requirements

#### Generate API Key for Movie DB API

To fetch popular movies, you will use the API from themoviedb.org.
If you don’t already have an account, you will need to create one in order to request an API Key.
URL : https://www.themoviedb.org/account/signup


* Oracle JDK 1.7
* Gradle 2.8
* Support Android 4.1 and Above (API 16)




### Tools used to develop
* Android Studio 1.4

[RETROFIT]: <http://square.github.io/retrofit/>
[Google GSON]: <https://github.com/google/gson>
[MOVIE DB API]: <https://www.themoviedb.org/>
[Picasso]: <http://square.github.io/picasso>
[Butterknife]: <http://jakewharton.github.io/butterknife/>
