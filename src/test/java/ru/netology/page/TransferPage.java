package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }
    public static DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public static DashboardPage makeInValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public static void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        $("[data-test-id='amount'] input").setValue(amountToTransfer);
        $("[data-test-id='from'] input").setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }

    public void findErrorMessage(String expectedText) {
        $("[data-test-id='error-message']").shouldHave(exactText(expectedText), Duration.ofSeconds(15));
    }

}
