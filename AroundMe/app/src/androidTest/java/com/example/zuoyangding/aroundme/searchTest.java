package com.example.zuoyangding.aroundme;

import android.content.ComponentName;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.core.deps.guava.cache.Weigher;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.zuoyangding.aroundme.Activity.LoginActivity;
import com.example.zuoyangding.aroundme.Activity.RegisterActivity;
import com.example.zuoyangding.aroundme.Activity.group_aroudme;
import com.example.zuoyangding.aroundme.Activity.homepage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Kenny on 4/20/2017.
 */

public class searchTest {

    @Rule
    public IntentsTestRule<group_aroudme> searchGroupAroundMeTest =
            new IntentsTestRule<group_aroudme>(group_aroudme.class);


    @Test
    public void searchFieldValid() throws Exception {
        onView(withId(R.id.imageButton3)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.topic_input)).perform(typeText("a"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.submit_search)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.group_list)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.group_list)).perform(click());
        Thread.sleep(1000);
    }
}
