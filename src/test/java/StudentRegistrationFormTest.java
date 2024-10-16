import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920 х 1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
           }

    @Test
    void fillFormTest() {

        String firstName = "Aleksey";
        String lastName = "Arkhipov";
        String userEmail = "my@yandex.ru";
        String userNumber = "1234567890";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").findElement(byText("Male")). click();
        $("#userNumber").setValue(userNumber).scrollTo();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__day--022"). click();

        $("#subjectsInput").setValue("a").sendKeys(Keys.ENTER);
        $("#hobbiesWrapper").findElement(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("1.jpg");

        $("#currentAddress").setValue("Moscow");

        $("#state").shouldBe(visible).click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Panipat")).click();

        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name")).shouldHave(text(firstName + " "+ lastName));
        $(".table-responsive").shouldHave(text("Student Email")).shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Gender")).shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("Mobile")).shouldHave(text("0"));
        $(".table-responsive").shouldHave(text("Date of Birth")).shouldHave(text("22 May,2000"));
        $(".table-responsive").shouldHave(text("Subjects")).shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Hobbies")).shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("Picture")).shouldHave(text("1.jpg"));
        $(".table-responsive").shouldHave(text("Address")).shouldHave(text("Moscow"));
        $(".table-responsive").shouldHave(text("State and City")).shouldHave(text("Haryana Panipat"));
    }
}
