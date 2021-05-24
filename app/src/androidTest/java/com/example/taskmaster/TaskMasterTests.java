package com.example.taskmaster;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest

public class TaskMasterTests {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testMainTitle() {
        onView(withText("taskmaster")).check(matches(isDisplayed()));
    }
//    testing if the main activity contain taskmaster title

    @Test
    public void testOnClick() {
        onView(withId(R.id.addTask)).perform(click());
//        check if the main activity contain a button with an specific id
    }

    @Test
    public  void testTextContent(){
        onView(withId(R.id.myTask)).check(matches(withText("My task")));
//  check if this id have the same test view on it

    }


}
