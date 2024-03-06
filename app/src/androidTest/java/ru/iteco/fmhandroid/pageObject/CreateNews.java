package ru.iteco.fmhandroid.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CreateNews {

    private final ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction title = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction time = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction date = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction description = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction save = onView(withId(R.id.save_button));
    private final int buttonSave = R.id.save_button;
    public int getButtonSave() {
        return buttonSave;
    }

    @Step("Ввод в поле категория {text}")
    public void addCategory(String text) {
        Allure.step("Ввод в поле категория {text}");
        category.check(matches(isDisplayed()));
        category.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод в поле заголовок {text}")
    public void addTitle(String text) {
        Allure.step("Ввод в поле заголовок {text}");
        title.check(matches(isDisplayed()));
        title.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод в поле дата {text}")
    public void addDate(String text) {
        Allure.step("Ввод в поле дата {text}");
        date.check(matches(isDisplayed()));
        date.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод в поле время {text}")
    public void addTime(String text) {
        Allure.step("Ввод в поле время {text}");
        time.check(matches(isDisplayed()));
        time.perform(replaceText(text), closeSoftKeyboard());

    }

    @Step("Ввод в поле описание {text}")
    public void addDescription(String text) {
        Allure.step("Ввод в поле описание {text}");
        description.check(matches(isDisplayed()));
        description.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Нажатие на кнопку Сохранить")
    public void pressSave() {
        Allure.step("Нажатие на кнопку Сохранить");
        closeSoftKeyboard();
        scrollTo();
        onView(isRoot()).perform(waitDisplayed(buttonSave, 10000));
        save.check(matches(isDisplayed()));
        save.perform(scrollTo()).perform(click());
    }

    @Step("Создание новости с полями: категория {category}, заголовок {title}, дата {date}, время {time}, описание {description}")
    public void createNews(String category, String title, String date, String time, String description) {
        Allure.step("Создание новости с полями: категория {category}, заголовок {title}, дата {date}, время {time}, описание {description}");
        addCategory(category);
        addTitle(title);
        addDate(date);
        addTime(time);
        addDescription(description);
        onView(isRoot()).perform(waitDisplayed(buttonSave, 5000));
        pressSave();
    }

    @Step("Проверка отображения ошибки")
    public void checkErrorDisplay(String text) {
        Allure.step("Проверка отображения ошибки");
        onView(withText(text)).check(matches(isDisplayed()));
    }

}
