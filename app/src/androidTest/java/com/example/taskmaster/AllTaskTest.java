package com.example.taskmaster;

import android.renderscript.Element;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class AllTaskTest {

    @Rule
    public ActivityScenarioRule<DisplayAllTask> activityRule2 =
            new ActivityScenarioRule<>(DisplayAllTask.class);

    @Test
    public  void  testTextView(){

        onView(withId(R.id.textView)).check(matches(withText("All Tasks")));
//        check if the textView id have the provided text

        onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeLeft());
//        check if the  recyclerView can swipe to left

        onView(withId(R.id.recyclerView)).perform(ViewActions.swipeLeft()).check(ViewAssertions.matches(isDisplayed()));
//        check if the  recyclerView can swipe to left and if there is a view displayed


        onView(withText("My task 1")).perform(ViewActions.swipeLeft()).check(ViewAssertions.matches(isDisplayed()));
//        swipe the recyclerView and check if it have My tasks 1 text





//        onView(withId(R.id.recyclerView)).perform(scrollTo()).check(matches(withText("My task 1")));
//        onView(withId(R.id.recyclerView)).perform(scrollTo(), click());

    }


}
