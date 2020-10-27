package br.com.ronaldopinto.test;
import static br.com.ronaldopinto.core.DriverFactory.getDriver;
import static br.com.ronaldopinto.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.ronaldopinto.core.DSL;

public class TesteAlert {
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
	public void interagirAlertSimples() {
		dsl.clicar("alert");
		String textoAlert = dsl.textoAlertAccept();
		Assert.assertEquals("Alert Simples", textoAlert);
		dsl.escreve("elementosForm:nome", textoAlert);
	}

	@Test
	public void interagirAlertConfirm() {
		dsl.clicar("confirm");
		Assert.assertEquals("Confirm Simples", dsl.textoAlertAccept());
		Assert.assertEquals("Confirmado", dsl.textoAlertAccept());

		dsl.clicar("confirm");
		Assert.assertEquals("Confirm Simples", dsl.textoAlertDismiss());
		Assert.assertEquals("Negado", dsl.textoAlertAccept());
	}

	@Test
	public void interagirAlertPrompt() {
		dsl.clicar("prompt");
		Assert.assertEquals("Digite um numero", dsl.textoAlertAccept("12"));
		Assert.assertEquals("Era 12?", dsl.textoAlertAccept());
		Assert.assertEquals(":D", dsl.textoAlertAccept());
	}

}
