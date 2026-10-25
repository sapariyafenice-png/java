
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
 
class LabKit implements Serializable {
    private static final long serialVersionUID = 1L;
    final String name;
    final int quantity;
    transient String checkoutCode;
 
    LabKit(String name, int quantity, String checkoutCode) {
        this.name = name;
        this.quantity = quantity;
        this.checkoutCode = checkoutCode;
    }
 
    public String toString() {
        return name + ", quantity=" + quantity
                + ", checkoutCode=" + checkoutCode;
    }
}
 
public class SaveLoadDemo {
    public static void main(String[] args) throws Exception {
        LabKit[] original = {
            new LabKit("Sensor kit", 7, "S-482"),
            new LabKit("Cable kit", 11, "C-613")
        };
        Path file = Path.of("lab-kits.bin");
        try (ObjectOutputStream output = new ObjectOutputStream(
                Files.newOutputStream(file))) {
            output.writeObject(original);
        }
        LabKit[] restored;
        try (ObjectInputStream input = new ObjectInputStream(
                Files.newInputStream(file))) {
            restored = (LabKit[]) input.readObject();
        }
        boolean verified = original != restored
                && original.length == restored.length;
        for (int i = 0; i < original.length; i++) {
            System.out.println("Before: " + original[i]);
            System.out.println("After:  " + restored[i]);
            verified &= original[i] != restored[i]
                    && original[i].name.equals(restored[i].name)
                    && original[i].quantity == restored[i].quantity
                    && restored[i].checkoutCode == null;
        }
        if (!verified) throw new AssertionError("Round trip failed");
        System.out.println("Fresh objects and saved fields verified.");
        System.out.println("Transient checkoutCode was not saved.");
    }
}
