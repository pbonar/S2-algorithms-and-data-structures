package BST;

public class Executor<T> implements IExecutor{
    String toReturn = "";

    @Override
    public void execute(Object elem) {
        toReturn += (elem);
        toReturn += " ";
    }

    @Override
    public String getResult() {
        return toReturn;
    }
}
