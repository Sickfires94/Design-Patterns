import java.io.IOError;
import java.util.Scanner;

public class VendingMachine {
    public void runMachine(){
        Scanner sc = new Scanner(System.in);
        int action = 0;
        while(true){

            // Idle State Simulation
            // Wait for Money to be added
            while(action != 1) {
                System.out.println("Add Money to Purchase Item");
                displayPossibleActions();
                action = getAction(sc);
            }

            // Display Entered Amount State simulation
            System.out.println("Money Entered");

            //Waiting For Selection State Simulation
            while(action != 2 && action != 3 ) {
                System.out.println("Select Product to Purchase");
                displayPossibleActions();
                action = getAction(sc);
                if(action == 1)
                    System.out.println("Can not add additional money, select item to Purchase");
            }

            // Item Selection Workflow
            if(action == 2) {
                System.out.println("Item Selected");
                System.out.println("Dispensing Item");
                System.out.println("Item Dispensed");
                System.out.println("Refunding remaining amount");
                System.out.println("*************************************");
            }

            // Transaction Cancelled Workflow
            if(action == 3) {
                System.out.println("Transaction Cancelled");
                System.out.println("Refunding remaining amount");
                System.out.println("*************************************");
            }
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
