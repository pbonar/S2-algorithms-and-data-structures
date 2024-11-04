package drawing;

public class drawBridge {
    draw Draw = new draw();

    public void drawBridge(int n){
        if (n<=0)
            return;
        else if (n==1){
            System.out.println("X");
            return;
        }
        else {
            if (n > 2) drawBeginAndEnd(n);
            for (int i = 1; i < n-2; i++){
                drawMiddle(i-1, n);
            }
            Draw.drawLine(n);
            drawBeginAndEnd(n);
        }
    }

    public void  drawBeginAndEnd(int n){
        System.out.print("I");
        Draw.drawChain(2*n-3, ' ');
        System.out.print("I");
        System.out.println();
    }

    public void drawMiddle(int space, int n){
        System.out.print("I");
        Draw.drawChain(space, ' ');
        System.out.print("\\");
        Draw.drawChain((2*n-5-2*space), ' ');
        System.out.print("/");
        Draw.drawChain(space, ' ');

        System.out.print("I");
        System.out.println();
    }
}
