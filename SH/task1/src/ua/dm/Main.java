package ua.dm;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<Character, Integer> map = new HashMap<>();
/*      If we need the user to enter the string we use Scanner.  If we don't need, we use String.    */
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
//        String string = "Hello word";
/*       We can calculate all chars, not only chars of English alphabet.                          */
        for (int i = 0; i < string.length(); i++) {
            string = string.toLowerCase();
            char chars = string.charAt(i);
            map.merge(chars, 1, (a, b) -> a + b);
        }
        System.out.println(map);
    }
}