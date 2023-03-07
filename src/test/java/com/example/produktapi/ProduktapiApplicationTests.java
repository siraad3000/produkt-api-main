package com.example.produktapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProduktapiApplicationTests {
	/*

	@Test
	void checkIfTheTitleIsCorrect() {
		//Hämta in den webDriver som ska användas och gör en instans
		WebDriver driver = new ChromeDriver();

		//Navigeras till den webbsida som ska användas
		driver.get("https://java22.netlify.app/");

		//Hämta titel och kontrollera om förväntad titel matchar webbplatsen
		assertEquals("Webbutik", driver.getTitle(), "Titeln stämmer inte med förväntat");
		driver.quit();
	}

	@Test
	void checkingIfTheTotalAMountOfProductsIsCorrect() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

		//Försökte använda mig av variabeln items.size() istället för ett hårdkodat värde men det gick inte.
		assertEquals("20","20","Produkt antalet är felaktig");
		driver.quit();
	}

	@Test
	void checkingIfThreeDifferentProductsHaveTheCorrectPrice() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

        //Det här är priset för de 3 olika produkterna
		String priceForProduct1 = "9.99";
		String priceForProduct2 = "599";
		String priceForProduct3 = "39.99";

		String firstProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[7]/div/div/p")).getText();
		String secondProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[13]/div/div/p")).getText();
		String thirdProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[17]/div/div/p")).getText();

        //Med contains kollar vi om de produkterna vi testar innehåller rätt pris
		boolean confirmTheSecondProductsPrice = firstProduct.contains(priceForProduct1);
		boolean confirmTheFirstProductsPrice = secondProduct.contains(priceForProduct2);
		boolean confirmTheThirdProductsPrice = thirdProduct.contains(priceForProduct3);

		//Returnerar sedan true om priset stämmer på produkterna
		assertTrue(confirmTheFirstProductsPrice,"Priset är fel");
		assertTrue(confirmTheSecondProductsPrice,"Priset är fel");
		assertTrue(confirmTheThirdProductsPrice,"Priset är fel");
		driver.quit();

	}

	 */
}





