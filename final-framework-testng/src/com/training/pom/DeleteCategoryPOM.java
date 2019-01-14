
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteCategoryPOM 
{
	private WebDriver driver;
	
	public DeleteCategoryPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//*[@id=\"responsive\"]/li[8]/a")
	private WebElement signin;
		
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[2]")
	private WebElement posts;
	
	@FindBy(xpath="//*[@id=\"menu-posts\"]/ul/li[4]/a")
	private WebElement clickCategoriesBtn;
	
	@FindBy(xpath="//*[@id=\"bulk-action-selector-top\"]")
	private WebElement clickBulkActionBtn;
	
	@FindBy(xpath="//*[@id=\"bulk-action-selector-top\"]/option[2]")
	private WebElement clickDeleteBtn;
	
	@FindBy(xpath="//*[@id=\"doaction\"]")
	private WebElement clickApplyBtn;
	
	public void clickSignInBtn() {
		this.signin.click(); 
	}
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickPostsBtn() {
		this.posts.click(); 
	}
	public void clcikCategoriesBtn() {
		this.clickCategoriesBtn.click(); 
	}
	public void clcikBulkBtn() {
		this.clickBulkActionBtn.click(); 
	}
	public void clcikOnDeleteBtn() {
		this.clickDeleteBtn.click(); 
	}
	public void clcikOnApplyBtn() {
		this.clickApplyBtn.click(); 
	}
	
}