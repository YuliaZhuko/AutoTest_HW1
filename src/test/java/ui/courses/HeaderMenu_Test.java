package ui.courses;

import annotations.Driver;
import components.HeaderMenuComponent;
import data.menu.CourcesData;
import data.menu.MenuItemData;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class HeaderMenu_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void selectCourseFromMenu() {

    new MainPage(driver).open();

    new HeaderMenuComponent(driver)
            .checkSubMenuListNotVisible(MenuItemData.Courses)
            .moveToMenuItem(MenuItemData.Courses)
            .checkSubMenuListVisible(MenuItemData.Courses)
            .clickCourseItem(CourcesData.Pyton_Developer)
            .checkDateOfStart();
  }
  @Test
  public void scrollToElement() {
    String href = "https://otus.ru/faq";
    String xpath ="//a/div[text()='FAQ']/..";
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    WebElement faqElement = driver.findElement(By.xpath("//a/div[text()='FAQ']"));
    Actions actions = new Actions(driver);
    actions.scrollToElement(faqElement).perform();
    mainPage.checkHref(href,xpath);


  }
  @Test
  public void  moveToElement (){
    String href = "https://otus.ru/contacts";
    String xpath ="//*[@id=\"__next\"]//div[5]//a[6]";
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    WebElement informationItemElement = driver.findElement(By.xpath("//span[text()='Информация']"));
    Actions actions = new Actions(driver);
    actions.moveToElement(informationItemElement)
            .perform();
    mainPage.checkHref(href,xpath);
  }
}