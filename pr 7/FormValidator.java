import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}
class SignupForm {
    @NotBlank
    @MaxLength(10)
    String username;

    @NotBlank
    @MaxLength(20)
    String password;

    SignupForm(String u, String p) {
        username = u;
        password = p;
    }
}

public class FormValidator {

    static List<String> validate(Object obj) throws Exception {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field f : fields) {

            f.setAccessible(true);
            String value = (String) f.get(obj);

            if (f.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.trim().isEmpty()) {
                    errors.add(f.getName() + " is blank");
                }
            }

            if (f.isAnnotationPresent(MaxLength.class)) {
                int max = f.getAnnotation(MaxLength.class).value();

                if (value != null && value.length() > max) {
                    errors.add(f.getName() + " exceeds " + max + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args) throws Exception {

        SignupForm s = new SignupForm("", "veryverylongpassword123");

        List<String> result = validate(s);

        if (result.isEmpty())
            System.out.println("Valid Form");
        else
            for (String e : result)
                System.out.println(e);
    }
}