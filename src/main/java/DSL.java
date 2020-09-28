
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

	/********* TextField e TextArea ************/

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

	/********* Radio e Check ************/

	public void clicar(String id) {
		driver.findElement(By.id(id)).click();
	}

	public Boolean isOpcaoMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	/********* Combo ************/

	public int obterQuantidadeOptionsCombo(By by) {
		Select combo = new Select(driver.findElement(by));
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean validarOptionsCombo(By by, String opcao) {
		Boolean encontrou = false;
		Select combo = new Select(driver.findElement(by));
		List<WebElement> options = combo.getOptions();

		for (WebElement option : options) {
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
		new Select(driver.findElement(By.id(id))).selectByVisibleText(valor);
	}

	public void deselecionarValorCombo(String id, String valor) {
		new Select(driver.findElement(By.id(id))).deselectByVisibleText(valor);
	}

	public String obterValorSelecionadoCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	/********* Link ************/

	public void clicarLink(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}

	/********* Textos ************/

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	/********* Alerts ************/

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

	/*********************** Tabela ************************/

	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		celula.findElement(By.xpath(".//input")).click();

	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}
}
