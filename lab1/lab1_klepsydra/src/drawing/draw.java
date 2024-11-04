package drawing;

public class draw {
    public void drawChain(int n, char a){
        for (int i = 0; i < n; i++){
            System.out.print(a);
        }
    }

    public void drawLine(int n){
        for (int i = 0; i < 2*n-1; i++){
            System.out.print("X");
        }
        System.out.println();
    }
}
