//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Run multiple threads to simulate multiple connection requests
        for (int i = 0; i < 1000; i++){
            Thread client = new Thread(new Client());
            client.start();
        }
    }
}