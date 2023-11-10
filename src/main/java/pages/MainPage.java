package pages;

import annotations.UrlPrefix;
import components.HeaderMenuComponent;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    @Step("Проверяем, что ссылка соответсвует шаблону")
    public void checkHref(String link, String xpath) {

        WebElement parentFaq = driver.findElement(By.xpath(xpath));
        Assertions.assertEquals(link, parentFaq.getAttribute("href"), "ссылки не совпадают");


    }
}
