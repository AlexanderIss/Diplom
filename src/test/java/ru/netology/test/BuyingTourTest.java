package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class BuyingTourTest {
    MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("Allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        mainPage = open("http://localhost:8080/", MainPage.class);
    }

    @AfterEach
    void tearDownAllDatabase() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Покупка тура кредитной картой с валидным заполнением формы и с одобрением банка.")
    void successfulPurchaseOfTourInCreditWithValidApprovedDebitCard() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.approvedCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotification("Операция одобрена Банком.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLineCreditRequestEntity();
        var expectedStatus = "APPROVED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("Отказ в покупке тура кредитной картой с валидным заполнением формы незарегистрированной картой.")
    void unsuccessfulPurchaseOfTourInCreditWithDebitCardNotRegisteredInSystem() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.randomCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    @DisplayName("Отказ в покупке тура кредитной картой с валидным заполнением формы без одобрения банка.")
    void unsuccessfulPurchaseOfTourInCreditWithValidDeclancedDebitCard() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.declinedCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "DECLINED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("Покупка тура дебетовой картой с валидным заполнением формы и с одобрением банка")
    void successfulPurchaseOfTourWithValidApprovedDebitCard() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.approvedCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotification("Операция одобрена Банком.");
        var actualStatusLastLinePaymentRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "APPROVED";
        assertEquals(actualStatusLastLinePaymentRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("Отказ в покупке тура дебетовой картой с валидным заполнением формы без одобрения банка.")
    void unsuccessfulPurchaseOfTourWithValidDeclancedDebitCard() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.declinedCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        var actualStatusLastLinePaymentRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "DECLINED";
        assertEquals(actualStatusLastLinePaymentRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("Отказ в покупке тура дебетовой картой с валидным заполнением формы незарегистрированной картой")
    void unsuccessfulPurchaseOfTourWithDebitCardNotRegisteredInSystem() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.randomCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    @DisplayName("Вывод уведомлений об ошибке при отправке формы с незаполненными полями.")
    void returnErrorWhenFormEmpty() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyErrorCardNumberField("Неверный формат");
        mainPage.verifyErrorMonthField("Неверный формат");
        mainPage.verifyErrorYearField("Неверный формат");
        mainPage.verifyErrorOwnerField("Поле обязательно для заполнения");
        mainPage.verifyErrorCVCField("Неверный формат");

    }

    @Test
    @DisplayName("Вывод уведомления об ошибке при отправке формы с невалидным номером карты.")
    void returnErrorWhenlettersInCardNumber() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.invalidCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyErrorCardNumberField("Неверный формат");
    }

    @Test
    @DisplayName("Вывод уведомления об ошибке при отправке формы с истекшим сроком обслуживания карты.")
    void returnErrorWhenPeriodInvalid() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.approvedCard();
        mainPage.invalidCardValidityPeriod();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyPeriodErrorYearField("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Вывод уведомления об ошибке при отправке формы c месяцем равным нулю.")
    void returnErrorWhenMonthEqualsZero() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.approvedCard();
        mainPage.invalidCardValidityMonthEqualsZero();
        mainPage.validOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyPeriodErrorYearField("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Вывод уведомления об ошибке при отправке формы с невалидным владельцем.")
    void returnErrorWhenCardWithInvalidCardholder() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.approvedCard();
        mainPage.validCardValidityPeriod();
        mainPage.inValidOwner();
        mainPage.validCVC();
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyErrorOwnerField("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Вывод уведомления об ошибке при отправке формы с невалидным CVC/CVV.")
    void returnErrorWhenCardWithInvalidCVC() {
        mainPage.chooseBy("Оплата по карте");
        mainPage.approvedCard();
        mainPage.validCardValidityPeriod();
        mainPage.validOwner();
        mainPage.inValidCVC();
        mainPage.verifySuccessfulNotificationIsNotVisible();
        mainPage.verifyErrorCVCField("Неверный формат");
    }

}
