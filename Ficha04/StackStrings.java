package Ficha04;

import java.util.ArrayList;


public class StackStrings {
    private ArrayList<String> stack;
    
    public StackStrings() {
        this.stack = new ArrayList<String>();
    }

    public String top() {
        if (this.stack != null) {
            return this.stack.get(this.stack.size()-1);
        }
        else return null;
    }

    public void push(String s) {
        this.stack.add(s);
    }

    public void pop() {
        if(!this.stack.isEmpty()) {
            this.stack.remove(this.stack.size() - 1);
        }
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public int length() {
        return this.stack.size();
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Stack de Strings\n");
        sb.append("Stack: " + this.stack.toString());
        return sb.toString();
    }
}
