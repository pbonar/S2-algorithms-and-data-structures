public class Substring {
    int index = -1;
    int length = 0;

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Substring{" +
                "index=" + index +
                ", length=" + length +
                '}';
    }
}
