import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeAjax() {
		dsl.escreve("j_idt727:name", "Texto");
		dsl.clicar("j_idt727:j_idt730");
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt727:display"), "Texto"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//i[contains(@class, 'pi-spinner')]")));
		Assert.assertEquals("Texto", dsl.obterTextoCampo("j_idt727:display"));
	}

}
