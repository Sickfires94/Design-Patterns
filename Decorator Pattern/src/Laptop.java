import java.util.Scanner;

public interface Laptop {
    public String Description();
    public int Cost();
}

class LatitudeLaptop implements Laptop {
    public String Description() { return "Latitude Laptop \n------------------------"; }
    public int Cost() { return 100; }
}

class InspironLaptop implements Laptop {
    public String Description() { return "Inspiron Laptop \n------------------------"; }
    public int Cost() { return 200; }
}

class ProbookLaptop implements Laptop {
    public String Description() { return "Probook Laptop \n------------------------"; }
    public int Cost() { return 300; }
}

class PredatorLaptop implements Laptop {
    public String Description() { return "Predator Laptop \n------------------------"; }
    public int Cost() { return 400; }
}

