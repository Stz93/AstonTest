package org.example;

public class WriteStrategyFactory {

    public static WriteStrategy create(String writeCode) {
        if(!writeCode.matches("[1-2]")){
            throw new IllegalArgumentException("Number must been from 1 to 2");
        }
        switch (writeCode) {
            case "1":
                System.out.println("You choose append in file");
                return new AppendWriteStrategy();

            case "2":
                System.out.println("You choose rewrite the file");
                return new RewriteWriteStrategy();

            default:
                throw new IllegalArgumentException(
                        "Неизвестный тип стратегии: "
                );
        }
    }
      public static WriteStrategy createDefaultStrategy() {
       return new AppendWriteStrategy();
       }
}