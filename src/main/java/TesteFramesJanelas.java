import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesJanelas {
	
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
	public void interagirFrames(){		
		dsl.alterarFrame("frame1");
		dsl.clicar("frameButton");
		String textoAlert = dsl.textoAlertAccept();
		Assert.assertEquals("Frame OK!", textoAlert);
		
		dsl.alterarFramePadrao();
		dsl.escreve("elementosForm:nome", textoAlert);		
	}
	
	@Test
	public void interagirPopups(){		
		dsl.clicar("buttonPopUpEasy");
		dsl.alterarJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		dsl.fecharJanelaAtual();
		
		dsl.alterarJanela("");
		dsl.escreve(By.tagName("textarea"), "e agora?");		
	}
	
	@Test
	public void interagirPopupsGenericas(){		
		dsl.clicar("buttonPopUpHard");
		dsl.alterarJanela(driver.getWindowHandles().toArray()[1].toString());
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		dsl.alterarJanela(driver.getWindowHandles().toArray()[0].toString());
		dsl.escreve(By.tagName("textarea"), "e agora?");		
	}
	
}
