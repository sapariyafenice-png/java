import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    public boolean hasLength(String pw) {
        return pw.length() >= 8;
    }

    public boolean hasUppercase(String pw) {
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(pw);
        return m.find();
    }

    public boolean hasDigit(String pw) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(pw);
        return m.find();
    }

    public boolean hasSpecial(String pw) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        Matcher m = p.matcher(pw);
        return m.find();
    }

    public String strength(String pw) {
        int count = 0;

        if (hasLength(pw))
            count++;
        if (hasUppercase(pw))
            count++;
        if (hasDigit(pw))
            count++;
        if (hasSpecial(pw))
            count++;

        if (count <= 1)
            return "Weak";
        else if (count <= 3)
            return "Medium";
        else
            return "Strong";
    }
}