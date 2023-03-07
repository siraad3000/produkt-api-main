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

	@Test
	void checkIfTheTitleIsCorrect() {
		//Hämta in den webDriver som ska användas gör en instans
		WebDriver driver = new ChromeDriver();

		//Navigera till den webbsida som ska användas
		driver.get("https://java22.netlify.app/");
		//driver.get("https://www.svt.se/");

		//Hämta titel och kontrollera om förväntad titel matchar webbplatsen
		assertEquals("Webbutik", driver.getTitle(), "Titeln stämmer inte med förväntat");

		driver.quit();
	}

	@Test
	void checkingIfTheTotalAMountOfProductsIsCorrect() {

		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

		List<WebElement> items = driver.findElements(By.className("productItem"));

		assertEquals("20", "20","Produkt antalet matchar ej");
		driver.quit();
	}

	@Test
	void checkingIfThreeDifferentProductsHaveTheCorrectPrice() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://java22.netlify.app/");

		String priceForProduct1 = "599";
		String priceForProduct2 = "9.99";
		String priceForProduct3 = "39.99";

		String firstProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[13]/div/div/p")).getText();
		String secondProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[7]/div/div/p")).getText();
		String thirdProduct = driver.findElement(By.xpath("//*[@id=\"productsContainer\"]/div/div[17]/div/div/p")).getText();


		boolean confirmTheFirstProductsPrice = firstProduct.contains(priceForProduct1);
		boolean confirmTheSecondProductsPrice = secondProduct.contains(priceForProduct2);
		boolean confirmTheThirdProductsPrice = thirdProduct.contains(priceForProduct3);

		assertTrue(confirmTheFirstProductsPrice,"Priset är fel");
		assertTrue(confirmTheSecondProductsPrice,"Priset är fel");
		assertTrue(confirmTheThirdProductsPrice,"Priset är fel");

		driver.quit();

	}


}





