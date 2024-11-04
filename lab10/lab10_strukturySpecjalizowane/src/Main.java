import java.util.*;
import measuring.Timer;

// Implementacja zbiorów rozłącznych oparta na liście dowiązaniowej
class ListDisjointSet {
    ListDisjointSet rep;
    ListDisjointSet next;
    ListDisjointSet last;
    public ListDisjointSet() {
        rep = this;
        next = null;
        last = this;
    }
    public ListDisjointSet findSet() {
        return rep;
    }
    public void union(ListDisjointSet x) {
        ListDisjointSet l = rep.last;
        l.next = x.rep;
        while (l.next != null){
            l = l.next;
            l.rep = rep;
            l.last = null;
        }
        rep.last = l;
    }

    public boolean isRepresent(){
        if (last != null) return true;
        else return false;
    }
    public boolean ifUnionedWith(ListDisjointSet z){
        if (z.rep == this.rep) return true;
        else return false;
    }
}
// Implementacja zbiorów rozłącznych oparta na lesie
class ForrestDisjointSet {
    ForrestDisjointSet parrent;

    public ForrestDisjointSet() {
        this.parrent = null;
    }

    public ForrestDisjointSet findSet() {
        ForrestDisjointSet y = this;
        while (y.parrent != null)
            y = y.parrent;
        return y;
    }

    public void union(ForrestDisjointSet x) {
         x.parrent = this.findSet();
    }

    public boolean ifPartOfForrest(){
        if (parrent != null) return true;
        else return false;
    }
    public boolean ifUnionedWith(ForrestDisjointSet z){
        if (z.findSet() == this.findSet()) return true;
        else return false;
    }
}
// Modyfikacja
class Dziecko {
    String imie;
    ListDisjointSet podzbior;

    public Dziecko(String imie) {
        this.imie = imie;
        this.podzbior = new ListDisjointSet();
    }

    public void poznajDziecko(Dziecko d){
        System.out.println("Poznaje dziecko " + imie + " i " + d.imie);
        podzbior.union(d.podzbior);
    }

    public boolean czyZna(Dziecko d){
        System.out.println("Czy dziecko " + imie + " zna " + d.imie + "? " + podzbior.ifUnionedWith(d.podzbior));
        return podzbior.ifUnionedWith(d.podzbior);
    }
}
public class Main {
    public static void main(String[] args) {
//        ArrayList<Dziecko> dzieciaki = new ArrayList<>();
//
//        dzieciaki.add(new Dziecko("Tomek"));
//        dzieciaki.add(new Dziecko("Magda"));
//        dzieciaki.add(new Dziecko("Piotrek"));
//        dzieciaki.add(new Dziecko("Hania"));
//        dzieciaki.add(new Dziecko("Oliwia"));
//
//        dzieciaki.get(0).poznajDziecko(dzieciaki.get(1));
//        dzieciaki.get(0).poznajDziecko(dzieciaki.get(2));
//        dzieciaki.get(3).poznajDziecko(dzieciaki.get(4));
//        //dzieciaki.get(0).poznajDziecko(dzieciaki.get(4));
//        for (int i = 0; i < dzieciaki.size(); i++){
//            for (int j = 0; j < dzieciaki.size(); j++)
//                dzieciaki.get(i).czyZna(dzieciaki.get(j));
//            System.out.println();
//        }
        testczas();
    }

    public static void test1(){
        System.out.println("=== FORREST ===");
        ArrayList<ForrestDisjointSet> forrest = new ArrayList<>();
        forrest.add(new ForrestDisjointSet());
        forrest.add(new ForrestDisjointSet());
        forrest.add(new ForrestDisjointSet());
        forrest.add(new ForrestDisjointSet());
        forrest.add(new ForrestDisjointSet());
        forrest.get(1).union(forrest.get(0));
        forrest.get(1).union(forrest.get(2));
        forrest.get(1).union(forrest.get(3));
        for (int i = 0; i < forrest.size(); i++)
            for (int j = 0; j < forrest.size(); j++)
                System.out.println("(" + i + ", " + j + ")" + forrest.get(i).ifUnionedWith(forrest.get(j)));
        for (int i = 0; i < 4; i++) {
            System.out.println("forrest.get(" + i + ").isPart() = " + forrest.get(i).ifPartOfForrest());
        }
        System.out.println("=== LIST ===");
        ArrayList<ListDisjointSet> lista = new ArrayList<>();
        lista.add(new ListDisjointSet());
        lista.add(new ListDisjointSet());
        lista.add(new ListDisjointSet());
        lista.add(new ListDisjointSet());
        lista.add(new ListDisjointSet());
        lista.get(1).union(lista.get(0));
        lista.get(1).union(lista.get(2));
        lista.get(4).union(lista.get(3));
        //lista.get(1).union(lista.get(4));
        for (int i = 0; i < 5; i++) {
            System.out.println("lista.get(" + i + ").isRepresent() = " + lista.get(i).isRepresent());
        }
        for (int i = 0; i < lista.size(); i++)
            for (int j = 0; j < lista.size(); j++)
                System.out.println("(" + i + ", " + j + ")" + lista.get(i).ifUnionedWith(lista.get(j)));
    }
    public static void testczas(){
        int n = 1000;
        Timer t = new Timer();
        System.out.println("=== FORREST ===");
        ArrayList<ForrestDisjointSet> forrest = new ArrayList<>();
        t.start();
        for (int i = 0; i < n; i++)
            forrest.add(new ForrestDisjointSet());
        t.stop();
        System.out.println("CZAS dodawania: " + t.duration());
        t.reset();
        t.start();
        for (int i = 0; i < n; i++)
            forrest.get(new Random().nextInt(n)%n).union(forrest.get(new Random().nextInt(n)%n));
        t.stop();
        System.out.println("CZAS laczenia: " + t.duration());
        t.reset();
        t.start();
        for (int i = 0; i < n; i++)
            forrest.get(new Random().nextInt(n)%n).ifUnionedWith(forrest.get(new Random().nextInt(n)%n));
        t.stop();
        System.out.println("CZAS pytania: " + t.duration());
        System.out.println("=== LIST ===");
        ArrayList<ListDisjointSet> lista = new ArrayList<>();
        t.reset();
        t.start();
        for (int i = 0; i < n; i++)
            lista.add(new ListDisjointSet());
        t.stop();
        System.out.println("CZAS dodawania: " + t.duration());
        t.reset();
        t.start();        for (int i = 0; i < n; i++)
            lista.get(new Random().nextInt(n)%n).union(lista.get(new Random().nextInt(n)%n));
        t.stop();
        System.out.println("CZAS laczenia: " + t.duration());
        t.reset();
        t.start();
        for (int i = 0; i < n; i++)
            lista.get(new Random().nextInt(n)%n).ifUnionedWith(lista.get(new Random().nextInt(n)%n));
        t.stop();
        System.out.println("CZAS pytania: " + t.duration());
        t.reset();
    }
}