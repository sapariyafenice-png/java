class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void synchronizedIncrement() 
    {
        count++;
    }
}

class CounterThread extends Thread {
    Counter counter;
    int times;
    boolean useSync;

    CounterThread(Counter counter, int times, boolean useSync) {
        this.counter = counter;
        this.times = times;
        this.useSync = useSync;
    }
    

    public void run() {
        for (int i = 0; i < times; i++) {
            if (useSync) {
                counter.synchronizedIncrement();
            } else {
                counter.increment();
            }
        }
    }
}

 class CounterRace {
    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 10;
        int incrementsPerThread = 100000;

        // Without synchronization
        Counter counter1 = new Counter();

        CounterThread[] threads1 = new CounterThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads1[i] = new CounterThread(
                counter1,
                incrementsPerThread,
                false
            );
            threads1[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads1[i].join();
        }

        int expected = numberOfThreads * incrementsPerThread;

        System.out.println("Without Synchronization:");
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + counter1.count);

        
        Counter counter2 = new Counter();

        CounterThread[] threads2 = new CounterThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads2[i] = new CounterThread(
                counter2,
                incrementsPerThread,
                true
            );
            threads2[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads2[i].join();
        }

        System.out.println("\nWith Synchronization:");
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + counter2.count);
    }
}