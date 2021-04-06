package ru.netology.test;

import lombok.val;

import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.page.Dashboard;
import ru.netology.page.Login;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestService {

    @Test
    void shouldTransferMoney() {
        open("http://localhost:9999/");
        int amount = 1000;
        val loginPage = new Login();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalance01 = dashboard.getCardBalanceFirst();
        val cardBalance02 = dashboard.getCardBalanceSecond();
        val cardInfo = Data.Card.getCardInfo01();
        val transferMoney = dashboard.cardRefillButtonClickSecond();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSend01 = Dashboard.cardBalanceAfterSendMoney(cardBalance01, amount);
        val cardBalanceAfterSend02 = Dashboard.cardBalanceAfterGetMoney(cardBalance02, amount);
        assertEquals(cardBalanceAfterSend01, dashboard.getCardBalanceFirst());
        assertEquals(cardBalanceAfterSend02, dashboard.getCardBalanceSecond());


    }

    @Test
    void shouldTransferMoneyFrom02To01Card() {
        open("http://localhost:9999/");
        int amount = 1500;
        val loginPage = new Login();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalance01 = dashboard.getCardBalanceFirst();
        val cardBalance02 = dashboard.getCardBalanceSecond();
        val cardInfo = Data.Card.getCardInfo02();
        val transferMoney = dashboard.cardRefillButtonClickFirst();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSend02 = Dashboard.cardBalanceAfterSendMoney(cardBalance02, amount);
        val cardBalanceAfterSend01 = Dashboard.cardBalanceAfterGetMoney(cardBalance01, amount);
        assertEquals(cardBalanceAfterSend01, dashboard.getCardBalanceFirst());
        assertEquals(cardBalanceAfterSend02, dashboard.getCardBalanceSecond());
    }

    @Test
    void shouldReturnErrorIfSendMoreBalance() {
        open("http://localhost:9999/");
        int amount = 20000;
        val loginPage = new Login();
        val authInfo = Data.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardInfo = Data.Card.getCardInfo02();
        val transferMoney = dashboard.cardRefillButtonClickSecond();
        transferMoney.transfer(cardInfo, amount);
        transferMoney.errorMessage();
    }

}
