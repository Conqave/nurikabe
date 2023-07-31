package Nurikabe;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Hint {
    /**
     * @param boardSet Plansza z zdefiniowana już przez użytkokwanika.
     */
    private byte[][] boardSet;
    private Game game1;

    /**
     *
     * Konstruktor klasy, definiuje tablicę, którą zaznaczył użytkownik.
     */
    Hint(byte[][] boardSet, Game game){
        this.boardSet = boardSet;
        game1 = game;
    }

    /**
     * Funkcja odpowiedzialna za załadowanie planszy z odpowiedziami.
     *
     */
    public byte[][] getResolvedBoard() throws FileNotFoundException {
        return game1.getResolvedBoard();
    }

    /**
     * @return Lista punktów z poprawnymi rozwiązaniami.
     */
    public ArrayList<Point> createGoodAnswersPoints() throws FileNotFoundException {
        ArrayList<Point> temp = new ArrayList<Point>();
        for(int i = 0; i < getResolvedBoard().length; i++){
            for(int j = 0; j < getResolvedBoard().length; j++){
                if((getResolvedBoard()[i][j]==126)&&boardSet[i][j]!=126){
                    Point temp2 = new Point(i, j);
                    temp.add(temp2);
                }
            }
        }
        return temp;
    }

    /**
     * @return Losuje poprawną odpowiedź, z przedziału, który pozostał nie zaznaczony przez użytownika.
     */
    public  Point getRandomHint() throws FileNotFoundException {
        Random random = new Random();
        random.nextInt(createGoodAnswersPoints().size());

        return createGoodAnswersPoints().get(random.nextInt(createGoodAnswersPoints().size()));
    }
}
