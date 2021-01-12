package com.imucreative.jetpackmoviecatalogue.ui.favorite.movie;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.ui.main.MainActivity;
import com.imucreative.jetpackmoviecatalogue.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class FavoriteMovieFragmentTest {
	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

	@Before
	public void setUp() {
		activityRule.launchActivity(new Intent());
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
		onView(withId(R.id.nav_favorite)).perform(click());
		onView(withId(R.id.tabs)).perform(swipeRight());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
		activityRule.finishActivity();
	}

	@Test
	public void loadFavoriteMovies() {
		onView(allOf(withId(R.id.rv_movie), isDisplayed()));
	}
}