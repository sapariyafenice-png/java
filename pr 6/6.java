@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class UrgentNotifier implements Urgent {
}
 
class Main {
    public static void main(String[] args) {

        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {email, sms};

        String message = "Exam is tomorrow";

        for (Notifier sender : senders) {
            sender.send(message);
        }

        Notifier urgentEmail = message ->
                System.out.println("Urgent Email: " + message);

        UrgentNotifier urgent = new UrgentNotifier();

        System.out.println("\nUrgent notification:");

        urgentEmail.send(message);
        urgentEmail.send(message);
    }
}

