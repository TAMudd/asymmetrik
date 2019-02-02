package org.example.autocomplete;

import org.example.autocomplete.model.AutocompleteProvider;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AutocompleteProvider provider = new AutocompleteProvider();
        Scanner in = new Scanner(System.in);
        String input;
        char command = ' ';

        System.out.println("\n*** Autocomplete Provider ***\n");

        do {
            System.out.print("Please select [T]rain, [I]nput, or [Q]uit: ");
            input = in.nextLine();

            if (input.length() > 0) {
                command = input.charAt(0);

                if (command == 'T' || command == 't') {
                    System.out.print("Train: ");
                    provider.train(in.nextLine());
                } else if (command == 'I' || command == 'i') {
                    System.out.print("Input: ");
                    String output = provider.getWords(in.nextLine()).toString();
                    output = output.substring(1, output.length() -1);
                    System.out.println(output);
                }
            }
        } while (command != 'Q' && command != 'q');

        System.out.println("Goodbye!");
    }
}
