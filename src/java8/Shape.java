package java8;

public interface Shape {
    void draw();
     default void draw(String color) {
        System.out.println("Draw shape with color " + color);
    }
}
