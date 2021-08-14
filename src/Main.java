import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MineSweeper ms= new MineSweeper();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mayın tarlası boyutlarını giriniz");
        System.out.print("x : ");
        int x=scanner.nextInt();
        System.out.print("y : ");
        int y=scanner.nextInt();
        ms.init(x,y);
    }
}
