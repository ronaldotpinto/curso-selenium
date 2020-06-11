import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteAlert {
	

	String url = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
	
	@Test
	public void buscarTextoPagina(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlert = alert.getText();
		
		Assert.assertEquals("Alert Simples", textoAlert);
		alert.accept(); 
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlert);
		
		driver.quit();
	}
	
}
