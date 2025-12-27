package org.example;

import java.util.Scanner;

public class InputStrategyFactory {
    static Scanner scannerForInput = new Scanner(System.in);
    public static InputStrategy create(String inputCode){
        if(!inputCode.matches("[1-4]")){
            throw new IllegalArgumentException("Wrong action code. Choose 1-4 or 0");
        }
        switch (inputCode){
            case "1":
                System.out.println("You choose file");
                String filePath = Main.INPUT_PATH;
                return new FileInputStrategy(filePath);
            case "2":
                System.out.println("Enter quantity:");
                while (!scannerForInput.hasNextInt()) {
                    System.out.print("It must be a number: ");
                    scannerForInput.next();
                }
                int countRandom = scannerForInput.nextInt();
                scannerForInput.nextLine();
                return new RandomInputStrategy(countRandom);
            case "3":
                System.out.println("Enter quantity:");
                while (!scannerForInput.hasNextInt()) {
                    System.out.print("It must be a number: ");
                    scannerForInput.next();
                }
                int countManual = scannerForInput.nextInt();
                scannerForInput.nextLine();
                return new ManualInputStrategy(countManual);
            default:
                throw new IllegalArgumentException("Incorrect");

        }
    }
}