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
