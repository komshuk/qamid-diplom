package ru.iteco.fmhandroid.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ControlPanelNews {
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    private final int buttonAddNews = R.id.add_news_image_view;
    public int getButtonAddNews() {
        return buttonAddNews;
    }


    private int buttonEditNews = R.id.edit_news_item_image_view;

    private final int buttonDeleteNews = R.id.delete_news_item_image_view;
    private final ViewInteraction buttonOk = onView(withId(android.R.id.button1));

    @Step("Нажатие на кнопку Добавить новость")
    public void addNews() {
        Allure.step("Нажатие на кнопку Добавить новость");
        onView(withId(buttonAddNews)).check(matches(isDisplayed()));
        onView(withId(buttonAddNews)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 6000));
    }

    @Step("Нажатие на кнопку Редактирование новостей")
    public void pressEditPanelNews(String text) {
        Allure.step("Нажатие на кнопку Редактирование новостей");
        ViewInteraction edit = onView(allOf(withId(buttonEditNews), withContentDescription(text)));
        edit.check(matches(isDisplayed()));
        edit.perform(click());
        onView(isRoot()).perform(waitDisplayed(editNews.getButtonSave(), 6000));
    }

    @Step("Нажатие на кнопку Удалить новость")
    public void deleteNews(String text) {
        Allure.step("Нажатие на кнопку Удалить новость");
        ViewInteraction delete = onView(allOf(withId(buttonDeleteNews), withContentDescription(text)));
        delete.check(matches(isDisplayed()));
        delete.perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Поиск новости с заголовком {text} и проверка ее видимости")
    public void searchNewsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск новости с заголовком {text} и проверка ее видимости");
        onView(withText(text)).check(matches(isDisplayed()));
    }


    @Step("Проверка отсутствия новости с заголовком {text}")
    public void checkDoesNotExistNews(String text) {
        Allure.step("Проверка отсутствия новости с заголовком {text}");
        onView(withText(text)).check(doesNotExist());
    }


}
