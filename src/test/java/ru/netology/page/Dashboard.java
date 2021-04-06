package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Dashboard {

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection refillButton = $$("[data-test-id='action-deposit']");
    private SelenideElement refreshButton = $(byText("Обновить"));


    public Transfer cardRefillButtonClickFirst() {
        refillButton.first().click();
        return new Transfer();
    }

    public Transfer cardRefillButtonClickSecond() {
        refillButton.last().click();
        return new Transfer();
    }

    public int getCardBalanceFirst() {
        return getCardBalance("01");
    }

    public int getCardBalanceSecond() {
        return getCardBalance("02");
    }

    public int getCardBalance(String id) {
        val text = cards.findBy(Condition.text(id)).text();
        return extractBalance(text);
    }

    public static int cardBalanceAfterSendMoney(int balance, int amount) {
        int total = balance - amount;
        return total;
    }

    public static int cardBalanceAfterGetMoney(int balance, int amount) {
        int total = balance + amount;
        return total;
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void setReload() {
        refreshButton.click();
    }
}
