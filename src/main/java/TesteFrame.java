import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrame {
	
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
}
