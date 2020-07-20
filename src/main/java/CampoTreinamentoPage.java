import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);		
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicar("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicar("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicar("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicar("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicar("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicar("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String escolaridade) {
		dsl.selecionarValorCombo("elementosForm:escolaridade", escolaridade);
	}
	
	public void setEsportes(String... esportes) {
		for (String esporte : esportes) {
			dsl.selecionarValorCombo("elementosForm:esportes", esporte);
		}
	}
	
	public void cadastrar() {
		dsl.clicar("elementosForm:cadastrar");
	}
	
	public String getResultadoCadastro() {
		return dsl.obterTexto(By.id("resultado"));
	}
	
	public String getNomeCadastro() {
		return dsl.obterTexto(By.id("descNome"));
	}
	
	public String getSobreomeCadastro() {
		return dsl.obterTexto(By.id("descSobrenome"));
	}
	
	public String getSexoCadastro() {
		return dsl.obterTexto(By.id("descSexo"));
	}
	
	public String getComidaCadastro() {
		return dsl.obterTexto(By.id("descComida"));
	}
	
	public String getEscolaridadeCadastro() {
		return dsl.obterTexto(By.id("descEscolaridade"));
	}
	
	public String getEsporteCadastro() {
		return dsl.obterTexto(By.id("descEsportes"));
	}

}