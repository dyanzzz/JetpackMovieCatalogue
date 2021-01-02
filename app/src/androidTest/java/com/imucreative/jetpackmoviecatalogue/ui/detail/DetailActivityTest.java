package com.imucreative.jetpackmoviecatalogue.ui.detail;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.ui.main.MainActivity;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;
import com.imucreative.jetpackmoviecatalogue.utils.EspressoIdlingResource;

import org.junit.After;
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

public class DetailActivityTest {
    private List<MovieEntity> dummyMovie = DataDummy.getListData("movie");
    private List<MovieEntity> dummyTvShow = DataDummy.getListData("tv");

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup() {
        ActivityScenario.launch(MainActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadDetailMovie() {
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyMovie.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(String.format("Date Release Movie %s", dummyMovie.get(0).getDate()))));
        onView(allOf(withId(R.id.text_voters_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_voters_detail), withText(dummyMovie.get(0).getVoteCount())));
        onView(allOf(withId(R.id.txt_description), isDisplayed()));
        onView(allOf(withId(R.id.txt_description), withText(dummyMovie.get(0).getDescription())));
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()));
    }

    @Test
    public void loadDetailTvShow() {
        onView(allOf(isDisplayed(), withText("TV Show"))).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyTvShow.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(String.format("Date Release TV Show %s", dummyTvShow.get(0).getDate()))));
        onView(allOf(withId(R.id.text_voters_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_voters_detail), withText(dummyMovie.get(0).getVoteCount())));
        onView(allOf(withId(R.id.txt_description), isDisplayed()));
        onView(allOf(withId(R.id.txt_description), withText(dummyMovie.get(0).getDescription())));
        onView(allOf(withId(R.id.img_poster), isDisplayed()));
        onView(allOf(withId(R.id.img_backdrop), isDisplayed()));
    }
}