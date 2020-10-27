package br.com.ronaldopinto.test;
import static br.com.ronaldopinto.core.DriverFactory.getDriver;
import static br.com.ronaldopinto.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.ronaldopinto.core.DSL;

public class TesteAjax {
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void testeAjax() {
		dsl.escreve("j_idt727:name", "Texto");
		dsl.clicar("j_idt727:j_idt730");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt727:display"), "Texto"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[contains(@class, 'pi-spinner')]")));
		Assert.assertEquals("Texto", dsl.obterTextoCampo("j_idt727:display"));
	}

}
