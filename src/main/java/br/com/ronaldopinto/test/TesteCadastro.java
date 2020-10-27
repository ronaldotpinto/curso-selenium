package br.com.ronaldopinto.test;

import static br.com.ronaldopinto.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.ronaldopinto.core.BaseTest;
import br.com.ronaldopinto.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {

	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@Test
	public void validarCadastroSimples() {
		page.setNome("Ronaldo");
		page.setSobrenome("Pinto");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsportes("Natacao");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.getResultadoCadastro());
		Assert.assertEquals("Ronaldo", page.getNomeCadastro());
		Assert.assertEquals("Pinto", page.getSobreomeCadastro());
		Assert.assertEquals("Masculino", page.getSexoCadastro());
		Assert.assertEquals("Pizza", page.getComidaCadastro());
		Assert.assertEquals("mestrado", page.getEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.getEsporteCadastro());
	}
}
