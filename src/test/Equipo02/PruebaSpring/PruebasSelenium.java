package Equipo02.PruebaSpring;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class PruebasSelenium {

	public static void main(String[] args) {
		
		/* 1º Cargamos el driver de Selenium para chrome */
		System.setProperty("webdriver.chrome.driver","./libs/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		/* 2º Cargamos la url a testear en el driver */
		String url = "https://pruebaspring.herokuapp.com/";
		driver.get(url);
		
		/* 3º Obtenemos las referencias de los WebElements y ejecutamos los pasos a testear */
		String body = driver.findElement(By.tagName("Body")).getText();
		
		
		System.out.println(body);
		
		
	}

}
