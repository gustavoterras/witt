package com.tw.witt.view.activity;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tw.witt.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by gustavoterras on 24/01/18.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppFlowFail {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void appFlowFail() {
        ViewInteraction appCompatImageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.main_vp),
                                0),
                        2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction verticalViewPager = onView(
                allOf(withId(R.id.main_vp),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager.perform(swipeLeft());

        ViewInteraction appCompatImageView2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.main_vp),
                                1),
                        1),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction verticalViewPager2 = onView(
                allOf(withId(R.id.main_vp),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager2.perform(swipeLeft());

        ViewInteraction appCompatImageView3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.main_vp),
                                2),
                        1),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction textView = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.result_of_search)),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.result_fail)),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
