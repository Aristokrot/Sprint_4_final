package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // Кнопка «Заказать» вверху
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка «Заказать» внизу
    private final By orderButtonBottom = By.xpath(".//button[text()='Заказать']");

    // Вопросы о важном - список вопросов
    private final By faqQuestions =  By.className("accordion__item");

    // Панель ответов FAQ
    private final By faqResponse= By.className("accordion__panel");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Клик по вопросу FAQ
    public void clickFaqQuestion(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (index < questions.size()) {
            WebElement question = questions.get(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
            question.click();
        }
    }

    // Получение текста ответа FAQ
    public String getFaqAnswerText(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (index < questions.size()) {
            return questions.get(index).findElement(faqResponse).getText();
        }
        return "";
    }



    // Клик по верхней кнопке заказа
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    // Клик по нижней кнопке заказа
    public void clickOrderButtonBottom() {
        WebElement bottomButton = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bottomButton);
        bottomButton.click();
    }

    // Проверка видимости ответа FAQ
    public boolean isFaqAnswerVisible(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (index < questions.size()) {
            return questions.get(index).findElement(faqResponse).isDisplayed();
        }
        return false;
    }



    // Скролл к FAQ
    public void scrollToFaq() {
        List<WebElement> questions = driver.findElements(faqQuestions);
        if (!questions.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questions.get(0));
        }
    }
}
