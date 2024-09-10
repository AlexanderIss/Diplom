package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement byButton = $(byText("Купить"));
    private final SelenideElement byInCreditButton = $(byText("Купить в кредит"));
    private final SelenideElement headingBy = $(byText("Оплата по карте"));
    private final SelenideElement headingByInCredit = $(byText("Кредит по данным карты"));
    private final SelenideElement сardNumberField = $("[placeholder='0000 0000 0000 0000']");

    private final SelenideElement monthField = $("[placeholder='08']");

    private final SelenideElement yearField = $("[placeholder='22']");

    private final SelenideElement ownerField = $$("[class=input__inner]").findBy(text("Владелец")).$("[class=input__control]");

    private final SelenideElement CVCField = $("[placeholder='999']");

    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successfulNotification = $(withText("Операция одобрена Банком."));
    private final SelenideElement unsuccessfulNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    private SelenideElement errorCardNumberField = $$("[class=input__inner]").findBy(text("Номер карты")).$(byText("Неверный формат"));
    private SelenideElement errorMonthField = $$("[class=input__inner]").findBy(text("Месяц")).$(byText("Неверный формат"));
    private SelenideElement periodErrorYearField = $$("[class=input__inner]").findBy(text("Год")).$(byText("Истёк срок действия карты"));
    private SelenideElement errorYearField = $$("[class=input__inner]").findBy(text("Год")).$(byText("Неверный формат"));
    private SelenideElement errorOwnerField = $$("[class=input__inner]").findBy(text("Владелец")).$(byText("Поле обязательно для заполнения"));
    private SelenideElement errorCVCField = $$("[class=input__inner]").findBy(text("CVC/CVV")).$(byText("Неверный формат"));

    public MainPage() {
        heading.shouldBe(Condition.visible);
    }

    public void verifyErrorNotification(String expectedText) {
        continueButton.click();
        unsuccessfulNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }

    public void verifySuccessfulNotification(String expectedText) {
        continueButton.click();
        successfulNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }

    public void verifySuccessfulNotificationIsNotVisible() {
        continueButton.click();
        successfulNotification.shouldBe(hidden, Duration.ofSeconds(15));
    }

    public void chooseBy(String expectedText) {
        byButton.click();
        headingBy.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void chooseByInCredit(String expectedText) {
        byInCreditButton.click();
        headingByInCredit.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void verifyErrorCardNumberField(String expectedText) {
        errorCardNumberField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyPeriodErrorYearField(String expectedText) {
        periodErrorYearField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorYearField(String expectedText) {
        errorYearField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorMonthField(String expectedText) {
        errorMonthField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorOwnerField(String expectedText) {
        errorOwnerField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorCVCField(String expectedText) {
        errorCVCField.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void approvedCard() {
        сardNumberField.setValue(DataHelper.getApprovedCardNumber());
    }

    public void declinedCard() {
        сardNumberField.setValue(DataHelper.getDeclinedCardNumber());

    }

    public void randomCard() {
        сardNumberField.setValue(DataHelper.getRandomCardNumber());
    }

    public void invalidCard() {
        сardNumberField.setValue("ABCD EFGH IJKL MNOP");
    }

    public void validCardValidityPeriod() {
        monthField.setValue(DataHelper.generateMonth(1));
        yearField.setValue(DataHelper.generateYear(1));
    }

    public void invalidCardValidityPeriod() {
        monthField.setValue(DataHelper.generateMonth(-1));
        yearField.setValue(DataHelper.generateYear(-1));
    }

    public void invalidCardValidityMonthEqualsZero() {
        monthField.setValue("00");;
        yearField.setValue(DataHelper.generateYear(1));
    }

    public void validOwner() {
        ownerField.setValue(DataHelper.generateOwner("en"));
    }

    public void inValidOwner() {
        ownerField.setValue(DataHelper.generateOwner("ru"));
    }

    public void validCVC() {
        CVCField.setValue(DataHelper.generateCVC());
    }

    public void inValidCVC() {
        CVCField.setValue(DataHelper.generateInvalidCVC());
    }

}