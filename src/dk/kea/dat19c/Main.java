package dk.kea.dat19c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create hashmaps with characters as keys and a corresponding value starting from 0 as value, and vice-versa
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Map<Character, Integer> charToValue = new HashMap<>();
        Map<Integer, Character> valueToChar = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            charToValue.put(chars.charAt(i), i);
            valueToChar.put(i, chars.charAt(i));
        }

        // Generate a random key to shift the characters by from 0-
        Random r = new Random();
        int limit = chars.length() - 1;
        int key = r.nextInt(limit) + 1;

        try {
            // The input file to read from
            Scanner input = new Scanner(new File("E:\\Users\\Joakim\\Skoleting\\Opgaver\\Java\\Semester 3\\AliceInWonderland.txt"));
            // The output file to write the encoded text to
            PrintStream output = new PrintStream(new File("E:\\Users\\Joakim\\Skoleting\\Opgaver\\Java\\Semester 3\\AliceEncoded.txt"));
            while (input.hasNextLine()) {
                // Read in 1 line at a time
                String line = input.nextLine();
                for (int i = 0; i < line.length(); i++){
                    // Take one character at a time in the line.
                    char c = line.charAt(i);
                    if (charToValue.containsKey(c)) {
                        // Get the value associated with that character if it's part of the map of chars to be encoded.
                        int charVal = charToValue.get(c);
                        // Shift that character's value by the key
                        int newVal = charVal+key;
                        // Loop back around the map if the key shifts the value outside the range of the keys
                        if (newVal > limit) {
                            newVal = (newVal % limit) -1;
                        }
                        // Get the character corresponding to the new value and replace it in the string through splicing
                        char newChar = valueToChar.get(newVal);
                        line = line.substring(0, i) + newChar + line.substring(i + 1);
                    }
                }
                output.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(key);
        System.out.println(charToValue);
        System.out.println(valueToChar);
    }
}
