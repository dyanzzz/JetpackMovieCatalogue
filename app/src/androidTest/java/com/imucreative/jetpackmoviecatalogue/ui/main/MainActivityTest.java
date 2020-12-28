package com.imucreative.jetpackmoviecatalogue.ui.main;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.rules.*;
import androidx.test.rule.ActivityTestRule;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class MainActivityTest {

    private List<MovieEntity> dummyMovie = DataDummy.getListData("movie");
    private List<MovieEntity> dummyTvShow = DataDummy.getListData("tv");

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void loadMovie() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed()));
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie() {
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyMovie.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(String.format("Date Release Movie %s", dummyMovie.get(0).getDate()))));
        onView(allOf(withId(R.id.text_description), isDisplayed()));
        onView(allOf(withId(R.id.text_description), withText(dummyMovie.get(0).getDescription())));
    }

    @Test
    public void loadTvShow() {
        onView(allOf(isDisplayed(), withText("TV Show"))).perform(click());
        onView(allOf(withId(R.id.rv_movie), isDisplayed()));
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.scrollToPosition(dummyTvShow.size()));
    }

    @Test
    public void loadDetailTvShow() {
        onView(allOf(isDisplayed(), withText("TV Show"))).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyTvShow.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(String.format("Date Release TV Show %s", dummyTvShow.get(0).getDate()))));
        onView(allOf(withId(R.id.text_description), isDisplayed()));
        onView(allOf(withId(R.id.text_description), withText(dummyTvShow.get(0).getDescription())));
    }

}