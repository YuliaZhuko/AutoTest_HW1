package components;

import annotations.Component;
import data.menu.CourcesData;
import data.menu.MenuItemData;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.LessonsPage;


@Component("//*[contains(@class, 'header2-menu_main')]")
public class HeaderMenuComponent extends AnyComponentAbs<HeaderMenuComponent> {

    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }

    private String menuItemLocator = "//span[text()='Обучение']";
    private String menuItemDropdownListLocator = "//a[text()='%s']";

    public HeaderMenuComponent moveToMenuItem(MenuItemData menuItemData) {
        WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator)));
        actions.moveToElement(menuItemElement).build().perform();

        return this;
    }

    public HeaderMenuComponent checkSubMenuListVisible(MenuItemData menuItemData) {
        assert standartWaiter.waitForElementVisible(
                driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, menuItemData.getName())))
        ) : "Sub menu not visible";

        return this;
    }

    @Step("Проверяем, что подменю {menuItemData} не отображается на странице")
    public HeaderMenuComponent checkSubMenuListNotVisible(MenuItemData menuItemData) {
        assert standartWaiter.waitForElementNotVisible(
                driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, menuItemData.getName())))
        ) : "Sub menu visible";

        return this;
    }

    public HeaderMenuComponent clickCourseItem(CourcesData courcesData) {

        String baseCourseTypeLocator = "//a[text()='%s']";
        WebElement baseCourceElement = driver.findElement(By.xpath(String.format(baseCourseTypeLocator, MenuItemData.Courses.getName())));

        actions.moveToElement(baseCourceElement).build().perform();
        driver.findElement(By.xpath("//a[text()='Программирование']")).click();
        driver.findElement(By.xpath(String.format("//h6/div[text()='%s']", courcesData.getName()))).click();

        return this;
    }

    public HeaderMenuComponent checkDateOfStart() {
        String startDate = "28 ноября";
        WebElement CDeveloperStartDate = driver.findElement(By.xpath("//div[3]/div/div[1]/p"));
        Assertions.assertEquals(startDate, CDeveloperStartDate.getText(), "Даты начала не совпадают");
        return this;
    }





}

