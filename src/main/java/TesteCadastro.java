import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void validarCadastroSimples(){		
		page.setNome("Ronaldo");
		page.setSobrenome("Pinto");
		page.setSexoMasculino();		
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsportes("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.getResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Ronaldo", page.getNomeCadastro());
		Assert.assertEquals("Sobrenome: Pinto",page.getSobreomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.getSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.getComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.getEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.getEsporteCadastro());
	}
}
