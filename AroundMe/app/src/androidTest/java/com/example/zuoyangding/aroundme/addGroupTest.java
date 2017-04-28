package com.example.zuoyangding.aroundme;

import android.content.ComponentName;
import android.support.test.espresso.core.deps.guava.cache.Weigher;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.zuoyangding.aroundme.Activity.LandingActivity;
import com.example.zuoyangding.aroundme.Activity.LoginActivity;
import com.example.zuoyangding.aroundme.Activity.editLandingActivity;
import com.example.zuoyangding.aroundme.Activity.RegisterActivity;
import com.example.zuoyangding.aroundme.Activity.homepage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
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
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Kenny on 4/2/2017.
 */

public class addGroupTest {

    @Rule
    public IntentsTestRule<LoginActivity> editLandingActivityIntentsTestRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void groupName() throws Exception {

        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("test1@test.com"));
        onView(withId(R.id.password_tx)).perform(typeText("1234567"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.addGroupButton)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.enterGroupName)).perform(typeText("testGroupName"), closeSoftKeyboard());
        onView(withId(R.id.Topics)).perform(typeText("testTopics"), closeSoftKeyboard());
        onView(withId(R.id.createGroup)).perform(click());
        Thread.sleep(1000);

    }

    @Test
    public void emptyInputs() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("test1@test.com"));
        onView(withId(R.id.password_tx)).perform(typeText("1234567"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.addGroupButton)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.createGroup)).perform(click());
        onView(withText("Please enter either a group name or topic."))
                .inRoot(withDecorView(not(editLandingActivityIntentsTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


}
