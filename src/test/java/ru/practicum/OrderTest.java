package ru.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.constans.RentalPeriod;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import ru.practicum.constans.ColorsScooter;

@RunWith(Parameterized.class) // Что бы запустить тесты в Firefox нужна команда: mvn clean test -Dbrowser=firefox
public class OrderTest extends DriverFactory {
    private final String name;
    private final String surname;
    private final String address;
    private final int metroIndex;
    private final String phone;
    private final String date;
    private final RentalPeriod rentalPeriod;
    private final ColorsScooter colorsScooter;
    private final String comment;
    private final boolean useTopButton;

    public OrderTest(String name, String surname, String address, int metroIndex,
                     String phone, String date, RentalPeriod rentalPeriod, ColorsScooter colorsScooter,
                     String comment, boolean useTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroIndex = metroIndex;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod ;
        this.colorsScooter = colorsScooter;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Иван", "Иванов", "ул. Лесная, д. 1", 0,
                        "89991234567", "01.08.2025", RentalPeriod.THREE_DAYS, ColorsScooter.BLACK,
                        "Позвонить за час", true},

                {"Мария", "Петрова", "пр. Мира, д. 25", 4,
                        "89998765432", "20.12.2024", RentalPeriod.ONE_DAY, ColorsScooter.GREY,
                        "Оставить у двери", true}
        });
    }

    @Test
    public void testSuccessfulOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (useTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        orderPage.fillFirstPage(name, surname, address, metroIndex, phone);
        orderPage.fillSecondPage(date, rentalPeriod, colorsScooter, comment);
        orderPage.confirmOrder();

        assertTrue("Должно отображаться модальное окно успешного заказа",
                orderPage.isOrderSuccess());
    }
}
