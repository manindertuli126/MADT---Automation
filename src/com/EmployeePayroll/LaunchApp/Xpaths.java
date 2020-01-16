package com.EmployeePayroll.LaunchApp;

public interface Xpaths {

	String homeCarBtn = "//button[@id = 'tab-car-tab-hp']";
	String homePickUpTxt = "//span[contains(text(),'Picking up')]//following::input[1]";
	String homeDropOffTxt = "//span[contains(text(),'Dropping off')]//following::input[1]";
	String homePickUpDateTxt = "//span[contains(text(),'Pick-up date')]//following::input[1]";
	String homeDropOffDateTxt = "//span[contains(text(),'Drop-off date')]//following::input[1]";
	String homeSearchBtn = "//button[@id = 'search-button-hp-car']";
	
}
