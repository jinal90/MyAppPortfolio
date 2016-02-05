package com.tcs.nanodegree.myappportfolio.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.adapter.ReviewAdapter;
import com.tcs.nanodegree.myappportfolio.adapter.TrailerAdapter;
import com.tcs.nanodegree.myappportfolio.bean.Movie;
import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.bean.Review;
import com.tcs.nanodegree.myappportfolio.bean.Trailer;
import com.tcs.nanodegree.myappportfolio.intefaces.IApiMethods;
import com.tcs.nanodegree.myappportfolio.util.Utility;
import com.tcs.nanodegree.myappportfolio.util.WrappingLinearLayoutManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MovieDetailsFragment extends Fragment implements View.OnClickListener {

    private Result movieObj;
    private ImageView imgPoster;
    private TextView tvOverview, tvReleaseDate, tvLanguage, tvTitle;
    private RatingBar movieRating;
    private Trailer movieTrailerObj;
    private Review movieReviewObj;
    private RecyclerView trailerRecyclerView, reviewRecyclerView;
    private TrailerAdapter trailerAdapter;
    private FloatingActionButton fabFavorite;
    private ReviewAdapter reviewAdapter;
    private boolean isFav = false;
    private Movie favMovies = null;


    public static MovieDetailsFragment newInstance(Result resultObj) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("MovieObj", resultObj);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            movieObj = (Result) getArguments().getParcelable(getResources().getString(R.string.movie_obj));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        setupUI(view);

        if (savedInstanceState != null) {
            movieObj = (Result) savedInstanceState.get(getResources().getString(R.string.saved_result_object));
            movieReviewObj = (Review) savedInstanceState.get(getResources().getString(R.string.saved_review_object));
            movieTrailerObj = (Trailer) savedInstanceState.get(getResources().getString(R.string.saved_trailer_object));
        }

        if (movieReviewObj == null) {
            String reviewJson = Utility.getSavedStringDataFromPref(
                    getActivity()
                    , getString(R.string.pref_review_object)
                            + movieObj.getId());
            if (Utility.notEmpty(reviewJson))
                movieReviewObj = (Review) Utility.getObjFromJsonString(reviewJson, Review.class);
        }

        if (movieTrailerObj == null) {
            String trailerJson = Utility.getSavedStringDataFromPref(
                    getActivity()
                    , getString(R.string.pref_trailer_object)
                            + movieObj.getId());
            if (Utility.notEmpty(trailerJson))
                movieTrailerObj = (Trailer) Utility.getObjFromJsonString(trailerJson, Trailer.class);
        }

        if (movieObj != null) {
            fillData();
        }

        if (movieTrailerObj != null) {
            trailerAdapter = new TrailerAdapter(getActivity(), movieTrailerObj.getResults());
            trailerRecyclerView.setAdapter(trailerAdapter);
            trailerRecyclerView.setVisibility(View.VISIBLE);
        } else {
            fetchMovieTrailer();
        }

        if (movieReviewObj != null) {
            reviewAdapter = new ReviewAdapter(getActivity(), movieReviewObj.getResults(), movieObj.getTitle());
            reviewRecyclerView.setAdapter(reviewAdapter);
            reviewRecyclerView.setVisibility(View.VISIBLE);
        } else {

            fetchMovieReview();

        }


        return view;
    }

    private void setupUI(View view) {

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(movieObj.getTitle());

        imgPoster = (ImageView) view.findViewById(R.id.img_movie_poster);
        Picasso.with(getActivity())
                .load(getResources().getString(R.string.TMDB_detail_image_url) + movieObj.getPosterPath())
                .placeholder(getResources().getDrawable(R.drawable.movie_default))
                .error(getResources().getDrawable(R.drawable.movie_default))
                .into(imgPoster);

        tvOverview = (TextView) view.findViewById(R.id.txtOverview);

        movieRating = (RatingBar) view.findViewById(R.id.movieRating);
        movieRating.setIsIndicator(true);

        tvReleaseDate = (TextView) view.findViewById(R.id.txtReleaseDateValue);
        tvLanguage = (TextView) view.findViewById(R.id.txtLanguageValue);

        WrappingLinearLayoutManager trailerLayoutManager = new WrappingLinearLayoutManager(getActivity());
        trailerRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_trailers);
        trailerRecyclerView.setNestedScrollingEnabled(false);
        trailerRecyclerView.setHasFixedSize(false);
        trailerRecyclerView.setLayoutManager(trailerLayoutManager);

        WrappingLinearLayoutManager reviewLayoutManager = new WrappingLinearLayoutManager(getActivity());
        reviewRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_review);
        reviewRecyclerView.setNestedScrollingEnabled(false);
        reviewRecyclerView.setHasFixedSize(false);
        reviewRecyclerView.setLayoutManager(reviewLayoutManager);

        fabFavorite = (FloatingActionButton) view.findViewById(R.id.fabFavorite);

        checkAlreadyFavorite();

        if (isFav)
            fabFavorite.setImageResource(R.drawable.ic_favorite_red);
        else
            fabFavorite.setImageResource(R.drawable.ic_favorite_white);

        fabFavorite.setOnClickListener(this);
    }


    public void fillData() {
        float rating = (float) ((5 * movieObj.getVoteAverage()) / 10);
        movieRating.setRating(rating);
        tvReleaseDate.setText(movieObj.getReleaseDate());
        Locale current = new Locale(movieObj.getOriginalLanguage());
        tvLanguage.setText(current.getDisplayLanguage());
        tvOverview.setText(movieObj.getOverview());
    }

    public void fetchMovieReview() {
        final IApiMethods methods;

        try {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(getResources().getString(R.string.TMDB_base_url)
                            + "/movie/" + movieObj.getId())
                    .build();

            methods = restAdapter.create(IApiMethods.class);

            Callback callback = new Callback() {
                @Override
                public void success(Object o, Response response) {

                    movieReviewObj = (Review) o;
                    reviewAdapter = new ReviewAdapter(getActivity(), movieReviewObj.getResults(), movieObj.getTitle());
                    reviewRecyclerView.setAdapter(reviewAdapter);
                    reviewRecyclerView.setVisibility(View.VISIBLE);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    reviewRecyclerView.setVisibility(View.GONE);
                }
            };

            methods.getMovieReview(getResources().getString(R.string.TMDB_ApiKey),
                    callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchMovieTrailer() {
        final IApiMethods methods;

        try {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(getResources().getString(R.string.TMDB_base_url)
                            + "/movie/" + movieObj.getId())
                    .build();

            methods = restAdapter.create(IApiMethods.class);

            Callback callback = new Callback() {
                @Override
                public void success(Object o, Response response) {

                    movieTrailerObj = (Trailer) o;
                    trailerAdapter = new TrailerAdapter(getActivity(), movieTrailerObj.getResults());
                    trailerRecyclerView.setAdapter(trailerAdapter);
                    trailerRecyclerView.setVisibility(View.VISIBLE);
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                    trailerRecyclerView.setVisibility(View.GONE);


                }
            };

            methods.getMovieTrailer(getResources().getString(R.string.TMDB_ApiKey),
                    callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(getResources().getString(R.string.saved_trailer_object), movieTrailerObj);
        outState.putParcelable(getResources().getString(R.string.saved_result_object), movieObj);
        outState.putParcelable(getResources().getString(R.string.saved_review_object), movieReviewObj);
    }


    public void checkAlreadyFavorite() {

        String favMoviesJson = Utility.getSavedStringDataFromPref(
                getActivity()
                , getString(R.string.pref_movie_object));

        if (Utility.notEmpty(favMoviesJson))
            favMovies = (Movie) Utility.getObjFromJsonString(favMoviesJson, Movie.class);

        if (favMovies != null && favMovies.getResults() != null)
            for (Result r : favMovies.getResults()) {
                if (r.getId() == movieObj.getId()) {
                    isFav = true;
                    break;
                }
            }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (isFav && favMovies != null) {

            isFav = false;
            fabFavorite.setImageResource(R.drawable.ic_favorite_white);

            for (Iterator<Result> it = favMovies.getResults().iterator(); it.hasNext(); ) {
                Result r = it.next();
                if (r.getId() == movieObj.getId()) {
                    it.remove();
                    break;
                }
            }

            Utility.deleteSavedStringDatafromPref(getActivity()
                    , getString(R.string.pref_review_object)
                    + movieObj.getId());
            Utility.deleteSavedStringDatafromPref(getActivity()
                    , getString(R.string.pref_trailer_object)
                    + movieObj.getId());

        } else {

            isFav = true;
            fabFavorite.setImageResource(R.drawable.ic_favorite_red);

            Toast.makeText(getActivity()
                    , movieObj.getTitle() + " " + getString(R.string.favorite_added)
                    , Toast.LENGTH_SHORT).show();

            String reviewJson = Utility.getJsonStringFromObj(movieReviewObj);
            String trailerJson = Utility.getJsonStringFromObj(movieTrailerObj);

            if (favMovies == null || favMovies.getResults() == null) {
                favMovies = new Movie();
                favMovies.setPage(1l);
                ArrayList<Result> resultList = new ArrayList<Result>();
                favMovies.setResults(resultList);
            }

            favMovies.getResults().add(movieObj);

            Utility.saveStringDataInPref(getActivity()
                    , getString(R.string.pref_review_object) + movieObj.getId()
                    , reviewJson);

            Utility.saveStringDataInPref(getActivity()
                    , getString(R.string.pref_trailer_object) + movieObj.getId()
                    , trailerJson);

        }

        String favMoviesJson = Utility.getJsonStringFromObj(favMovies);

        Utility.saveStringDataInPref(getActivity()
                , getString(R.string.pref_movie_object)
                , favMoviesJson);

    }
}
