package com.sdi.tests.utils;

import java.util.List;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {
	private WebDriver driver;
	
	public SeleniumHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickNavOption(String option) {
		By locator = By.id(option);
		driver.findElement(locator).click();
	}
	
	public void clickSubnavOption(String submenu, String opcionclick) {
		Actions builder = new Actions(driver);
		WebElement hoverElement = driver.findElement(By.id(submenu));
		builder.moveToElement(hoverElement).perform();
		By locator = By.id(opcionclick);
		driver.findElement(locator).click();
	}

	public boolean textExists(String texto) {
		List<WebElement> list = driver.findElements(By
				.xpath("//*[contains(text(),'" + texto + "')]"));
		return list.size() > 0;
	}

	public boolean elementContains(String element, String text) {
		return driver.findElement(By.id(element)).getText().equals(text);
	}

	public void waitForId(String id) {
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(
						By.id(id)));
	}
}
