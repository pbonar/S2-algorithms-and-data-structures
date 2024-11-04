package Lista;

public class Element<E> {
    private E value;
    private Element next;
    private Element prev;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public Element getPrev() {
        return prev;
    }

    public void setPrev(Element prev) {
        this.prev = prev;
    }

    Element(E data){
        this.value=data;
    }

    public void insertAfter(Element elem){
        elem.setNext(this.getNext());
        elem.setPrev(this);
        this.getNext().setPrev(elem);
        this.setNext(elem);}

    public void insertBefore(Element elem){
        elem.setNext(this);
        elem.setPrev(this.getPrev());
        this.getPrev().setNext(elem);
        this.setPrev(elem);}

    public void remove(){
        this.getNext().setPrev(this.getPrev());
        this.getPrev().setNext(this.getNext());
    }
}
