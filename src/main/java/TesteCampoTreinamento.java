import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	String url = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
	
	@Test
	public void interagirTextField() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(url);		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void interagirTextArea(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(url);
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste\n\nNova Linha\nÚltima Linha");		
		Assert.assertEquals("Teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void interagirRadioButton(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(url);
		driver.findElement(By.id("elementosForm:sexo:0")).click();		
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());		
		
		driver.quit();
	}
	
	@Test
	public void interagirCheckBox(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(url);
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();		
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());		
		
		driver.quit();
	}
	
	@Test
	public void interagirComboBox(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,  765));
		driver.get(url);
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(3);
		//combo.selectByValue("2grauincomp");
		combo.selectByVisibleText("1o grau completo");
		Assert.assertEquals("1o grau completo", combo.getFirstSelectedOption().getText());
		
		driver.quit();
	}
	
	@Test
	public void validarComboBox(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(url);
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		Boolean encontrou = false;
		
		for(WebElement option: options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
		driver.quit();
	}
	
	@Test
	public void interagirComboMulti(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSectedOptions.size());
		driver.quit();
	}
	
	@Test
	public void interagirButtonSimple(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
				
		driver.findElement(By.id("buttonSimple"));
	}
}
