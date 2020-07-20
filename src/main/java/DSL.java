import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve(String id, String valor) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(valor);
	}
	
	public void escreve(By by, String valor) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(valor);
	}
	
	public String obterValorCampo(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public String obterTextoCampo(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	
	public void clicar(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public Boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public int obterQuantidadeOptionsCombo(By by) {
		Select combo = new Select(driver.findElement(by));
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean validarOptionsCombo(By by, String opcao) {
		Boolean encontrou = false;
		Select combo = new Select(driver.findElement(by));
		List<WebElement> options = combo.getOptions();
		
		for(WebElement option: options) {
			if (option.getText().equals(opcao)) {
				encontrou = true;
				break;
			}
		}
		
		return encontrou;
	}
	
	public List<WebElement> obterOpcaoesMarcadasCombo(By by) {
		Select combo = new Select(driver.findElement(by));
		List<WebElement> options = combo.getAllSelectedOptions();
		return options;
	}
	
	public void selecionarValorCombo(String id, String valor) {
		new Select(driver.findElement(By.id(id)))
		.selectByVisibleText(valor);
	}
	
	public void deselecionarValorCombo(String id, String valor) {
		new Select(driver.findElement(By.id(id)))
		.deselectByVisibleText(valor);
	}
	
	public String obterValorSelecionadoCombo(String id) {
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
	
	public String textoAlertAccept() {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.accept();
		return texto;
	}
	
	public String textoAlertAccept(String textArea) {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.sendKeys(textArea);
		alert.accept();
		return texto;
	}
	
	public String textoAlertDismiss() {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.dismiss();
		return texto;
	}
	
	/*********** Frames e Janelas ****************/
	public void alterarFrame(String frame) {
		driver.switchTo().frame(frame);		
	}
	
	public void alterarFramePadrao() {
		driver.switchTo().defaultContent();		
	}
	
	public void alterarJanela(String janela) {
		driver.switchTo().window(janela);
	}
	
	public void fecharJanelaAtual() {
		driver.close();
	}
	
	/*********************** JS ************************/
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
}
