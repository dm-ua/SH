package ua.dm;

import java.util.EmptyStackException;
import java.util.Stack;

public class TSStack {

   public static void push(Stack stack, Object object) {
        try {
            stack.push(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   public static Object pop(Stack stack) {
        Object object = null;
        try {
            object = stack.pop();
            System.out.println(object);
        }catch (EmptyStackException e){
            System.out.println("empty stack");
        }
        return object;
    }
}
