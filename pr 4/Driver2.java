import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PasswordChecker pc = new PasswordChecker();

        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        System.out.println("Length >= 8 : " + pc.hasLength(pw));
        System.out.println("Uppercase   : " + pc.hasUppercase(pw));
        System.out.println("Digit       : " + pc.hasDigit(pw));
        System.out.println("Special Char: " + pc.hasSpecial(pw));
        System.out.println("Strength    : " + pc.strength(pw));

        sc.close();
        
    }
}