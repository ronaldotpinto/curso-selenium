package br.com.ronaldopinto.suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.ronaldopinto.core.DriverFactory;
import br.com.ronaldopinto.test.TesteCadastro;
import br.com.ronaldopinto.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ TesteCadastro.class, TesteRegrasCadastro.class, })

public class SuiteTeste {
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
}
