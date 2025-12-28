package org.example;

public class WriteStrategyFactory {

    public static WriteStrategy create(String writeCode) {
        switch (writeCode) {
            case "1":
                System.out.println("You choose append in file");
                return new AppendWriteStrategy();

            case "2":
                System.out.println("You choose rewrite the file");
                return new RewriteWriteStrategy();

            default:
                throw new IllegalStateException("Wrong action code. Choose 1-2.");
        }
    }
      public static WriteStrategy createDefaultStrategy() {
       return new AppendWriteStrategy();
       }
}