package com.salesforce;


class ChromeDriver {
    String name;

    public ChromeDriver(String name) {
        this.name = name;
    }
}

class PriyaThreadLocal {
    ChromeDriver car;

    public void set(ChromeDriver car) {
        this.car = car;
    }

    public ChromeDriver get() {
        return this.car;
    }
}

class BasePage {
    public void call() {
        System.out.println("--- Loading element using driver ---- ");
    }
}

class LoginPage extends BasePage {
    String name;

    public LoginPage() {
        this.name = "Priya";
    }

    public void call() {
        System.out.println("--- Loading LoginPage ---- ");
    }
}

class HomePage extends BasePage {
    String name;

    public HomePage() {
        this.name = "Priya";
    }

    public HomePage(ChromeDriver cd) {
        PriyaPageFactory.initPageElements(cd, this);
    }

    public void call() {
        System.out.println("--- Loading HomePage ---- ");
    }
}

class PriyaPageFactory {

    public static void initPageElements(ChromeDriver cd, BasePage obj) {
        System.out.println("Using driver:: " + cd.name);
        obj.call();
    }
}

public class Test {

    public static void main(String[] args) {
        ChromeDriver c = new ChromeDriver("Chrome");
        PriyaThreadLocal s = new PriyaThreadLocal();
        s.set(c);
        s.get();

        BasePage lp = new LoginPage();
        PriyaPageFactory.initPageElements(c, lp);

        BasePage hp = new HomePage();
        PriyaPageFactory.initPageElements(c, hp);

    }
}
