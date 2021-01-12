package com.imucreative.jetpackmoviecatalogue.ui.main;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
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
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class MainActivityTest {

    private List<MovieEntity> dummyMovie = DataDummy.getListData("movie");
    private List<MovieEntity> dummyTvShow = DataDummy.getListData("tv");

    @Before
    public void setup() {
        ActivityScenario.launch(MainActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void loadMovie() {
        onView(withId(R.id.nav_movie)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.nav_movie)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favorite_button)).perform(click());
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyMovie.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(dummyMovie.get(0).getDate())));
        onView(allOf(withId(R.id.text_voters_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_voters_detail), withText(dummyMovie.get(0).getVoteCount())));
        onView(allOf(withId(R.id.txt_description), isDisplayed()));
        onView(allOf(withId(R.id.txt_description), withText(dummyMovie.get(0).getDescription())));
        onView(allOf(withId(R.id.text_popularity_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_popularity_detail), withText((int) dummyMovie.get(0).getPopularity())));
        onView(allOf(withId(R.id.text_language_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_language_detail), withText(dummyMovie.get(0).getLanguage())));
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void loadTvShow() {
        onView(withId(R.id.nav_tv)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.scrollToPosition(dummyTvShow.size()));
    }

    @Test
    public void loadDetailTvShow() {
        onView(withId(R.id.nav_tv)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.rv_movie))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favorite_button)).perform(click());
        onView(allOf(withId(R.id.text_title), isDisplayed()));
        onView(allOf(withId(R.id.text_title), withText(dummyTvShow.get(0).getTitle())));
        onView(allOf(withId(R.id.text_date), isDisplayed()));
        onView(allOf(withId(R.id.text_date), withText(dummyTvShow.get(0).getDate())));
        onView(allOf(withId(R.id.text_voters_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_voters_detail), withText(dummyTvShow.get(0).getVoteCount())));
        onView(allOf(withId(R.id.txt_description), isDisplayed()));
        onView(allOf(withId(R.id.txt_description), withText(dummyTvShow.get(0).getDescription())));
        onView(allOf(withId(R.id.text_popularity_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_popularity_detail), withText((int) dummyTvShow.get(0).getPopularity())));
        onView(allOf(withId(R.id.text_language_detail), isDisplayed()));
        onView(allOf(withId(R.id.text_language_detail), withText(dummyTvShow.get(0).getLanguage())));
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}