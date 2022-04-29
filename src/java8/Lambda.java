package java8;

@FunctionalInterface
interface Sayable {
    public String say();
}

public class Lambda {
    public static void main(String[] args) {
        Sayable a = () ->
            "I have nothing to say.";
        System.out.println(a.say());
    }

}
