package br.com.ronaldopinto.test;
import static br.com.ronaldopinto.core.DriverFactory.getDriver;
import static br.com.ronaldopinto.core.DriverFactory.killDriver;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.com.ronaldopinto.core.DSL;

public class TesteCampoTreinamento {

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
	public void interagirTextField() {
		dsl.escreve("elementosForm:nome", "Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void interagirTextFieldDuplo() {
		dsl.escreve("elementosForm:nome", "Ronaldo");
		Assert.assertEquals("Ronaldo", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Trindade");
		Assert.assertEquals("Trindade", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void interagirTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste\n\nNova Linha\n�ltima Linha");
		Assert.assertEquals("Teste\n\nNova Linha\n�ltima Linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}

	@Test
	public void interagirRadioButton() {
		dsl.clicar("elementosForm:sexo:0");
		assertTrue(dsl.isOpcaoMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void interagirCheckBox() {
		dsl.clicar("elementosForm:comidaFavorita:0");
		assertTrue(dsl.isOpcaoMarcado("elementosForm:comidaFavorita:0"));
	}

	@Test
	public void interagirComboBox() {
		dsl.selecionarValorCombo("elementosForm:escolaridade", "1o grau completo");
		Assert.assertEquals("1o grau completo", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));
	}

	@Test
	public void validarComboBox() {
		Assert.assertEquals(8, dsl.obterQuantidadeOptionsCombo(By.id("elementosForm:escolaridade")));
		Assert.assertTrue(dsl.validarOptionsCombo(By.id("elementosForm:escolaridade"), "Mestrado"));
	}

	@Test
	public void interagirComboMulti() {
		dsl.selecionarValorCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarValorCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarValorCombo("elementosForm:esportes", "O que eh esporte?");

		List<WebElement> opcoesMarcadas = dsl.obterOpcaoesMarcadasCombo(By.id("elementosForm:esportes"));
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.deselecionarValorCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterOpcaoesMarcadasCombo(By.id("elementosForm:esportes"));
		Assert.assertEquals(2, opcoesMarcadas.size());
	}

	@Test
	public void interagirButtonSimple() {
		dsl.clicar("buttonSimple");

		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
	}

	@Test
	public void interagirLink() {
		dsl.clicarLink("Voltar");

		Assert.assertEquals("Voltou!", dsl.obterTexto(By.id("resultado")));
	}

	@Test
	public void buscarTextoPagina() {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

	@Test
	public void testeJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Testando JS via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void clicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
		Assert.assertEquals("Maria", dsl.textoAlertAccept());
	}
}
