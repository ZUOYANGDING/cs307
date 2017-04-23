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
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Kenny on 4/22/2017.
 */

public class reportTest {

    @Rule
    public IntentsTestRule<homepage> reportButtonTestRule =
            new IntentsTestRule<homepage>(homepage.class);


    @Test
    public void reportOthersprofile() throws Exception {
        onData(anything()).inAdapterView(withId(R.id.group_list))
                .atPosition(0)
                .perform(click());
        Thread.sleep(4000);

        onData(anything()).inAdapterView(withId(R.id.chat_messages))
                .atPosition(0)
                .perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.reportButton)).perform(click());
        onView(withId(R.id.reportButton)).check(matches(withText("Reported")));

    }

    @Test
    public void reportOthersprofile_privacy() throws Exception {
        onData(anything()).inAdapterView(withId(R.id.group_list))
                .atPosition(0)
                .perform(click());
        Thread.sleep(4000);

        onData(anything()).inAdapterView(withId(R.id.chat_messages))
                .atPosition(0)
                .perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.reportBtn)).perform(click());
        onView(withId(R.id.reportBtn)).check(matches(withText("Reported")));

    }
}
