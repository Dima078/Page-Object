package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.MoneyTransfer;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    private String card1 = DataHelper.getCardNumber1().getNumber();
    private String card2 = DataHelper.getCardNumber2().getNumber();

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "2000";
        new DashboardPage().firstCard();
        new MoneyTransfer().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 12000;
        int expectedCard2 = 8000;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new MoneyTransfer().transferMoney(money, card1);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "10000";
        new DashboardPage().firstCard();
        new MoneyTransfer().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 20000;
        int expectedCard2 = 0;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new MoneyTransfer().transferMoney(money, card1);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllOverV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        String money = "12000";
        new DashboardPage().firstCard();
        new MoneyTransfer().transferMoney(money, card2);
        int actualCard1 = new DashboardPage().getCardBalance(0);
        int actualCard2 = new DashboardPage().getCardBalance(1);
        int expectedCard1 = 22000;
        int expectedCard2 = -2000;
        assertEquals(expectedCard1, actualCard1);
        assertEquals(expectedCard2, actualCard2);
        new DashboardPage().secondCard();
        new MoneyTransfer().transferMoney(money, card1);
    }
}