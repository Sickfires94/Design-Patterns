import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ClockTimer extends Subject{
    int hour;
    int minute;
    int second;

    ClockTimer(){
        LocalDateTime now = LocalDateTime.now();
        this.second = now.getSecond();
        this.minute = now.getMinute();
        this.hour = now.getHour();
    }

    void tick(){
        second++;
        if(second == 60){
            second = 0;
            minute++;
            if(minute == 60){
                minute = 0;
                hour++;
            }
        }
        this.notifyObservers();
    }
}
