package br.com.ronaldopinto.test;

import static br.com.ronaldopinto.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.ronaldopinto.core.BaseTest;
import br.com.ronaldopinto.core.DSL;
import br.com.ronaldopinto.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Ronaldo", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Ronaldo", "Pinto", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Ronaldo", "Pinto", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?" },
				{ "Ronaldo", "Pinto", "Masculino", Arrays.asList("Carne"), new String[] { "Karate", "O que eh esporte?" }, "Voce faz esporte ou nao?" } });
	}

	@Test
	public void validarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} else if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}

		if (comidas.contains("Carne"))
			page.setComidaCarne();
		if (comidas.contains("Pizza"))
			page.setComidaPizza();
		if (comidas.contains("Vegetariano"))
			page.setComidaVegetariano();

		page.setEsportes(esportes);
		page.cadastrar();

		Assert.assertEquals(msg, dsl.textoAlertAccept());
	}

}
