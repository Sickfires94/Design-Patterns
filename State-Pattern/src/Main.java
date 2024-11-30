import java.io.IOError;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Run Vending Machine following State design Pattern
        StatefulVendingMachine svm = new StatefulVendingMachine();
        svm.runMachine();

        // Run Vending Machine not Following State design Pattern
//        VendingMachine vm = new VendingMachine();
//        vm.runMachine();
    }


}