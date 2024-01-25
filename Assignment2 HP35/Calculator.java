
public class Calculator {
    Item[] expr;
    int ip;
    StackClass stack;

    public Calculator(Item[] expr, StackClass stack) {
        this.expr = expr;
        this.ip = 0;
        this.stack = stack;
    }

    public int run() {
        while (ip < expr.length) {
            step();
        }
        return stack.pop();
    }

    public void step() {
        Item nxt = expr[ip++];
        switch (nxt.type()) {
            case ADD: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
                break;
            }
            case SUB: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x - y);
                break;
            }
            case DIV: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x / y);
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

        }

    
    }

    public static void main(String[] args) {
       /*  Item[] expr = {
            Item.value(1),
            Item.value(2),
            Item.value(3),
            Item.value(4),
            Item.value(5),
            Item.value(6),
            Item.value(7),
            Item.value(8),
            Item.value(9),
            Item.value(10),
            Item.value(11),
            Item.value(12),
            Item.value(13),
            Item.value(14),
            Item.value(15),
            Item.value(16),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add(),
            Item.Mul(),
            Item.Add()
        };
        StackClass dynamicStack = new DynamicStack(10);
        Calculator calc = new Calculator(expr, dynamicStack);
        int res = calc.run();
    
        // Check the type of stack and print accordingly
        if (dynamicStack instanceof DynamicStack) {
            System.out.println("Using DynamicStack");
        } else if (dynamicStack instanceof StaticStack) {
            System.out.println("Using StaticStack");
        }
    
        System.out.println("Calculator: res = " + res);
        */

        System.out.println("calculating bench marks");
        StaticStack staticStk = new StaticStack(1024);
        int z= 0; 
        /*while(z <1024){
            staticStk.pop();
            z++;
        }

        */
        int x =0;



        long min = 999999999;
        while(x < 1000){

        long startTime = System.nanoTime(); // Start the timer
            for(int k=0; k<1000; k++){
                staticStk.push(k);
        }
        
            for(int k = 0; k < 1000; k++) {
                staticStk.pop();
            }

         long endTime = System.nanoTime(); // End the timer   
           if(endTime -startTime <min) { min = endTime -startTime;

           }
           staticStk = new StaticStack(1024); // Reset the stack
          x++;

        }
        System.out.println("static time "+ min);

        System.out.println("calculating bench marks for DynamicStack");

    DynamicStack dynamicStk = new DynamicStack(1024);
 

    int y = 0;
    long Min = 999999999;
    
    while(y < 1000) {
        long startTime = System.nanoTime(); // Start the timer
        for(int k = 0; k < 1000; k++) {
            dynamicStk.push(k);
        }
         for(int k = 0; k < 1000; k++) {
            dynamicStk.pop();
        }
        
        long endTime = System.nanoTime(); // End the timer   
        if(endTime - startTime < Min) {
            Min = endTime - startTime;
        }
    
        dynamicStk = new DynamicStack(1024); // Reset the stack
        y++;
    }
    
    System.out.println("DynamicStack time: " + Min);
    



    }

}
