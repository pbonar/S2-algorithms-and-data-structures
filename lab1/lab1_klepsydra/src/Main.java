import drawing.*;

public class Main {
    public static void main(String[] args) {
        //rysowanie klepsydr
        drawHourglass drawh = new drawHourglass();
        for (int i = 0; i <= 6; i++) {
            System.out.println("n = " + i);
            drawh.drawHourglass(i);
            System.out.println();
        }

        //rysowanie mostow
        drawBridge drawb = new drawBridge();
        for (int i = -5; i <= 6; i++) {
            System.out.println("n = " + i);
            drawb.drawBridge(i);
            System.out.println();
        }

    }
}