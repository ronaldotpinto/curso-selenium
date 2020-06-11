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
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void interagirFrames(){		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlert = alert.getText();
		Assert.assertEquals("Frame OK!", textoAlert);
		alert.accept();
		
		driver.switchTo().parentFrame();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlert);		
	}
	
	@Test
	public void interagirPopups(){		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");		
	}
	
	@Test
	public void interagirPopupsGenericas(){		
		driver.findElement(By.id("buttonPopUpHard")).click();
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");		
	}
	
}
