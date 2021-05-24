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
public class AddTaskTest {



    @Rule
    public ActivityScenarioRule<DisplayAddTask> activityRule1 =
            new ActivityScenarioRule<>(DisplayAddTask.class);

    @Test
    public  void  testOnClickMatcher(){
        onView(withId(R.id.addTaskView)).perform(click()).check(matches(isDisplayed()));
    }
//    check if there is onClick that match the provided view id


    @Test
    public void testAddTaskTitle() {
        onView(withId(R.id.addTaskText)).check(matches(withText("Add task")));

    }
}
