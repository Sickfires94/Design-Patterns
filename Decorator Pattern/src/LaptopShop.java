import java.util.Scanner;

class LaptopShop{
    static Laptop buyLaptop(){
        Laptop laptop = LaptopOptions();
        laptop = AddonOptions(laptop);

        System.out.println("Laptop Order: ");
        System.out.println(laptop.Description());

        System.out.println("Order Price: " + laptop.Cost());

        return laptop;
    }

    private static Laptop LaptopOptions(){
        String[] laptops = {"Latitude", "Inspiron", "Probook", "Predator"};
        Scanner sc = new Scanner(System.in);


        for (int i = 1; i <= laptops.length; i++){
            System.out.println(i + ". " + laptops[i - 1]);
        }

        System.out.println("Select Laptop: ");

        int input = sc.nextInt();

        return switch (input) {
            case 1 -> new LatitudeLaptop();
            case 2 -> new InspironLaptop();
            case 3 -> new ProbookLaptop();
            case 4 -> new PredatorLaptop();
            default -> {
                System.out.println("Invalid Selection");
                yield LaptopOptions();
            }
        };
    }

    private static Laptop AddonOptions(Laptop laptop){
        Scanner sc = new Scanner(System.in);
        Laptop l = laptop;

        while (true){
            printAddons();
            System.out.println("Select Addon: ");
            int input = sc.nextInt();
            switch (input) {
                case 1 -> l = new Ram(l);
                case 2 -> l = new Storage(l);
                case 3 -> l = new Mouse(l);
                case 4 -> l = new GPU(l);
                case 5 -> {
                    sc.close();
                    return l;
                }
                default -> System.out.println("Invalid Selection");
            }
        }
    }

    private static void printAddons(){
        String[] addons = {"4GB Ram", "1TB Storage", "Mouse", "Geforce 1650 GPU", "Finalize Order"};
        for (int i = 1; i <= addons.length; i++){
            System.out.println(i + ". " + addons[i - 1]);
        }
    }
}

