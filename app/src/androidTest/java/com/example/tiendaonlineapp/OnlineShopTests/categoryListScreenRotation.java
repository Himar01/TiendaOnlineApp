package com.example.tiendaonlineapp.OnlineShopTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.categories.CategoryListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



    @RunWith(AndroidJUnit4.class)
    public class categoryListScreenRotation {

        @Rule
        public ActivityScenarioRule<CategoryListActivity> mActivityScenarioRule =
                new ActivityScenarioRule<>(CategoryListActivity.class);
        @Rule
        public ActivityScenarioRule<CategoryListActivity> testRule =
                new ActivityScenarioRule<>(CategoryListActivity.class);
        private Activity activity;
        UiDevice device;
        Context context =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        @Before
        public void setUp() {
            AppMediator.resetInstance();
            UserLog.resetInstance();
            try {

                device = UiDevice.getInstance(getInstrumentation());
                device.setOrientationNatural();

            } catch (RemoteException e) {
            }

        }

        @After
        public void tearDown() {

            try {

                UiDevice device = UiDevice.getInstance(getInstrumentation());
                device.setOrientationNatural();

            } catch (RemoteException e) {
            }

        }

        private void rotate_natural() {
            try {
                device.setOrientationNatural();
            } catch (RemoteException e) {
            }
        }   private void rotate_left() {
            try {

                device.setOrientationLeft();
            } catch (RemoteException ignored) {
            }
        }

        @Test
        public void categoryListScreenRotation() {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ViewInteraction textView = onView(
                    allOf(withId(R.id.content), withText("Componentes"),
                            withParent(allOf(withId(R.id.linearLayout),
                                    withParent(withId(R.id.category_list)))),
                            isDisplayed()));
            textView.check(matches(withText("Componentes")));

            ViewInteraction textView2 = onView(
                    allOf(withId(R.id.login), withText("Iniciar | Registrarse"),
                            withParent(allOf(withId(R.id.userBar),
                                    withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                            isDisplayed()));
            textView2.check(matches(withText("Iniciar | Registrarse")));

            // Added a sleep statement to match the app's execution delay.
            // The recommended way to handle such scenarios is to use Espresso idling resources:
            // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rotate_left();
            ViewInteraction textView3 = onView(
                    allOf(withId(R.id.content), withText("Componentes"),
                            withParent(allOf(withId(R.id.linearLayout),
                                    withParent(withId(R.id.category_list)))),
                            isDisplayed()));
            textView3.check(matches(withText("Componentes")));

            ViewInteraction textView4 = onView(
                    allOf(withId(R.id.login), withText("Iniciar | Registrarse"),
                            withParent(allOf(withId(R.id.userBar),
                                    withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                            isDisplayed()));
            textView4.check(matches(withText("Iniciar | Registrarse")));

            // Added a sleep statement to match the app's execution delay.
            // The recommended way to handle such scenarios is to use Espresso idling resources:
            // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rotate_natural();
            ViewInteraction textView5 = onView(
                    allOf(withId(R.id.content), withText("Componentes"),
                            withParent(allOf(withId(R.id.linearLayout),
                                    withParent(withId(R.id.category_list)))),
                            isDisplayed()));
            textView5.check(matches(withText("Componentes")));

            ViewInteraction textView6 = onView(
                    allOf(withId(R.id.login), withText("Iniciar | Registrarse"),
                            withParent(allOf(withId(R.id.userBar),
                                    withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                            isDisplayed()));
            textView6.check(matches(withText("Iniciar | Registrarse")));

        }

        private static Matcher<View> childAtPosition(
                final Matcher<View> parentMatcher, final int position) {

            return new TypeSafeMatcher<View>() {
                @Override
                public void describeTo(Description description) {
                    description.appendText("Child at position " + position + " in parent ");
                    parentMatcher.describeTo(description);
                }

                @Override
                public boolean matchesSafely(View view) {
                    ViewParent parent = view.getParent();
                    return parent instanceof ViewGroup && parentMatcher.matches(parent)
                            && view.equals(((ViewGroup) parent).getChildAt(position));
                }
            };
        }
    }
