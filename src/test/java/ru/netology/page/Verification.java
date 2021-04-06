package ru.netology.page;

import ru.netology.data.Data;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Verification {
    private SelenideElement codeField = $("[type='text']");
    private SelenideElement verifyButton = $(byText("Продолжить"));


    public Dashboard validVerify(Data.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new Dashboard();
    }
}
