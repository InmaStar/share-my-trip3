package com.sdi.tests.Tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdi.tests.pageobjects.POLoginForm;
import com.sdi.tests.pageobjects.POSignupForm;
import com.sdi.tests.utils.SeleniumHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SDI2_Tests {
	private static final String url = "http://localhost:8280/sdi2-23/";
	private WebDriver driver;
	private SeleniumHelper helper;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get(url);
		helper = new SeleniumHelper(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	// 1. [RegVal] Registro de Usuario con datos válidos.
	@Test
	public void t01_RegVal() {
		helper.clickNavOption("main-nav:registrarse");
		driver.findElement(By.id("restore-db")).click();
		helper.clickNavOption("main-nav:registrarse");
		
		String username = "usuario10001";
		new POSignupForm(driver, username, username, username,
				username, username, username+"@example.org").submit();
		helper.waitForId("viajes");
		assertTrue(helper.elementContains("main-nav:rol", username));
	}

	// 2. [RegInval] Registro de Usuario con datos inválidos (contraseñas
	// diferentes).
	@Test
	public void t02_RegInval() {
		helper.clickNavOption("main-nav:registrarse");
		String username = "usuario10001";
		new POSignupForm(driver, username, username, username+"1",
				username, username, username+"@example.org").submit();
		helper.waitForText("The passwords are different");
	}

	// 3. [IdVal] Identificación de Usuario registrado con datos válidos.
	@Test
	public void t03_IdVal() {
		helper.clickNavOption("main-nav:validarse");
		new POLoginForm(driver, "usuario1", "usuario1").submit();
		helper.waitForId("viajes");
		assertTrue(helper.elementContains("main-nav:rol", "usuario1"));
	}

	// 4. [IdInval] Identificación de usuario registrado con datos inválidos.
	@Test
	public void t04_IdInval() {
		helper.clickNavOption("main-nav:validarse");
		new POLoginForm(driver, "usuario1", "usuario2").submit();
		helper.waitForText("The password is incorrect");
	}

	// 5. [AccInval] Intento de acceso con URL desde un usuario no público (no
	// identificado). Intento de acceso a vistas de acceso privado.
	@Test
	public void t05_AccInval() {

	}

	// 6. [RegViajeVal] Registro de un viaje nuevo con datos válidos.
	@Test
	public void t06_RegViajeVal() {

	}

	// 7. [RegViajeInVal] Registro de un viaje nuevo con datos inválidos.
	@Test
	public void t07_RegViajeInVal() {

	}

	// 8. [EditViajeVal] Edición de viaje existente con datos válidos.
	@Test
	public void t08_EditViajeVal() {

	}

	// 9. [EditViajeInVal] Edición de viaje existente con datos inválidos.
	@Test
	public void t09_EditViajeInVal() {

	}

	// 10. [CancelViajeVal] Cancelación de un viaje existente por un promotor.
	@Test
	public void t10_CancelViajeVal() {

	}

	// 11. [CancelMulViajeVal] Cancelación de múltiples viajes existentes por un
	// promotor.
	@Test
	public void t11_CancelMulViajeVal() {

	}

	// 12. [Ins1ViajeAceptVal] Inscribir en un viaje un solo usuario y ser
	// admitido por el promotor.
	@Test
	public void t12_Ins1ViajeAceptVal() {

	}

	// 13. [Ins2ViajeAceptVal] Inscribir en un viaje dos usuarios y ser
	// admitidos los dos por el promotor.
	@Test
	public void t13_Ins2ViajeAceptVal() {

	}

	// 14. [Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo) dos
	// usuarios y ser admitidos los dos y que un tercero intente inscribirse en
	// ese mismo viaje pero ya no pueda por falta de plazas.
	@Test
	public void t14_Ins3ViajeAceptInval() {

	}

	// 15. [CancelNoPromotorVal] Un usuario no promotor Cancela plaza.
	@Test
	public void t15_CancelNoPromotorVal() {

	}

	// 16. [Rech1ViajeVal] Inscribir en un viaje un usuario que será admitido y
	// después rechazarlo por el promotor.
	@Test
	public void t16_Rech1ViajeVal() {

	}

	// 17. [i18N1] Cambio del idioma por defecto a un segundo idioma. (Probar
	// algunas vistas)
	@Test
	public void t17_i18N1() {

	}

	// 18. [i18N2] Cambio del idioma por defecto a un segundo idioma y vuelta al
	// idioma por defecto. (Probar algunas vistas)
	@Test
	public void t18_i18N2() {

	}

	// 19. [OpFiltrado] Prueba para el filtrado opcional.
	@Test
	public void t19_OpFiltrado() {

	}

	// 20. [OpOrden] Prueba para la ordenación opcional.
	@Test
	public void t20_OpOrden() {

	}

	// 21. [OpPag] Prueba para la paginación opcional.
	@Test
	public void t21_OpPag() {

	}

	// 22. [OpMante] Prueba del mantenimiento programado opcional.

	@Test
	public void t22_OpMante() {

	}
}