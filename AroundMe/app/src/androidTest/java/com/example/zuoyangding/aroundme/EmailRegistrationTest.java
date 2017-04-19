package com.example.zuoyangding.aroundme;

import android.content.ComponentName;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.core.deps.guava.cache.Weigher;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.example.zuoyangding.aroundme.Activity.LoginActivity;
import com.example.zuoyangding.aroundme.Activity.RegisterActivity;
import com.example.zuoyangding.aroundme.Activity.homepage;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Kenny on 4/2/2017.
 */

public class EmailRegistrationTest {

    @Rule
    public IntentsTestRule<RegisterActivity> emailRegistrationTest =
            new IntentsTestRule<RegisterActivity>(RegisterActivity.class);

    @Test
    public void nickNameOnly() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("ValidNickname"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter an email address")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void inValidNickName() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("12"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a valid email address")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void inValidNickName2() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("NickNameTooLongToBeAcceptedAsValidNickName"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a valid email address")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void inValidNickName3() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("NickNameTooLongToBeAcceptedAsValidNickName"));
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.retype_password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Nick Name should between 3 to 20 characters")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void invalidEmail() throws Exception {
        onView(withId(R.id.email_tx)).perform(typeText("inValidEmailAddress"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a valid email address")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void emptyRetypePassword() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("testNickname"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Please enter a password")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void invalidPassword() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("testNickname"));
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.retype_password_tx)).perform(typeText("18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Password should between 6 to 20 characters")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void invalidRetypePassword() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("testNickname"));
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.retype_password_tx)).perform(typeText("purdue"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Password does not match")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void duplicateEmail() throws Exception {
        onView(withId(R.id.nickname_tx)).perform(typeText("testNickname"));
        onView(withId(R.id.email_tx)).perform(typeText("zheng323@purdue.edu"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.retype_password_tx)).perform(typeText("purdue18"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.register_bt)).perform(click());
        Thread.sleep(1000);
        onView(withText("Registration Error : The email address is already in use by another account.")).inRoot(withDecorView(not(emailRegistrationTest.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

}
