class AnalogClock extends Observer{
    void update(Subject clockTimer){
        draw((ClockTimer)clockTimer);
    }

    void draw(ClockTimer clockTimer){
        System.out.println("Analog Clock: " + (clockTimer.hour % 12) + ":" + clockTimer.minute + ":" + clockTimer.second);
    }

}

class DigitalClock extends Observer{
    void update(Subject clockTimer){
        draw((ClockTimer)clockTimer);
    }

    void draw(ClockTimer clockTimer){
        System.out.println("Digital Clock: " + (clockTimer.hour % 24) + ":" + clockTimer.minute + ":" + clockTimer.second);
    }
}
