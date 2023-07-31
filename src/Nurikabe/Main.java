package Nurikabe;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * Uruchomienie całej aplikacji w jednym wątku
         */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
