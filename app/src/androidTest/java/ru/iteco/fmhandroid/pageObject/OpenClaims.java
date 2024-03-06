package ru.iteco.fmhandroid.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
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

public class OpenClaims {

    EditClaims editClaims = new EditClaims();
    private final int buttonEdit = R.id.edit_processing_image_button;
    private final ViewInteraction buttonStatus = onView(withId(R.id.status_processing_image_button));
    private final ViewInteraction textStatus = onView(withId(R.id.status_label_text_view));
    private final ViewInteraction addComment = onView(withId(R.id.editText));
    private final ViewInteraction buttonOk = onView(withId(android.R.id.button1));
    private final ViewInteraction textComment = onView(withId(R.id.comment_description_text_view));
    private final ViewInteraction statusCompleted = onView(withText("Исполнить"));
    private final ViewInteraction statusCanceled = onView(withText("Отменить"));

    private final ViewInteraction textTitle = onView(withId(R.id.title_text_view));

    public int getButtonEdit() {
        return buttonEdit;
    }

    @Step("Нажатие на кнопку Редактировать Заявку")
    public void pressEditClaims() {
        Allure.step("Нажатие на кнопку Редактировать Заявку");
        onView(withId(buttonEdit)).check(matches(isDisplayed()));
        onView(withId(buttonEdit)).perform(click());
        onView(isRoot()).perform(waitDisplayed(editClaims.getButtonSave(), 5000));
    }

    @Step("Нажатие на кнопку Статус заявки")
    public void pressStatusClaims() {
        Allure.step("Нажатие на кнопку Статус заявки");
        buttonStatus.check(matches(isDisplayed()));
        buttonStatus.perform(click());
    }

    @Step("Ввод в поле комментарий {text}")
    public void addComment(String text) {
        Allure.step("Ввод в поле комментарий {text}");
        addComment.check(matches(isDisplayed()));
        addComment.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Нажатие на кнопку ОК")
    public void pressOk() {
        Allure.step("Нажатие на кнопку ОК");
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Поиск темы, содержащей {text}, и проверка её видимости")
    public void searchTitleAndCheckIsDisplayed(String text) {
        Allure.step("Поиск темы, содержащей {text}, и проверка её видимости");
        onView(isRoot()).perform(waitDisplayed(buttonEdit, 5000));
        ViewInteraction textClaims = onView(withText(text));
        textClaims.check(matches(isDisplayed()));
    }

    @Step("Проверка соответсвия комментария введенному тексту")
    public void commentCheckWithText(String text) {
        Allure.step("Проверка соответсвия комментария введенному тексту");
        textComment.check(matches(isDisplayed()));
        textComment.check(matches(withText(text)));
    }

    @Step("Проверка статуса заявки")
    public void statusClaims(String text) {
        Allure.step("Проверка статуса заявки");
        textStatus.check(matches(isDisplayed()));
        textStatus.check(matches(withText(text)));
    }

    @Step("Изменение статуса заявки на Отменена")
    public void pressStatusCanceled() {
        Allure.step("Изменение статуса заявки на Отменена");
        statusCanceled.perform(click());
    }
    @Step("Изменение статуса заявки на Выполнена")
    public void pressStatusCompleted() {
        Allure.step("Изменение статуса заявки на Выполнена");
        statusCompleted.perform(click());
    }

}
