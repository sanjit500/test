
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCategoryPOM 
{
	private WebDriver driver;
	
	public AddNewCategoryPOM(WebDriver driver) 
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
	
	@FindBy(id="tag-name")
	private WebElement EnterName;
	
	@FindBy(id="tag-slug")
	private WebElement EnterSlug;
	
	@FindBy(id="tag-description")
	private WebElement EnterDescription;
	
	@FindBy(id="submit")
	private WebElement addCatgyBtn;
	
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
	public void sendName(String titleEnter) {
		this.EnterName.sendKeys(titleEnter); 
	}
	public void sendSlug(String contentEnter) {
		this.EnterSlug.sendKeys(contentEnter); 
	}
	public void sendDescription(String contentEnter) {
		this.EnterDescription.sendKeys(contentEnter); 
	}
	public void clickAddNwCtgryBtn() {
		this.addCatgyBtn.click(); 
	}
	
}