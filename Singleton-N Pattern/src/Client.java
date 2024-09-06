public class Client implements Runnable{

    // Retrieve free connection
    // use it
    // release it

    @Override
    public void run() {
        Connections_NTon conn = Connections_NTon.getInstance();
        conn.use();
        Connections_NTon.releaseConnection(conn);
    }
}