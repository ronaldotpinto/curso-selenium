import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesJanelas {
	
	String url = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void interagirFrames(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlert = alert.getText();
		Assert.assertEquals("Frame OK!", textoAlert);
		alert.accept();
		
		driver.switchTo().parentFrame();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlert);
		
		driver.quit();
	}
	
	@Test
	public void interagirPopups(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
	
	@Test
	public void interagirPopupsGenericas(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
	
}
