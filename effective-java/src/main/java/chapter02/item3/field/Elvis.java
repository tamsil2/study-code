package chapter02.item3.field;

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, im outta here!!!");
    }

    public static void main(String[] args) {
        Elvis el = Elvis.INSTANCE;
        el.leaveTheBuilding();
    }
}
