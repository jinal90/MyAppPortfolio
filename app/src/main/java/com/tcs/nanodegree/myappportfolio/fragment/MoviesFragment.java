package com.tcs.nanodegree.myappportfolio.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.adapter.GridAdapter;
import com.tcs.nanodegree.myappportfolio.bean.Movie;
import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.intefaces.IApiMethods;
import com.tcs.nanodegree.myappportfolio.util.Utility;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MoviesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View view;
    private GridAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private ProgressBar progDialog, smallLoading;
    private int sortByRating = 1, sortByPopularity = 0, selectedSorting = 0, favoriteMovies = 2;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount;
    private Integer pageCount = 1;
    private Movie movie;
    private TextView errorText;
    private int currentSelectedSort = 0;

    public void setPageCount(Integer p) {
        this.pageCount = p;
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    public MoviesFragment() {
        // Required empty public constructor
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movies, container, false);

        setUI(view);

        if (savedInstanceState != null) {
            currentSelectedSort = savedInstanceState.getInt(getResources().getString(R.string.current_sort_option));
            movie = (Movie) savedInstanceState.get(getResources().getString(R.string.saved_movie_object));
            pageCount = savedInstanceState.getInt(getResources().getString(R.string.saved_page_count));
        }

        if (movie != null && movie.getResults() != null && movie.getResults().size() > 0) {
            mAdapter = new GridAdapter(getActivity(), movie.getResults());
            mRecyclerView.setAdapter(mAdapter);
            loading = true;
            progDialog.setVisibility(View.GONE);
            smallLoading.setVisibility(View.GONE);
            errorText.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            fetchMovies(currentSelectedSort);
        }

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_popular_sort) {
            if (currentSelectedSort != sortByPopularity) {
                setPageCount(1);
                fetchMovies(sortByPopularity);
                currentSelectedSort = 0;
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.popular_already_sorted),
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_rating_sort) {
            if (currentSelectedSort != sortByRating) {
                setPageCount(1);
                fetchMovies(sortByRating);
                currentSelectedSort = sortByRating;
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.highest_rating_already_sorted),
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_favorite_movies) {
            if (currentSelectedSort != favoriteMovies) {
                setPageCount(1);
                fetchMovies(favoriteMovies);
                currentSelectedSort = favoriteMovies;
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.favorite_already_sorted),
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_the_movies_screen, menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(getResources().getString(R.string.current_sort_option), currentSelectedSort);
        outState.putParcelable(getResources().getString(R.string.saved_movie_object), movie);
        outState.putInt(getResources().getString(R.string.saved_page_count), pageCount);
    }

    private void setUI(View view) {
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_movies);
        mRecyclerView.setHasFixedSize(true);

        Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        // The number of Columns

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180)
            mLayoutManager = new GridLayoutManager(getActivity(), 3);
        else
            mLayoutManager = new GridLayoutManager(getActivity(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        progDialog = (ProgressBar) view.findViewById(R.id.loadingDialog);
        smallLoading = (ProgressBar) view.findViewById(R.id.smallLoading);

        errorText = (TextView) view.findViewById(R.id.txtError);
        errorText.setVisibility(View.GONE);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= movie.getResults().size()) {
                            loading = false;

                            fetchMovies(selectedSorting);
                        }
                    }
                }

            }
        });
    }

    /**
     * @param sortType
     */
    public void fetchMovies(int sortType) {
        selectedSorting = sortType;
        IApiMethods methods;

        try {

            if (sortType == favoriteMovies) {
                progDialog.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                String favMoviesJson = Utility.getSavedStringDataFromPref(
                        getActivity()
                        , getString(R.string.pref_movie_object));
                if (Utility.notEmpty(favMoviesJson))
                    movie = (Movie) Utility.getObjFromJsonString(favMoviesJson, Movie.class);

                if (movie != null) {
                    mAdapter = new GridAdapter(getActivity(), movie.getResults());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    progDialog.setVisibility(View.GONE);
                    smallLoading.setVisibility(View.GONE);
                    errorText.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    progDialog.setVisibility(View.GONE);
                    smallLoading.setVisibility(View.GONE);
                    errorText.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }

            } else {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(getResources().getString(R.string.TMDB_base_url))
                        .build();

                methods = restAdapter.create(IApiMethods.class);

                if (pageCount == 1) {
                    progDialog.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    smallLoading.setVisibility(View.VISIBLE);
                }

                Callback callback = new Callback() {
                    @Override
                    public void success(Object o, Response response) {

                        if (pageCount == 1) {
                            movie = (Movie) o;
                            mAdapter = new GridAdapter(getActivity(), movie.getResults());
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        } else {
                            Movie nextMovieObj = (Movie) o;
                            List<Result> oldResult = movie.getResults();
                            oldResult.addAll(nextMovieObj.getResults());
                            movie.setResults(oldResult);
                            mAdapter.notifyDataSetChanged();
                            loading = true;
                        }
                        progDialog.setVisibility(View.GONE);
                        smallLoading.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        pageCount++;

                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        progDialog.setVisibility(View.GONE);
                        smallLoading.setVisibility(View.GONE);
                        errorText.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }
                };

                if (sortType == sortByRating) {
                    methods.getMovie(getResources().getString(R.string.TMDB_ApiKey),
                            pageCount.toString(),
                            getResources().getString(R.string.sort_by_rating),
                            callback);
                } else {
                    methods.getMovie(getResources().getString(R.string.TMDB_ApiKey),
                            pageCount.toString(),
                            getResources().getString(R.string.sort_by_popularity),
                            callback);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mLayoutManager.setSpanCount(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3);

    }
}
