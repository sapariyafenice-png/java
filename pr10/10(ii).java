
import java.util.ArrayList;
import java.util.List;
 
class BufferDemo {
    static class Buffer {
        private final int[] values = new int[3];
        private int front, rear, count;
 
        synchronized void put(int value) throws InterruptedException {
            while (count == values.length) wait();
            values[rear] = value;
            rear = (rear + 1) % values.length;
            count++;
            System.out.println("Produced " + value);
            notify();
        }
 
        synchronized int take() throws InterruptedException {
            while (count == 0) wait();
            int value = values[front];
            front = (front + 1) % values.length;
            count--;
            System.out.println("Consumed " + value);
            notify();
            return value;
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        List<Integer> consumed = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) buffer.put(i * 3);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) {
                    consumed.add(buffer.take());
                }
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        List<Integer> expected = List.of(3, 6, 9, 12, 15, 18, 21, 24);
        System.out.println("Consumed in order: " + consumed);
        if (!consumed.equals(expected)) {
            throw new AssertionError("Items lost or out of order");
        }
        System.out.println("All 8 items verified.");
    }
}
