package com.sdi.tests.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POLoginForm {
	private WebDriver driver;
	private String login;
	private String password;
	
	public POLoginForm(WebDriver driver, String login, String password) {
		this.driver = driver;
		this.login = login;
		this.password = password;
	}
	
	public void submit() {
		fillField("login-form:login", login);
		fillField("login-form:password", password);
		
		By boton = By.id("login-form:submit");
		driver.findElement(boton).click();
	}
	
	private void fillField(String fieldName, String value) {
		WebElement element = driver.findElement(By.id(fieldName));
		element.click();
		element.clear();
		element.sendKeys(value);
	}
	
}
