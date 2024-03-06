package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
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
import ru.iteco.fmhandroid.pageObject.AboutApp;
import ru.iteco.fmhandroid.pageObject.AppBar;
import ru.iteco.fmhandroid.pageObject.Authorization;

import ru.iteco.fmhandroid.pageObject.Main;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutAppTest {
    Authorization authorization = new Authorization();

    AppBar appBar = new AppBar();
    Main main = new Main();

    AboutApp aboutApp = new AboutApp();
    private final String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy";
    private final String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

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

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("О приложении")
    @Feature("Просмотр политики конфиденциальности")
    @Description("Должна открыться политика конфиденциальности")
    @Test
    public void shouldOpenPrivacyPolicy() {
        appBar.switchToAboutApp();
        aboutApp.intentPrivatePolicy(urlPrivacyPolicy);
        aboutApp.back();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("О приложении")
    @Feature("Просмотр пользовательского соглашения")
    @Description("Должно открыться пользовательское соглашение")
    @Test
    public void shouldOpenTermsOfUse() {
        appBar.switchToAboutApp();
        aboutApp.intentTermOfUse(urlTermsOfUse);
        aboutApp.back();

    }

}
