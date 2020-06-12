import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	
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
	public void interagirTextField() {
		dsl.escreve("elementosForm:nome", "Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome"));		
	}
	
	@Test
	public void interagirTextArea(){
		dsl.escreve("elementosForm:sugestoes", "Teste\\n\\nNova Linha\\nÚltima Linha");
		Assert.assertEquals("Teste", dsl.obterValorCampo("elementosForm:sugestoes"));		
	}
	
	@Test
	public void interagirRadioButton(){
		dsl.clicar("elementosForm:sexo:0");
		assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));				
	}
	
	@Test
	public void interagirCheckBox(){
		dsl.clicar("elementosForm:comidaFavorita:0");
		assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:0"));				
	}
	
	@Test
	public void interagirComboBox(){
		dsl.selecionarCombo("elementosForm:escolaridade", "1o grau completo");
		Assert.assertEquals("1o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));		
	}
	
	@Test
	public void validarComboBox(){
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
	}
	
	@Test
	public void interagirComboMulti(){
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		List<WebElement> allSectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSectedOptions.size());
	}
	
	@Test
	public void interagirButtonSimple(){
		dsl.clicar("buttonSimple");
				
		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
	}
	
	@Test
	public void interagirLink(){
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto(By.id("resultado")));
	}
	
	@Test
	public void buscarTextoPagina(){
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
}
