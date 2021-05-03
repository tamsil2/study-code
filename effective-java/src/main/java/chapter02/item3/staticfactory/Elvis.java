package chapter02.item3.staticfactory;

// 코드 3-2 정적 팩토리 방식의 싱글톤
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis(){}
    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding invoke!!!");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
