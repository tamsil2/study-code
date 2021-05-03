package chapter02.item8;

// 잘 짜여진 클라이언트 코드의 예
public class Adult {
    public static void main(String[] args) {
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕~");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
