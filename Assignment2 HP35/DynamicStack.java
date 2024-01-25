// Dynamic Stack implementation
public class DynamicStack extends StackClass{
    private int[] stack;
    private int top;

    public DynamicStack(int initialSize) {
        stack = new int[initialSize];
        top = 0;
    }

    @Override
    public void push(int value) {
        if (top == stack.length) {
            resize(stack.length * 2);
        }
        stack[top++] = value;
    }

    @Override
    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = stack[--top];
        if (top > 0 && top == stack.length / 4) {
            resize(stack.length / 2);
        }
        return value;
    }

    private void resize(int newSize) {
        int[] newStack = new int[newSize];
        System.arraycopy(stack, 0, newStack, 0, top);
        stack = newStack;
    }
}