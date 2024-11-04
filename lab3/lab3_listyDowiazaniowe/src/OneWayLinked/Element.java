package OneWayLinked;

class Elementum<E> {
    private E value;
    private Elementum next = null;

    public Elementum(E value) {
        this.value = value;
    }

    public Elementum(E value, Elementum next) {
        this.value = value;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Elementum getNext() {
        return next;
    }

    public void setNext(Elementum next) {
        this.next = next;
    }

    void Element(E data) {
        this.value = data;
    }

    public void insertAfter(Elementum elem) {
        elem.setNext(this.getNext());
        this.setNext((Elementum) elem);
    }

    public void insertFirst(Elementum elem) {
        elem.setNext((Elementum) this);
    }
}