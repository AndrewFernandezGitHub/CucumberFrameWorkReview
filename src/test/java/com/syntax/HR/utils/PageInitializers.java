package com.syntax.HR.utils;

import com.syntax.HR.pages.AddEmployeePage;
import com.syntax.HR.pages.DashboardPage;
import com.syntax.HR.pages.LoginPage;

public class PageInitializers {
    public static LoginPage login;
    public static AddEmployeePage addEmployeePage;
    public static DashboardPage dash;

    public static void initializePageObjects() {
        login = new LoginPage();
        addEmployeePage = new AddEmployeePage();
        dash = new DashboardPage();
    }
}