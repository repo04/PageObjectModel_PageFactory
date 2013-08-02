/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelegencia.tests.utility;

/**
 * 
 * @author Joshua.Perumalla
 */
public class Constants {
	/**
	 * 
	 * Constants is a class where we pass xpaths for our test data(normally in
	 * data-driven framework we put all the xpaths in properties files but not
	 * here in PageObjectModel_PageFactory, we put all the xpaths in Constants
	 * class)
	 */
	// Login Page PATH
	public static String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "//src//com//intelegencia//tests//config/config.properties";
	// Login Page
	public static final String username = "//*[@id='username']";
	public static final String password = "//*[@id='password']";
	public static final String submit = "//*[@id='loginpage_submit_btn']";
	// landing page
	public static final String Homelink = "Home";
	public static final String Courseslink = "//*[@id='leftnav_mycontacts']";
	public static final String homeUrl = "//ul[@id='nav']/li[1]/a";
	public static final String wallPublishPanelXPATH = "//*[@id='wall_publisher_textarea_noedit']";
	public static final String linkToWallXPATH = "//ul[@id='nav']/li[1]/a";
	public static final String urlLink = "//div/input[3]";
	public static final String btnWallShareXPATH = "//*[@id='wall_publisher_btn']/tbody/tr[2]/td[2]/em/button";
	public static final String linkButton = "//td[6]/table/tbody/tr[2]/td[2]/em/button";
	public static final String shareType = "sharetype";
	public static final String profilePage = "//*[@id='nav']/li[2]/a";
	public static final String myCourse = "//*[@id='region-pre']/div/ul/li/a";
	public static final String courseGroupName = "//*[@id='region-pre']/div/ul/li/ul/li/ul/li/a[contains(text(),'2.2.4 Group')]";
	public static final String clickOnCourse = "//*[@id='region-pre']/div/ul/li/a";
	public static final String profile = "//*[@id='nav']/li[2]/a";
	public static final String searchContact = "//*[@id='txtSearchContacts']";
	public static final String searchContactButton = "//*[@id='searchcontactsbutton']";
	public static final String searchTeacherLabel = "//a/label";
	public static final String searchTextarea = "//li/div/div/div/textarea";
	public static final String searchClick = "//div[2]/a[2]";
	public static final String leftnav1Click = "//*[@id='leftnav_livesessions']/span";
	public static final String leftnav2Click = "//*[@id='leftnav_livemeetings']/span[2]";
	public static final String createSession = "//a[@id='create_session']";
	public static final String sesionName = "//input[@id='eventname']";
	public static final String sessionDescription = "//*[@id='edit-description']";
	public static final String durationRadioBtn = "//*[@id='duration_yes']";
	public static final String endDate = "//*[@id='enddate']";
	public static final String endTime = "//*[@id='endtime']";
	public static final String textPresent = "//*[@id='ext-comp-1010']/div/div[2]/div[3]/table/tbody/tr[2]/td/span[2]";
	public static final String urlPostpath = "//div/input[3]";
	public static final String fileNamePath = "//thead/tr/td/div";
	public static final String buttonsAvailable = "//div/input";
	public static final String fileNamePresent = "//label";
	public static final String fileDeleteButton = "//td[3]/div/div";
	public static final String fileButtonDeleteClk = "//div[2]/div/div/div/div/table/tbody/"
			+ "tr/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/em/button";
	public static final String fileButtonClk = "//td[2]/table/tbody/tr/td/table/tbody/tr/td"
			+ "/table/tbody/tr[2]/td[2]/em/button";
	public static final String fileNameCheck = "//div/table/tbody/tr/td/div/a";
	public static final String contactName = "Contacts";
	public static final String searchContactName = "//input[@id='txtSearchContacts']";
	public static final String grpCreation = "Groups";
	public static final String startWorkingGroup = "//*[@id='leftnav_my_working_groups']";
	public static final String teacherPostOnStdWall = "//a[@id='leftnav_wall']";
	public static final String logoutBtn = "//*[@id='navright']/li[3]/a[@href='#']";
	public static final String finalLogoutBtn = "//*[@id='navright']/li[3]/ul/li[2]/a";

	// group creation
	public static final String startSocialGroup = "//*[@id='groups-buttons']/a";
	public static final String groupName = "//*[@id='id_groupname']";
	public static final String shortName = "//*[@id='id_shortname']";
	public static final String aboutGroup = "//*[@id='id_aboutgroup']";
	public static final String whoShouldJoin = "//*[@id='id_whoshouldjoin']";
	public static final String topics = "//*[@id='id_topic1']";
	public static final String submitGroup = "//*[@id='id_submitbutton']";
	public static final String findSocialGroup = "//*[@id='leftnav_find_group']";
	public static final String searchSocialGroup = "//*[@id='search_text']";
	public static final String searchField = "//*[@id='searchfield']";
	public static final String findSubmit = "//*[@id='search_button']";
	public static final String joinButton = "//*[@id='groupBtmInfo_1355']/a[@class='join']";
	public static final String joinLink = "//*[@id='ext-comp-1008']/tbody/tr[2]/td[2]";
	public static final String asdLink = "//*[@id='ext-comp-1007']/tbody/tr[2]/td[@class='x-btn-mc']";
	public static final String activityReport = "//*[@id='leftnav_activityreport']/span";
	public static final String quizLink = "//*[@id='grades-admin']/table[2]/tbody/tr[7]/td[1]/a/strong[contains(text(),'quiz')]";
	public static final String joinSocGrpLink = "//*[starts-with(@id,'group_')]//a[@class='join']";
	public static final String mySocGrp = "//*[@id='leftnav_my_social_groups']";
	public static final String leavesocGrp = "//*[starts-with(@id,'group_')]//div/div[3]/a[1]";
	public static final String leavesocGrplink = "//*[@id='ext-gen70']";
	public static final String leaveGrp = "//*[@id='ext-comp-1007']/tbody/tr[2]/td[2]";

	// wallPage
	public static final String fileLink = "Files";
	public static final String selectQuiz = "//a/strong[contains(text(),'Test QUIZ')]";
	public static final String attemptQuiz = "//*[@id='region-main']//input[@type='submit']";
	public static final String selectOption = "//div[2]/div[2]/div/input[@value='1']";
	public static final String endQuiz = "//*[@id='endtestid']";
	public static final String finalSubmitQuiz = "//*[starts-with(@id,'single_button')]";
	public static final String dialogBoxData = "//*[@id='confirmdialog']/div[2]";
	public static final String clickDialogBox = "//*[@id='confirmdialog']/div[3]/span/button[2]";
	public static final String deleteSocGrp = "//*[@id='sgroup_delete']";

	// profile page
	public static final String wallPage = "//*[@id='leftnav_wall']";
	public static final String personalInfo = "//*[@id='leftnav_info']";
	public static final String portfolio = "//*[@id='leftnav_portfolio']";
	public static final String notes = "//*[@id='leftnav_notes']";
	public static final String files = "//*[@id='leftnav_files']";
	public static final String contacts = "//*[@id='leftnav_mycontacts']";
	// file upload
	public static final String fileUpload = "upload-btn";
	// course group wall
	public static final String writeInWall1 = "//*[@id='wall_publisher_textarea_noedit']";
	public static final String writeInWall2 = "//*[@id='wall_publisher_textarea_noedit']";
}
