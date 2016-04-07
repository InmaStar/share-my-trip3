package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POSignupForm {
	private WebDriver driver;
	private String login;
	private String password;
	private String repeatPassword;
	private String name;
	private String surname;
	private String email;
	
	public POSignupForm(WebDriver driver, String login, String password,
			String repeatPassword, String name, String surname, String email) {
		this.driver = driver;
		this.login = login;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public void submit() {
		fillField("signup-form:login", login);
		fillField("signup-form:password1", password);
		fillField("signup-form:password2", repeatPassword);
		fillField("signup-form:nombre", name);
		fillField("signup-form:apellidos", surname);
		fillField("signup-form:email", email);
		
		By boton = By.id("signup-form:submit");
		driver.findElement(boton).click();
	}
	
	private void fillField(String fieldName, String value) {
		WebElement element = driver.findElement(By.id(fieldName));
		element.click();
		element.clear();
		element.sendKeys(value);
	}
	
}
