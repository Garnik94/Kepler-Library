package service;

public class MainDynamicPageService {

    public String str = "hello";

    private static boolean loginPage = true;
    private static boolean chooseSectionPage = false;
    private static boolean registrationPage = false;

    public static void onLoginPage() {
        loginPage = true;
        chooseSectionPage = false;
        registrationPage = false;
    }

    public static void onChooseSectionPage() {
        chooseSectionPage = true;
        loginPage = false;
        registrationPage = false;
    }

    public static void onRegistrationPage() {
        registrationPage = true;
        loginPage = false;
        chooseSectionPage = false;
    }

    public static boolean isLoginPage() {
        return loginPage;
    }

    public static boolean isChooseSectionPage() {
        return chooseSectionPage;
    }

    public static boolean isRegistrationPage() {
        return registrationPage;
    }
}
