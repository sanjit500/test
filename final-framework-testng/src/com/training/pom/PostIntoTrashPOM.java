package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostIntoTrashPOM {
	private WebDriver driver; 
	
	public PostIntoTrashPOM(WebDriver driver) {
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
	
	@FindBy(linkText="Trash")
	private WebElement trash;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/ul/li[4]/a")
	private WebElement trashTab;
	
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
	public void clickTrashBtn() {
		this.trash.click(); 
	}
	public void clickTrashTab() {
		this.trashTab.click(); 
	}
}