package br.com.ronaldopinto.test;
import static br.com.ronaldopinto.core.DriverFactory.getDriver;
import static br.com.ronaldopinto.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.ronaldopinto.core.DSL;

public class TesteFramesJanelas {

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
	public void interagirFrames() {
		dsl.alterarFrame("frame1");
		dsl.clicar("frameButton");
		String textoAlert = dsl.textoAlertAccept();
		Assert.assertEquals("Frame OK!", textoAlert);

		dsl.alterarFramePadrao();
		dsl.escreve("elementosForm:nome", textoAlert);
	}

	@Test
	public void interagirFrameOculto() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);

		dsl.alterarFrame("frame2");
		dsl.clicar("frameButton");
		String msg = dsl.textoAlertAccept();
		Assert.assertEquals("Frame OK!", msg);
	}

	@Test
	public void interagirPopups() {
		dsl.clicar("buttonPopUpEasy");
		dsl.alterarJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		dsl.fecharJanelaAtual();

		dsl.alterarJanela("");
		dsl.escreve(By.tagName("textarea"), "e agora?");
	}

	@Test
	public void interagirPopupsGenericas() {
		dsl.clicar("buttonPopUpHard");
		dsl.alterarJanela(getDriver().getWindowHandles().toArray()[1].toString());
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		dsl.alterarJanela(getDriver().getWindowHandles().toArray()[0].toString());
		dsl.escreve(By.tagName("textarea"), "e agora?");
	}

}
