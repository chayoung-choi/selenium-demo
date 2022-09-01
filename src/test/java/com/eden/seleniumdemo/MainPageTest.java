package com.eden.seleniumdemo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * https://selenide.org/documentation/screenshots.html
 * 자동 스크린샷은 shouldBe 실패 경우 지원
 * @ExtendWith({ScreenShooterExtension.class}) 설정 시 : shouldBe, assertEquals, assertThat의 실패 경우 지원(Allure 자동 첨부파일 안됨)
 */
/**
 * * 테스트 순서 지정
 * @TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
 * @Order(1)
 */

public class MainPageTest {
  MainPage mainPage = new MainPage();

  @BeforeAll
  public static void setUpAll() {
    Configuration.browserSize = "1280x800";
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @BeforeEach
  public void setUp() {
    open("https://www.jetbrains.com/");
  }

  @Test
  public void search() {
    mainPage.searchButton.click();

    $("[data-test='search-input']").sendKeys("Selenium");
    $("button[data-test='full-search-button']").click();

    $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
  }

  @Test
  public void toolsMenu() {
    mainPage.toolsMenu.click();

    $("div[data-test='main-submenu']").shouldBe(visible);
  }

  @Test
  public void navigationToAllTools() {
    mainPage.seeAllToolsButton.click();

    $("#products-page").shouldBe(visible);

    assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
  }
}
