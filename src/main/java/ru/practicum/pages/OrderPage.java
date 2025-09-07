package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.constans.RentalPeriod;
import ru.practicum.constans.ColorsScooter;
import java.time.Duration;


public class OrderPage {
    private final WebDriver driver;

    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodInput = By.className("Dropdown-placeholder");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButtonMiddle = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text() ='Заказать') ]");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successModal = By.className("Order_ModalHeader__3FDaJ");

    private final By body = By.tagName("body");
    private final String[] metroStations = {
            "Сокольники", "Лубянка", "Красные Ворота", "Чистые пруды",
            "Комсомольская", "Курская", "Бауманская", "Электрозаводская"
    };

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstPage(String name, String surname, String address, int metroIndex, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);


        String metroStation = metroStations[metroIndex];

        driver.findElement(metroInput).click();
        WebElement metroOption = driver.findElement(By.xpath(".//div[text()='" + metroStation + "']"));
        metroOption.click();

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }


    public void fillSecondPage(String date, RentalPeriod rentalPeriod, ColorsScooter colorsScooter, String comment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));


        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(body).click();
        driver.findElement(rentalPeriodInput).click();

        WebElement periodOption = driver.findElement(By.xpath(".//div[text()='" + rentalPeriod.getDisplayText() + "']"));
        periodOption.click();

        driver.findElement(body).click();

        if (colorsScooter == ColorsScooter.BLACK) {
            driver.findElement(blackColorCheckbox).click();
        } else if (colorsScooter == ColorsScooter.GREY) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButtonMiddle).click();
    }

    public void confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public boolean isOrderSuccess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return successElement.isDisplayed();
    }
}
