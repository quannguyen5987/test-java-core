package java8;

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("ve hinh");
    }

    public void s(){
        this.draw("do");
    }

}
