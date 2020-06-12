import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteAlert {
	
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
	public void interagirAlertSimples(){
		dsl.clicar("alert");
		String textoAlert = dsl.textoAlertAccept();
		Assert.assertEquals("Alert Simples", textoAlert);
		dsl.escreve("elementosForm:nome", textoAlert);
	}
	
	@Test
	public void interagirAlertConfirm(){
		dsl.clicar("confirm");
		Assert.assertEquals("Confirm Simples", dsl.textoAlertAccept());
		Assert.assertEquals("Confirmado", dsl.textoAlertAccept());
		
		dsl.clicar("confirm");
		Assert.assertEquals("Confirm Simples", dsl.textoAlertDismiss());
		Assert.assertEquals("Negado", dsl.textoAlertAccept());
	}
	
	@Test
	public void interagirAlertPrompt(){		
		dsl.clicar("prompt");
		Assert.assertEquals("Digite um numero", dsl.textoAlertAccept("12"));
		Assert.assertEquals("Era 12?", dsl.textoAlertAccept());
		Assert.assertEquals(":D", dsl.textoAlertAccept());
	}
	
}
