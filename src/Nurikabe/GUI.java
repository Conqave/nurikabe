package Nurikabe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class GUI extends JFrame implements ActionListener {
    /**
     * @param newGame Przycisk odpowiedzialny za otwarcie nowego okna gry.
     * @param hint Przycisk odpowiedzialny za udzielenie podpowiedzi podczas gry.
     * @param check Przycisk odpowiedzialny za sprawdzenie, czy gra została poprawnie rozwiązana.
     * @param game obiekt klasy Game.
     * @param buttonWidth Wymiar x przycisku.
     * @param buttonHeight Wymiar y przycisku.
     * @param buttonMarginTop Margines górny.
     * @param buttonMarginRight Margines z prawej strony.
     */
    private JButton quickStart, newGame, loadBoard;
    private Game game = new Game();
    private byte[][] board = game.getBoard();
    private int buttonWidth = 128;
    private int buttonHeight = 48;
    private int buttonMarginTop = 64;
    private int buttonMarginRight = 32;

    public GUI() throws FileNotFoundException {
        /**
         * Utworzenie okna z menu głównym gry o wielkości 208 x 400.
         */
        super("Nurikabe game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(208, 400);
        setResizable(false);

        /**
         * Ustawienie przycisku nowej gry
         */
        quickStart = new JButton("Quick start");
        quickStart.setBounds(buttonMarginRight, buttonMarginTop, buttonWidth, buttonHeight);
        quickStart.setForeground(Color.BLACK);
        quickStart.addActionListener(this);
        add(quickStart);

        newGame = new JButton("Choose board");
        newGame.setBounds(buttonMarginRight, buttonMarginTop * 2, buttonWidth, buttonHeight);
        newGame.setForeground(Color.BLACK);
        newGame.addActionListener(this);
        add(newGame);

        loadBoard = new JButton("Load board");
        loadBoard.setBounds(buttonMarginRight, buttonMarginTop * 3, buttonWidth, buttonHeight);
        loadBoard.setForeground(Color.BLACK);
        loadBoard.addActionListener(this);
        add(loadBoard);

        setLayout(null);
        setVisible(true);
    }


    /**
     * Obłsuga przycisków w menu głównym.
     * Szybki start gry
     * Wybór planszy
     * Wczytanie planszy z pliku
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == quickStart) {
            try {
                GameGui gameGui = new GameGui(game);
                gameGui.start();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if (obj == newGame) {
            JFrame choose = new JFrame("Choice of board");
            choose.setDefaultCloseOperation(EXIT_ON_CLOSE);

            JButton A = new JButton("5x5A");
            JButton B = new JButton("4x4");
            JButton C = new JButton("5x5B");
            JButton D = new JButton("10x10");
            JButton E = new JButton("7x7");

            choose.setLayout(null);
            choose.setSize(1000, 200);
            A.setBounds(buttonMarginRight, buttonMarginTop, buttonWidth, buttonHeight);
            B.setBounds(buttonMarginRight * 6, buttonMarginTop, buttonWidth, buttonHeight);
            C.setBounds(buttonMarginRight * 12, buttonMarginTop, buttonWidth, buttonHeight);
            D.setBounds(buttonMarginRight * 18, buttonMarginTop, buttonWidth, buttonHeight);
            E.setBounds(buttonMarginRight * 24, buttonMarginTop, buttonWidth, buttonHeight);

            ActionListener choiceA = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        choose.setVisible(false);
                        game.chooseBoard("sample1.txt");
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }
                }
            };
            ActionListener choiceB = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        choose.setVisible(false);
                        game.chooseBoard("sample2.txt");
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }
                }
            };
            ActionListener choiceC = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        choose.setVisible(false);
                        game.chooseBoard("sample3.txt");
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }
                }
            };
            ActionListener choiceD = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        choose.setVisible(false);
                        game.chooseBoard("sample4.txt");
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();

                    }
                }
            };
            ActionListener choiceE = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        choose.setVisible(false);
                        game.chooseBoard("sample5.txt");
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            };
            A.addActionListener(choiceA);
            B.addActionListener(choiceB);
            C.addActionListener(choiceC);
            D.addActionListener(choiceD);
            E.addActionListener(choiceE);
            choose.add(A);
            choose.add(B);
            choose.add(C);
            choose.add(D);
            choose.add(E);
            choose.setVisible(true);

        } else if (obj == loadBoard) {
            JFrame location = new JFrame("Enter board's location");
            JTextField write = new JTextField(20);
            JButton load = new JButton("Load");
            location.add(load);
            location.add(write);
            write.setBounds(50, 20, 400, 60);
            load.setBounds(210, 100, 80, 20);

            location.setLayout(null);
            location.setSize(500, 180);
            location.setVisible(true);

            ActionListener loadAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        location.setVisible(false);
                        game.loadBoard(write.getText());
                        GameGui gameGui = new GameGui(game);
                        gameGui.start();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            };

            load.addActionListener(loadAction);

        }

    }
}
