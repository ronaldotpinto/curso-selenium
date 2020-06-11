import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	String url = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
	
	@Test
	public void validarCadastroSimples(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ronaldo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pinto");
		driver.findElement(By.id("elementosForm:sexo:0")).click();		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade")))
			.selectByVisibleText("Superior");
		new Select(driver.findElement(By.id("elementosForm:esportes")))
			.selectByVisibleText("Corrida");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Ronaldo", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Pinto", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Corrida", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Esportes: Corrida", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit();
	}
	
	@Test
	public void validarNomeObrigatorio(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void validarSobreNomeObrigatorio(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ronaldo");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void validarSexoObrigatorio(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ronaldo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pinto");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void validarComidaVegetariana(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ronaldo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pinto");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void validarPraticaEsportiva(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1220, 765));
		driver.get(url);
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ronaldo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pinto");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:esportes")))
		.selectByVisibleText("Corrida");
		new Select(driver.findElement(By.id("elementosForm:esportes")))
		.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
		
		driver.quit();
	}
}
