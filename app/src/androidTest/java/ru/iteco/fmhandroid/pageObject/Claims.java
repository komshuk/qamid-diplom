package ru.iteco.fmhandroid.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class Claims {
    CreateClaims createClaims = new CreateClaims();
    OpenClaims openClaims = new OpenClaims();

    private final int buttonAddClaims = R.id.add_new_claim_material_button;

    public int getButtonAddClaims() {
        return buttonAddClaims;
    }


    @Step("Нажатие на кнопку Добавить новость")
    public void pressAddClaim() {
        Allure.step("Нажатие на кнопку Добавить новость");
        onView(withId(buttonAddClaims)).check(matches(isDisplayed()));
        onView(withId(buttonAddClaims)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createClaims.getButtonSave(), 6000));
    }

    @Step("Поиск заявки с темой {text} и переход в нее")
    public void searchClaimsAndSwitch(String text) {
        Allure.step("Поиск заявки с темой {text} и переход в нее");
        ViewInteraction textTitle = onView(ViewMatchers.withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));
        textTitle.check(matches(isDisplayed()));
        textTitle.perform(click());
        onView(isRoot()).perform(waitDisplayed(openClaims.getButtonEdit(), 6000));
    }

    @Step("Поиск заявки с темой {text} и проверка ее видимости")
    public void searchClaimsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск заявки с темой {text} и проверка ее видимости");
        onView(isRoot()).perform(waitDisplayed(buttonAddClaims, 5000));
        ViewInteraction textTitle = onView(ViewMatchers.withId(R.id.claim_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));
        textTitle.check(matches(isDisplayed()));
    }






}
