import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSincronismo {
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void interagirEsperaFixa() throws InterruptedException {
		dsl.clicar("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu Certo?");
	}

	@Test
	public void interagirEsperaImplicita() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicar("buttonDelay");
		dsl.escreve("novoCampo", "Deu Certo?");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void interagirEsperaExplicita() {
		dsl.clicar("buttonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu Certo?");
	}

}
