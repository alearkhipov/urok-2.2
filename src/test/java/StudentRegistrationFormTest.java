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
        Configuration.holdBrowserOpen = false;
           }

    @Test
    void fillFormTest() {

        String name = "Aleksey";

        open("/automation-practice-form");

        $("#firstName").setValue(name);
        $("#lastName").setValue("Arkhipov");
        $("#userEmail").setValue("my@yandex.ru");
        $("#genterWrapper").findElement(byText("Male")). click();
        $("#userNumber").setValue("0987654321").scrollTo();

        $("#dateOfBirth").hover(). click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--022"). click();

        $("#subjectsInput").setValue("a").sendKeys(Keys.ENTER);
        $("#hobbiesWrapper").findElement(byText("Music")).click();

        File image = new File("src/test/resources/1.jpg");
        $("#uploadPicture").uploadFile(image);

        $("#currentAddress").setValue("Moscow");

        $("#state").shouldBe(visible).click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Panipat")).click();

        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name")).shouldHave(text(name + " "+ "Arkhipov"));
        $(".table-responsive").shouldHave(text("Student Email")).shouldHave(text("my@yandex.ru"));
        $(".table-responsive").shouldHave(text("Gender")).shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("Mobile")).shouldHave(text("0987654321"));
        $(".table-responsive").shouldHave(text("Date of Birth")).shouldHave(text("22 May,2000"));
        $(".table-responsive").shouldHave(text("Subjects")).shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Hobbies")).shouldHave(text("Hobbies"));
        $(".table-responsive").shouldHave(text("Picture"));
        $(".table-responsive").shouldHave(text("State and City")).shouldHave(text("Haryana Panipat"));
    }

}
