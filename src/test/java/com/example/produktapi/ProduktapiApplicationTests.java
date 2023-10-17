package com.example.produktapi;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ProduktapiApplicationTests {
	//Gör beforeEach och AfterEach

	@Test
	void checkIfTheTitleIsCorrect() {
		//Hämta in den webDriver som ska användas och gör en instans
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);

		//Navigeras till den webbsida som ska användas
		driver.get("https://java22.netlify.app/");

		//Hämta titel och kontrollera om förväntad titel matchar webbplatsen
		assertEquals("Webbutik", driver.getTitle(), "Titeln stämmer inte med förväntat");

	}

	@Test
	void checkingIfTheTotalAMountOfProductsIsCorrect() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		driver.get("https://java22.netlify.app/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> products = driver.findElements(By.className("productItem"));

		//Försökte använda mig av variabeln items.size() istället för ett hårdkodat värde men det gick inte.
		assertEquals(20,products.size(),"Antalet matchar inte!");
		driver.quit();
	}

	@Test
	void checkingIfThreeDifferentProductsHaveTheCorrectPrice() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		driver.get("https://java22.netlify.app/");

		//Det här är priset för de 3 olika produkterna
		String priceForProduct1 = "56.99";
		String priceForProduct2 = "599";
		String priceForProduct3 = "39.99";

		String firstProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[15]/div/div/p")).getText();
		String secondProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[13]/div/div/p")).getText();
		String thirdProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[17]/div/div/p")).getText();

        //Med contains kollar vi om de produkterna vi testar innehåller rätt pris
		boolean confirmTheSecondProductsPrice = firstProduct.contains(priceForProduct1);
		boolean confirmTheFirstProductsPrice = secondProduct.contains(priceForProduct2);
		boolean confirmTheThirdProductsPrice = thirdProduct.contains(priceForProduct3);

		//Returnerar sedan true om priset stämmer på produkterna
		assertTrue(confirmTheFirstProductsPrice);
		assertTrue(confirmTheSecondProductsPrice);
		assertTrue(confirmTheThirdProductsPrice);
		driver.quit();

	}


}






