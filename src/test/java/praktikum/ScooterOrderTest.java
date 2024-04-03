package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.RentPage;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    @Rule
    public final DriverRule driverRule = new DriverRule();
    private final By enterPoint;
    private final By rentTern;
    private final By scoterColour;

    public ScooterOrderTest(By enterPoint, By rentTern, By scoterColour) {
        this.enterPoint = enterPoint;
        this.rentTern = rentTern;
        this.scoterColour = scoterColour;
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {MainPage.ORDER_BUTTON_HEADER, RentPage.CHOOSE_RENT_TERM_TWO_DAYS, RentPage.SCOOTER_COLOUR_BLACK},
                {MainPage.ORDER_BUTTON_BODY, RentPage.CHOOSE_RENT_TERM_ONE_DAY, RentPage.SCOOTER_COLOUR_GREY},
        };
    }
    @Test
    public void scooterOrderTest() throws InterruptedException {
        WebDriver driver= driverRule.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.acceptCookie();
        main.clickOrderButton(enterPoint); //параметризация точки входа (кнопка Заказать в header, в body)
        OrderPage order = new OrderPage(driver);
        order.sendNameAndLastname();
        order.sendAdresToDelivery();
        order.selectMetroStation();
        order.sendPhoneNumber();
        order.clickButtonNext();
        RentPage rent = new RentPage(driver);
        rent.sendDeliveryDate();
        rent.rentTerm(rentTern); //параметризация срока аренды (двое суток, сутки)
        rent.chooseScooterColour(scoterColour);// параметризация цвета самоката (черный , серый)
        rent.sendCommentToCourier();
        rent.clickOrderButton();
        rent.clickOrderButtonConfirm();
        rent.modalOrderIsProcessedIsVisible();
    }
}