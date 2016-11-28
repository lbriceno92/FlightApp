package com.lbriceno.dragonsflight;

import android.os.SystemClock;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import com.lbriceno.dragonsflight.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by luis_ on 11/27/2016.
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void priceClick(){
        onView(withId(R.id.min)).perform(ViewActions.click()).perform(ViewActions.replaceText("200"),pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.max)).perform(ViewActions.click()).perform(replaceText("500"),pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.action_search)).perform(ViewActions.click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("braavos"), pressKey(KeyEvent.KEYCODE_ENTER));
    }

}
