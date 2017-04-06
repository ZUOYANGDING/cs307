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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Kenny on 4/2/2017.
 */

public class editLandingActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> editLandingActivityIntentsTestRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void nickNameOInly() throws Exception {

        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"));
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.profileButton)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.landing_Edit)).perform(click());
        Thread.sleep(1000);


        onView(withId(R.id.edit_landing_Nickname)).perform(typeText("testNickName"), closeSoftKeyboard());
        onView(withId(R.id.edit_landing_Save)).perform(click());
        onView(withId(R.id.edit_landing_error)).check(matches(withText("Please fill in all fields")));
        Thread.sleep(1000);

    }

    @Test
    public void emptyBirthday() throws Exception {

        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"));
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.profileButton)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.landing_Edit)).perform(click());
        Thread.sleep(1000);


        onView(withId(R.id.edit_landing_Nickname)).perform(typeText("testNickName"), closeSoftKeyboard());
        onView(withId(R.id.edit_landing_intro)).perform(typeText("testIntro"), closeSoftKeyboard());
        onView(withId(R.id.edit_landing_Save)).perform(click());
        onView(withId(R.id.edit_landing_error)).check(matches(withText("Please fill in all fields")));
        Thread.sleep(1000);
    }


    @Test
    public void validInputs() throws Exception {
        // Login
        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"));
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.profileButton)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.landing_Edit)).perform(click());
        Thread.sleep(1000);


        onView(withId(R.id.edit_landing_Nickname)).perform(typeText("testNickname"));
        onView(withId(R.id.edit_landing_Birthday)).perform(typeText("01012001"));
        onView(withId(R.id.edit_landing_intro)).perform(typeText("testIntro"), closeSoftKeyboard());
        onView(withId(R.id.edit_landing_Save)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.landing_Nickname)).check(matches(withText("testNickname")));
        onView(withId(R.id.landing_Birthday)).check(matches(withText("01012001")));
        onView(withId(R.id.landing_intro)).check(matches(withText("testIntro")));
        Thread.sleep(1000);

    }


}
