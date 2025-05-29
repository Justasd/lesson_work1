package Runtime;

import java.util.Stack;
import TypeSystem.Type;

public class ExecutionStack {
    private Stack<Type> stack = new Stack<>();

    public void push(Type value) {
        stack.push(value);
    }

    public Type pop() {
        return stack.pop();
    }

    public Type peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
