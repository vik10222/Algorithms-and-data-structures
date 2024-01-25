// Static Stack implementation
public class StaticStack extends StackClass {
    private int[] stack;
    private int top;

    public StaticStack(int size) {
        stack = new int[size];
        top = 0;
    }

    @Override
    public void push(int value) {
        if (top == stack.length) {
            throw new IllegalStateException("Stack is full");
        }
        stack[top++] = value;
    }

    @Override
    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[--top];
    }
}