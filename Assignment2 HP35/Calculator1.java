// Enum to represent different types of Itemss
enum ItemTypes {
    ADD,
    SUB,
    MUL,
    DIV,
    VALUE
}

// Class to represent an Items in the expression
class Items {
    private ItemTypes
 type;
    private int value = 0;

    public Items(ItemTypes
 type, int value) {
        this.type = type;
        this.value = value;
    }

    public ItemTypes
 type() {
        return type;
    }

    public int value() {
        return value;
    }
}

// stack implementation
class stack {
    private int[] data;
    private int top;

    public stack(int capacity) {
        data = new int[capacity];
        top = -1;
    }

    public void push(int value) {
        data[++top] = value;
    }

    public int pop() {
        return data[top--];
    }
}

// Calculator1 class
public class Calculator1 {
    Items[] expr;
    int ip;
    stack stack;

    public Calculator1(Items[] expr) {
        this.expr = expr;
        this.ip = 0;
        this.stack = new stack(expr.length); // Creating a stack with capacity equal to the expression length
    }

    public int run() {
        while (ip < expr.length) {
            step();
        }
        return stack.pop();
    }

    public void step() {
        Items nxt = expr[ip++];
        switch (nxt.type()) {
            case ADD: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
                break;
            }
            case MUL: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
                break;
            }
            case VALUE: {
                stack.push(nxt.value());
                break;
            }
            default:
                // Handle unsupported operation or invalid input
                break;
        }
    }

    public static void main(String[] args) {
        // Sample expression: 3 4 + 2 4 + *
        Items[] expression = {
            new Items(ItemTypes
        .VALUE, 3),
            new Items(ItemTypes
        .VALUE, 4),
            new Items(ItemTypes
        .ADD, 0),
            new Items(ItemTypes
        .VALUE, 2),
            new Items(ItemTypes
        .VALUE, 4),
            new Items(ItemTypes
        .ADD, 0),
            new Items(ItemTypes
        .MUL, 0)
        };

        Calculator1 calculator = new Calculator1(expression);
        int result = calculator.run();
        System.out.println("Result: " + result); // Output: Result: 42
    }
}
