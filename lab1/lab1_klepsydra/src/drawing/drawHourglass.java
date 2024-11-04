package drawing;
public class drawHourglass {
    draw Draw = new draw();
    public void drawWithSpaces(int space, int o){
        Draw.drawChain(space, ' ');
        System.out.print("X ");
        if (o > 0){
            Draw.drawChain(o, 'O');
            System.out.print(" X");
        }
        else {
            System.out.print("X");
        }
        System.out.println();
    }

    public void drawHourglass(int n){
        if (n <= 0){
            return;
        }
        else if (n == 1){
            System.out.println("X");
            return;
        }
        else {
            Draw.drawLine(n);
            for (int i = 1; i < n-1; i++)
                drawWithSpaces(i, (2*n-5)-2*i);
            Draw.drawChain(n-1, ' ');
            System.out.println("X");
            for (int i = n-2; i > 0; i--)
                drawWithSpaces(i, (2*n-5)-2*i);
            Draw.drawLine(n);
            return;
        }
    }
}
