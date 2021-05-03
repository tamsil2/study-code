package chapter02.item3.enumtype;

// 코드 3-3 열거 타입 방식의 싱글턴 - 바람직한 방법
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("ENUM leaveTheBuilding invoke!!!");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
