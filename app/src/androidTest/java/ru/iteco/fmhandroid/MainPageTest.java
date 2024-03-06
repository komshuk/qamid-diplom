package ru.iteco.fmhandroid;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.pageObject.AppBar;
import ru.iteco.fmhandroid.pageObject.Authorization;

import ru.iteco.fmhandroid.pageObject.Main;

import ru.iteco.fmhandroid.pageObject.OurMission;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    Authorization authorization = new Authorization();
    Main main = new Main();
    AppBar appBar = new AppBar();
    OurMission ourMission = new OurMission();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (!main.isDisplayedButtonProfile()) {
            authorization.loginSuccessful();
        }
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Главный  экран")
    @Feature("Переход на главный экран")
    @Description("Должен открыться главный экран")
    @Test
    public void shouldOpenMainScreen() {
        appBar.switchToOurMission();
        ourMission.textScreenCheckIsDisplayed();
        appBar.switchToMain();
        main.checkNews();
    }

}
