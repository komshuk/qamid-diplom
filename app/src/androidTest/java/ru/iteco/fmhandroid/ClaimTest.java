package ru.iteco.fmhandroid;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
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
import ru.iteco.fmhandroid.Utils.Utils;

import ru.iteco.fmhandroid.pageObject.AppBar;
import ru.iteco.fmhandroid.pageObject.Authorization;
import ru.iteco.fmhandroid.pageObject.Claims;

import ru.iteco.fmhandroid.pageObject.CreateClaims;
import ru.iteco.fmhandroid.pageObject.CreateNews;
import ru.iteco.fmhandroid.pageObject.EditClaims;

import ru.iteco.fmhandroid.pageObject.Main;
import ru.iteco.fmhandroid.pageObject.OpenClaims;

import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimTest {
    Authorization authorization = new Authorization();

    Claims claims = new Claims();
    AppBar appBar = new AppBar();
    CreateNews createNews = new CreateNews();
    Utils utils = new Utils();

    CreateClaims createClaims = new CreateClaims();
    EditClaims editClaims = new EditClaims();
    OpenClaims openClaims = new OpenClaims();
    Main main = new Main();
    private final String executor = "Ivanov Ivan Ivanovich";
    private final String lastDate = "01.01.2020";
    private final String description = "тест";
    private final String time = "12:00";
    private final String editTime = "16:00";
    private final String editDescription = "тест редактирование";
    private final String comment = "заявка исполнена";

    private final String textErrorWrongData = "Неверно указан период";
    private final String textErrorEmptyField = "Заполните пустые поля";

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
    @Epic("Заявки")
    @Feature("Создание заявки")
    @Description("Должна создаться заявка с указанной темой на экране заявок")
    @Test
    public void shouldBeMadeClaims() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки";
        createClaims.addTitle(title);
        createClaims.addExecutor(executor);
        createClaims.addDate(utils.currentDate());
        createClaims.addTime(time);
        createClaims.addDescription(description);
        createClaims.pressSave();
        claims.searchClaimsAndCheckIsDisplayed(title);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Заявки")
    @Feature("Создание заявки с плановой датой в прошлом")
    @Description("При создании заявки с плановой датой в прошлом должна появляться ошибка")
    @Test
    public void shouldStayOnClaimsCreationScreenWhenCreatingClaimsInPast() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки в прошлом";
        createClaims.addTitle(title);
        createClaims.addExecutor(executor);
        createClaims.addDate(lastDate);
        createClaims.addTime(time);
        createClaims.addDescription(description);
        createClaims.pressSave();
        createClaims.checkErrorDisplay(textErrorWrongData);

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Заявки")
    @Feature("Создание заявки с плановой датой спустя 5 лет")
    @Description("При создании заявки с плановой датой в будущем должна появляться ошибка")
    @Test
    public void shouldStayOnClaimsCreationScreenWhenCreatingAClaimsStoryAfter5Years() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки в будущем";
        createClaims.addTitle(title);
        createClaims.addExecutor(executor);
        createClaims.addDate(utils.dateMore5Year());
        createClaims.addTime(time);
        createClaims.addDescription(description);
        createClaims.pressSave();
        createClaims.checkErrorDisplay(textErrorWrongData);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Заявки")
    @Feature("Создание заявки с пустыми полями")
    @Description("При создании заявки с пустыми полями должна появляться ошибка")
    @Test
    public void shouldStayOnClaimsCreationScreenWhenCreatingClaimsWithEmptyFields() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        createClaims.pressSave();
        createClaims.pressOk();
        createClaims.checkErrorDisplay(textErrorEmptyField);

    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Заявки")
    @Feature("Редактирование заявки")
    @Description("После редактирования заявки заявка должна отредактироваться (проверяем, что на экране открытой заявки изменилась тема)")
    @Test
    public void shouldEditTheClaimsAfterEditing() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки для редактирования";
        createClaims.createClaims(title, " ", utils.currentDate(), time, description );
        claims.searchClaimsAndSwitch(title);
        openClaims.pressEditClaims();
        String editTitle = "Редактирование заявки";
        editClaims.editTitle(editTitle);
        editClaims.editExecutor(executor);
        editClaims.editDate(utils.dateMore1Month());
        editClaims.editTime(editTime);
        editClaims.editDescription(editDescription);
        editClaims.pressSave();
        openClaims.searchTitleAndCheckIsDisplayed(editTitle);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Заявки")
    @Feature("Отмена заявки")
    @Description("После отмены статус заявки изменится на отменена")
    @Test
    public void shouldChangeStatusOfClaimsToCanceled () {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки для отмены";
        createClaims.createClaims(title, " ", utils.currentDate(), time, description );
        claims.searchClaimsAndSwitch(title);
        openClaims.statusClaims("Открыта");
        openClaims.pressStatusClaims();
        openClaims.pressStatusCanceled();
        openClaims.statusClaims("Отменена");
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Заявки")
    @Feature("Исполнение заявки")
    @Description("После исполнения статус заявки изменится на исполнена, появится комментарий")
    @Test
    public void shouldChangeStatusOfClaimsToComplited() {
        appBar.switchToClaims();
        claims.pressAddClaim();
        String title = "Создание заявки для исполнения";
        createClaims.createClaims(title, executor, utils.currentDate(), time, description );
        claims.searchClaimsAndSwitch(title);
        openClaims.statusClaims("В работе");
        openClaims.pressStatusClaims();
        openClaims.pressStatusCompleted();
        openClaims.addComment(comment);
        openClaims.pressOk();
        openClaims.statusClaims("Выполнена");
        openClaims.commentCheckWithText(comment);
    }
}
