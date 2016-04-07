package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POTripForm {
	private WebDriver driver;
	
	public POTripForm(WebDriver driver) {
		this.driver = driver;
	}

	public void fillForm() {
		driver.findElement(By.id("trip-form:precarga")).click();
	}


	public void invalidateForm() {
		WebElement element = driver.findElement(By.id("trip-form:calleSalida"));
		element.click();
		element.clear();
	}
	
	public void submitForm() {
		driver.findElement(By.id("trip-form:submit")).click();
	}

}
