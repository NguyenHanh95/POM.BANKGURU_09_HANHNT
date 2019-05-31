package commons;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbtractPage {
	WebElement element;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;
	List<WebElement> Elements;

	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardtoNextPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();

	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();

	}

//web element
	public void clickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();

	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);

	}

	public void selectItermInDropDown(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getSelectItermInDropDown(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();

	}

	public void selectCustomDropdownList(WebDriver driver, String parentXpath, String allItemXpath, String valueExpected) throws InterruptedException {
		// click để mở dropdown ra
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		waitExplicit = new WebDriverWait(driver, 30);
		javascriptExecutor.executeScript("arguments[0].click();", parent);
		parent.click();
		// wait cho tất cả các item được mở
		List<WebElement> allItems = (List<WebElement>) driver.findElement(By.xpath(allItemXpath));

		// GetText tất cả các item ra và kiểm tra giá trị có bằng giá trị mong muốn hay k
		for (WebElement childElement : allItems) {
			if (childElement.getText().equals(valueExpected)) {
				
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(1000);

			}
			if (childElement.isDisplayed()) {
				childElement.click();
			} else {
				javascriptExecutor.executeScript("arguments[0].click();", childElement);
			}
			Thread.sleep(1000);
			break;

		}
	}

	public String getAtributeValue(WebDriver driver, String locator, String atributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(atributeName);

	}

	public void countElementNumber(WebDriver driver, String locator) {
		driver.findElement(By.xpath(locator));
		Elements.size();
	}

	public void checkToCheckBox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckBox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplay(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlEnable(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
}