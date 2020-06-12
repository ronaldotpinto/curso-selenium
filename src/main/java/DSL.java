import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve(String id, String valor) {
		driver.findElement(By.id(id)).sendKeys(valor);
	}
	
	public String obterValorCampo(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public void clicar(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public Boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarLink(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

}
