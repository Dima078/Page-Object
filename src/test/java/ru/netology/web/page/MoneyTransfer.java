package ru.netology.web.page;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class MoneyTransfer {

    private String amount = "2000";
    private String card1 = "5559 0000 0000 0001";
    private String card2 = "5559 0000 0000 0002";

    public DashboardPage transferMoney () {
        $$(withText("Пополнить")).first().click();
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").setValue(card2);
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public VerificationPage defaultMoney () {
        $$(withText("Пополнить")).last().click();
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").setValue(card1);
        $("[data-test-id='action-transfer']").click();
        return new VerificationPage();
    }
}
