import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Connections_NTon {

    private static final int DEFAULT_CONNECTIONS = 5;

    // NTon Attributes
    private static Queue<Connections_NTon> connections = null;
    private static int size;
    private static int createdInstances;

    // Connections Attributes
    private final int id;
    private Connections_NTon(){
        id = createdInstances;
        createdInstances++;
    }

    // Sample Use of connections
    public void use(){
        try {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 100));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Connection " + id + "  Used, Thread " + Thread.currentThread().getName());
    }


    // Initialization of NTon
    public static synchronized void initialize(int s){
       if (connections != null) return;

       connections = new LinkedList<Connections_NTon>();
       size = s;
       createdInstances = 0;
    }

    // Get Instance from pool
    public static Connections_NTon getInstance(){
       // Initialize Queue if not yet initialized
       if (connections == null) initialize(DEFAULT_CONNECTIONS);

       // Create connection if not available
       if (createdInstances < size){
           synchronized (Connections_NTon.class) {
               if (createdInstances < size){
                   Connections_NTon conn = new Connections_NTon();
                   createdInstances++;

                   return conn;
               }
           }
       }

       // Get free connection
       synchronized (Connections_NTon.class){
           while (connections.isEmpty()){
               try{
                   Connections_NTon.class.wait();
               }
               catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
               return connections.remove();
       }
   }

   // release connection object
   public static synchronized void releaseConnection(Connections_NTon conn){
       connections.add(conn);
       Connections_NTon.class.notifyAll();
   }
}
