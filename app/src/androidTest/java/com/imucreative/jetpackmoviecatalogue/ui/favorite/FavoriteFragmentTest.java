package com.imucreative.jetpackmoviecatalogue.ui.favorite;

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
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteFragmentTest {
	@Rule
	public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
	private MainActivity activity;
	private FavoriteFragment fragment;

	@Before
	public void setUp() {
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
		activity = activityTestRule.getActivity();
		activityTestRule.launchActivity(new Intent());
		fragment = new FavoriteFragment();
		onView(withId(R.id.nav_favorite)).perform(click());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
		activityTestRule.finishActivity();
	}

	@Test
	public void swipePage() {
		onView(withId(R.id.viewpager))
				.check(matches(isDisplayed()));

		onView(withId(R.id.viewpager))
				.perform(swipeLeft());

		onView(withId(R.id.viewpager))
				.perform(swipeRight());
	}

	@Test
	public void checkTabLayoutDisplayed() {
		onView(withId(R.id.tabs))
				.perform(click())
				.check(matches(isDisplayed()));
	}
}