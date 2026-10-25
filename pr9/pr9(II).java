
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
 
 class SeatBookingDemo {
    static class BookingDesk {
        int seatsLeft = 5;
        final CyclicBarrier checked = new CyclicBarrier(10);
 
        boolean unsafeBook() {
            if (seatsLeft <= 0) return false;
            // All ten see availability before any deduction occurs.
            try {
                checked.await();
            } catch (Exception error) {
                throw new RuntimeException(error);
            }
            seatsLeft--;
            return true;
        }
 
        synchronized boolean book() {
            if (seatsLeft <= 0) return false;
            seatsLeft--;
            return true;
        }
    }
 
    static void run(boolean safe) throws InterruptedException {
        BookingDesk desk = new BookingDesk();
        AtomicInteger successful = new AtomicInteger();
        Thread[] customers = new Thread[10];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Thread(() -> {
                boolean booked = safe ? desk.book() : desk.unsafeBook();
                if (booked) successful.incrementAndGet();
            });
            customers[i].start();
        }
        for (Thread customer : customers) customer.join();
        System.out.println((safe ? "Synchronized" : "Unsafe")
                + ": bookings=" + successful.get()
                + ", seatsLeft=" + desk.seatsLeft);
        if (safe && (successful.get() != 5 || desk.seatsLeft != 0)) {
            throw new AssertionError("Incorrect seat allocation");
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
        System.out.println("5 seats, 10 customers");
        System.out.println("Unsafe availability checks use a barrier.");
        run(false);
        run(true);
    }
}
