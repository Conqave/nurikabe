package Nurikabe;

import java.awt.*;
import java.util.List;

/**
 * Klasa odpowiedzialna za operacje na danych i ich obsługę
 */
public class Utils {
    /**
     * Funkcja odpowiedzialna za odczyt wymiarów planszy.
     */
    Point getDimensions(List<String> inputStream){
        int xDimension = 0, yDimension = 0;
        if(inputStream.get(0).substring(0,2).equals("D:") && !inputStream.get(0).substring(2,3).equals("1")){
            xDimension = Integer.parseInt(inputStream.get(0).substring(2,3));
            yDimension = Integer.parseInt(inputStream.get(0).substring(4,5));
        }
        else if(inputStream.get(0).substring(0,2).equals("D:") && inputStream.get(0).substring(2,3).equals("1")){
            xDimension = Integer.parseInt(inputStream.get(0).substring(3,4))+10;
            yDimension = Integer.parseInt(inputStream.get(0).substring(6,7))+10;
        }
        else {
            System.out.println("Wystąpił błąd podczas analizy wymiarów planszy");
        }
        Point pnt = new Point(xDimension, yDimension);
        return pnt;
    }

    /**
     * Funkcja wyświetlająca w konsoli planszę.
     */
    public void showBoard(List<String> inputStream){
            int l = 1;
                for(int i = 0; i < getDimensions(inputStream).y; i++){
                    for(int j = 0; j < getDimensions(inputStream).x; j++){
                        System.out.print(inputStream.get(l) + " ");
                        l++;
                    }
                    System.out.println("");
                }
    }
    /**
     * Funkcja wyświetlająca w konsoli planszę.
     */
    public void showByteBoard(byte[][] inputStream){
        for(int i = 0; i < inputStream.length; i++){
            for(int j = 0; j < inputStream[i].length; j++){
                System.out.print(inputStream[i][j] + " ");
            }
            System.out.println("");
        }
    }
    /**
     * Funkcja zwraca planszę w postaci tablicy dwuwymiarowej.
     */
    public byte[][] getBoard(List<String> inputStream){
        byte[][] tmp = new byte[getDimensions(inputStream).x][getDimensions(inputStream).y];
        int l = 1;
        for(int i = 0; i < getDimensions(inputStream).y; i++){
            for(int j = 0; j < getDimensions(inputStream).x; j++){
                tmp[i][j] = Byte.parseByte(inputStream.get(l));
                l++;
            }
            System.out.println("");
        }
        return tmp;
    }
}
