
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolObject {
    private final int id;
    private static Queue<PoolObject> list;
    private static int maxObjects = 5;
    private static int currentObjects = 0;

    private PoolObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void release() {
        synchronized (PoolObject.class) {
            list.add(this);
            System.out.println("Object " + id + " returned to the pool.");
            PoolObject.class.notifyAll();
        }
    }

    private static PoolObject getInstance() {
        //initialising list
        if (list == null) {
            synchronized (PoolObject.class) {
                if (list==null) {
                    list = new LinkedList<>();
                }
            }
        }

        //no free object and limit not reached
        if (list.isEmpty() && currentObjects < maxObjects) {
            synchronized (PoolObject.class) {
                if (list.isEmpty() && currentObjects < maxObjects) {
                    PoolObject newObj = new PoolObject(currentObjects);
                    currentObjects++;
                    System.out.println("Created new object " + newObj.getId());
                    return newObj;
                }
            }
        }

        //free object available
//        if (!list.isEmpty()) {
//            synchronized (PoolObject.class) {
//                if (!list.isEmpty()) {
//                    PoolObject obj = list.poll();
//                    System.out.println("Reusing object " + obj.getId());
//                    return obj;
//                }
//            }
//        }
        //no free object and limit reached
        synchronized (PoolObject.class) {
            while (true) {
                try {
                    PoolObject.class.wait();  // Wait until an object is released
                    if (!list.isEmpty()) {
                        PoolObject obj = list.poll();
                        System.out.println("Reusing object " + obj.getId());
                        return obj;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public static void main(String[] args) {
        //simpletest();
        threadedTest();

    }

    public static void simpletest() {
        PoolObject p1 = PoolObject.getInstance();
        PoolObject p2 = PoolObject.getInstance();
        PoolObject p3 = PoolObject.getInstance();
        PoolObject p4 = PoolObject.getInstance();
        PoolObject p5 = PoolObject.getInstance();

        // Try to get the 6th object, which will wait until an object is released
//        PoolObject p6 = PoolObject.getInstance();

        // Release objects back to the pool
        p1.release();
        p2.release();

        // Try getting new objects after releasing some
        PoolObject p7 = PoolObject.getInstance();
        PoolObject p8 = PoolObject.getInstance();
    }

    public static void threadedTest() {
        // Create a thread pool with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Simulate 10 threads each trying to acquire and release pool objects
        for (int i = 0; i < 1000; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    // Each thread tries to get a PoolObject from the pool
                    System.out.println("Thread " + threadId + " trying to get object");
                    PoolObject obj = PoolObject.getInstance();
                    System.out.println("Thread " + threadId + " got object with id: " + obj.getId());

                    // Simulate some work with the object
                    TimeUnit.MILLISECONDS.sleep(500);  // Simulating work

                    // Release the object back to the pool
                    System.out.println("Thread " + threadId + " releasing object with id: " + obj.getId());
                    obj.release();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Gracefully shut down the executor service
        executor.shutdown();
        try {
            // Wait for all tasks to finish
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force shutdown if tasks take too long
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All threads have finished execution.");
    }


}
