package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.MoneyTransfer;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        new MoneyTransfer().transferMoney("2000");
        var actual = new DashboardPage().getCardBalance();
        int expected = 12000;
        assertEquals(expected, actual);
        new MoneyTransfer().defaultMoney("2000");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        new MoneyTransfer().transferMoney("10000");
        var actual = new DashboardPage().getCardBalance();
        int expected = 20000;
        assertEquals(expected, actual);
        new MoneyTransfer().defaultMoney("10000");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsAllOverV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        new MoneyTransfer().transferMoney("10000");
        new MoneyTransfer().transferMoney("2000");
        var actual = new DashboardPage().getCardBalance();
        int expected = 22000;
        assertEquals(expected, actual);
        new MoneyTransfer().defaultMoney("12000");
    }
}