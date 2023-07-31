package Nurikabe;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Klasa odpowiedzialna za funkcjonalność gry.
 */
public class Game {
    /**
     * @param readFile Obiekt klasy ReadFile wczytujący nierozwiązaną planszę.
     * @param readResolvedFile Obiekt klasy ReadFile wczytującu rozwiązaną planszę.
     * @param board Plansza gry.
     * @param resolvedBoard Rozwiązana plansza gry.
     * @param dimensions Wymiary planszy.
     */

    String base = "resources/";
    private ReadFile readFile = new ReadFile("resources/sample3.txt");
    private ReadFile readResolvedFile = new ReadFile("resources/sampl3rozw.txt");
    private byte[][] board, resolvedBoard;
    private Point dimensions;

    /**
     * Konstruktor klasy, definiujący  planszę nierozwiązaną jak i rozwiązaną.
     */
    public Game() throws FileNotFoundException {
        Utils util = new Utils();
        board = util.getBoard(readFile.getInputStream());
        resolvedBoard = util.getBoard(readResolvedFile.getInputStream());
        dimensions = util.getDimensions(readFile.getInputStream());
    }

    public void chooseBoard(String choice) throws FileNotFoundException{
        Utils util = new Utils();

        ReadFile readChosenFile = new ReadFile(base+choice);
        ReadFile readChosenResolvedFile = new ReadFile(base+"sampl"+choice.charAt(6)+"rozw.txt");

        board = util.getBoard(readChosenFile.getInputStream());
        resolvedBoard = util.getBoard(readChosenResolvedFile.getInputStream());
        dimensions = util.getDimensions(readChosenFile.getInputStream());
    }

    public void loadBoard(String load) throws FileNotFoundException{
        Utils util = new Utils();

        ReadFile readLoadedFile = new ReadFile(load);
        ReadFile readLoadedResolvedFile = new ReadFile("resources/sampl3rozw.txt");

        board = util.getBoard(readLoadedFile.getInputStream());
        resolvedBoard = util.getBoard(readLoadedResolvedFile.getInputStream());
        dimensions = util.getDimensions(readLoadedFile.getInputStream());
    }

    /**
     * Gettery planszy nierozwiązanej, rozwiązanej i ich wymiarów.
     */
    public byte[][] getBoard() {
        return board;
    }

    public byte[][] getResolvedBoard(){
        return resolvedBoard;
    }

    public Point getDimensions() {
        return dimensions;
    }
    /**
     * @param board
     * 127 pole puste
     * 126 wyspa
     * 125 woda
     * 124 poprawna odpowiedź
     */
}
