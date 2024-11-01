import java.time.Clock;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClockTimer clockTimer = new ClockTimer();
        AnalogClock analogClock = new AnalogClock();
        DigitalClock digitalClock = new DigitalClock();

        clockTimer.attach(analogClock);
        clockTimer.attach(digitalClock);

        while (true) {
            clockTimer.tick();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}