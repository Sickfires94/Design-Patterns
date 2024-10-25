public abstract class Addons implements Laptop{
    Laptop laptop;
    Addons(Laptop laptop) {
        this.laptop = laptop;
    }
}

class Ram extends Addons{
    Ram(Laptop laptop) {
        super(laptop);
    }

    public String Description(){
        return "4GB Ram \n" + laptop.Description();
    }

    public int Cost(){
        return 100 + laptop.Cost();
    }
}

class Storage extends Addons{
    Storage(Laptop laptop) {
        super(laptop);
    }

    public String Description(){
        return "1TB Storage \n" + laptop.Description();
    }

    public int Cost(){
        return 200 + laptop.Cost();
    }
}

class Mouse extends Addons{
    Mouse(Laptop laptop) {
        super(laptop);
    }

    public String Description(){
        return "Mouse \n" + laptop.Description();
    }

    public int Cost(){
        return 300 + laptop.Cost();
    }
}

class GPU extends Addons{
    GPU(Laptop laptop) {
        super(laptop);
    }

    public String Description(){
        return "Geforce 1650 super ultra-lite GPU \n" + laptop.Description();
    }

    public int Cost(){
        return 400 + laptop.Cost();
    }
}
