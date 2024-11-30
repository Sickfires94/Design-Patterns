import java.io.IOError;
import java.util.Scanner;

public class StatefulVendingMachine {
    State state;
    int money;

    public StatefulVendingMachine(){
        state = new idleState();
        money = 0;
    }

    public void runMachine(){
        Scanner sc = new Scanner(System.in);
        int action = 0;
        while(true){
            displayPossibleActions();
            action = getAction(sc);
            this.state.next(this, Actions.values()[action]);
            }
        }

    void displayPossibleActions(){
        for(int index = 0; index < Actions.values().length; index++) {
            System.out.println("[" + index + "] " + Actions.values()[index]);
        }
    }

    int getAction(Scanner sc){
        int action = -1;
        while(action < 0 || action > 3){
            System.out.print("Input Action number : ");
            try{action = sc.nextInt();}
            catch(Exception e){
                System.out.println("Invalid Input");
            }
            System.out.println("****************************************");
        }
        return action;
    }
}
