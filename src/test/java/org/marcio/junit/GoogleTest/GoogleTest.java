package org.marcio.junit.GoogleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

//Ordenar os testes por ordem alfabética @FixMethodOrder(MethodSorters.NAME_ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoogleTest {

	// Instanciando o objeto do Selenium WebDriver pra manipular o Browser
	private WebDriver driver;

	// Configurando os testes com a classe Before do Junit (opcional)
	@Before
	public void Setup() {

		// Abrir o Chrome
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		System.out.println("Maximizando a janela \n");
		driver.manage().window().maximize();

		System.out.println("Abrindo o site \n");
		driver.navigate().to("https://www.google.com/ \n");
	}

	// Iniciando um Test com a Anotação @Test do Junit
	@Test
	public void PesquisaGoogle() {

		System.out.println("*** Iniciando o Teste 1 *** \n");

		String TermoParaPesquisar = "Queijo";

		try {

			System.out.println("Localizando a caixa de texto e digitando nela \n");
			driver.findElement(By.name("q")).sendKeys(TermoParaPesquisar);

			System.out.println("Após digitar dê um ENTER para pesquisar \n");
			driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			System.out.println("Comparando o que foi pesquisado com o que esta na caixa de texto \n");
			assertEquals(true, driver.getPageSource().contains(TermoParaPesquisar) == true);

		} catch (InterruptedException e) {
			System.out.println("Não foi possivel localizar o elemento para pesquisar \n");
		}

	}

	// Iniciando um Test com a Anotação @Test do Junit

	@Test
	public void VerificandoSeEstaLogado() throws InterruptedException {
		System.out.println("*** Iniciando o Teste 2 *** \n");

		Thread.sleep(3000);
		if (driver.getPageSource().contains("Fazer login") == true) {

			System.out.println(" Usuário não esta logado na conta do Google \n");
		} else {
			System.out.println(" Usuário esta logado na conta do Google \n");
		}

	}

	// Concluindo o Test com a Anotação @After do Junit

	@After
	public void endTest() {
		System.out
				.println("Encerrando o teste, fechando o navegador e matando o processo no Gerenciador de Tarefas \n");

		driver.quit();

	}

}
