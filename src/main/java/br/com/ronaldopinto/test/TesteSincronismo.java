package br.com.ronaldopinto.test;
import static br.com.ronaldopinto.core.DriverFactory.getDriver;
import static br.com.ronaldopinto.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.ronaldopinto.core.DSL;

public class TesteSincronismo {
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void interagirEsperaFixa() throws InterruptedException {
		dsl.clicar("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu Certo?");
	}

	@Test
	public void interagirEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicar("buttonDelay");
		dsl.escreve("novoCampo", "Deu Certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void interagirEsperaExplicita() {
		dsl.clicar("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu Certo?");
	}

}
