package com.example.zuoyangding.aroundme;

import android.content.ComponentName;
import android.support.test.espresso.core.deps.guava.cache.Weigher;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.zuoyangding.aroundme.Activity.LoginActivity;
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

public class EmailLoginTest {

    @Rule
    public IntentsTestRule<LoginActivity> emailLoginActivityTestRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void emailRegistrationBtn() throws Exception {
        onView(withId(R.id.email_register_bt)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), RegisterActivity.class)));
        Thread.sleep(1000);

    }

    @Test
    public void emptyEmailAndPassword() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter an email")).inRoot(withDecorView(not(emailLoginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void invalidEmail() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("invalid_email"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a valid email address")).inRoot(withDecorView(not(emailLoginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void validEmailEmptyPassword() throws Exception {
        onView(withId(R.id.email_tx)).perform(typeText("test1@test.com"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a password")).inRoot(withDecorView(not(emailLoginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void wrongPassword() throws Exception {
        onView(withId(R.id.email_tx)).perform(typeText("test1@test.com"));
        onView(withId(R.id.password_tx)).perform(typeText("purdue"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);
        onView(withText("Authentication failed")).inRoot(withDecorView(not(emailLoginActivityTestRule.getActivity().getWindow().getDecorView()))).
                check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void sucessEmailLogin() throws Exception {
        Thread.sleep(1000);
        onView(withId(R.id.email_tx)).perform(typeText("test1@test.com"));
        onView(withId(R.id.password_tx)).perform(typeText("1234567"));
        onView(withId(R.id.email_login_btn)).perform(click());
        Thread.sleep(1000);
    }




}
