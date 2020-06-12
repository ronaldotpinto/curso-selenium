import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void validarCadastroSimples(){		
		dsl.escreve("elementosForm:nome", "Ronaldo");
		dsl.escreve("elementosForm:sobrenome", "Pinto");
		dsl.clicar("elementosForm:sexo:0");		
		dsl.clicar("elementosForm:comidaFavorita:0");
		dsl.selecionarValorCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarValorCombo("elementosForm:esportes", "Corrida");
		dsl.clicar("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto(By.id("resultado")).startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Ronaldo", dsl.obterTexto(By.id("descNome")));
		Assert.assertEquals("Sobrenome: Pinto", dsl.obterTexto(By.id("descSobrenome")));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto(By.id("descSexo")));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto(By.id("descComida")));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto(By.id("descEscolaridade")));
		Assert.assertEquals("Esportes: Corrida", dsl.obterTexto(By.id("descEsportes")));
		Assert.assertEquals("Esportes: Corrida", dsl.obterTexto(By.id("descEsportes")));
	}
	
	@Test
	public void validarNomeObrigatorio(){		
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.textoAlertAccept());
	}
	
	@Test
	public void validarSobreNomeObrigatorio(){		
		dsl.escreve("elementosForm:nome", "Ronaldo");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.textoAlertAccept());
	}
	
	@Test
	public void validarSexoObrigatorio(){		
		dsl.escreve("elementosForm:nome", "Ronaldo");
		dsl.escreve("elementosForm:sobrenome", "Pinto");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.textoAlertAccept());
	}
	
	@Test
	public void validarComidaVegetariana(){		
		dsl.escreve("elementosForm:nome", "Ronaldo");
		dsl.escreve("elementosForm:sobrenome", "Pinto");
		dsl.clicar("elementosForm:sexo:0");
		dsl.clicar("elementosForm:comidaFavorita:0");
		dsl.clicar("elementosForm:comidaFavorita:3");
		dsl.clicar("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.textoAlertAccept());
	}
	
	@Test
	public void validarPraticaEsportiva(){		
		dsl.escreve("elementosForm:nome", "Ronaldo");
		dsl.escreve("elementosForm:sobrenome", "Pinto");
		dsl.clicar("elementosForm:sexo:0");
		dsl.clicar("elementosForm:comidaFavorita:0");
		dsl.selecionarValorCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarValorCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicar("elementosForm:cadastrar");
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.textoAlertAccept());
	}
}
