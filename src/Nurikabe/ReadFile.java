package Nurikabe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFile {
    /**
     * @param inputStream Zmienna przechowuje w tablicy po liniach to, co ma być wczytane z pliku.
     */
    private List<String> inputStream = new ArrayList<String>();

    /**
     *
     * @param fileName Nazwa pliku, który ma być wczytany.
     * @throws FileNotFoundException
     *
     * Konstruktor klasy ReadFile, ustawiający potrzebne parametry w ceu wczytania pliku.
     */
    ReadFile(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()){
            inputStream.add(s.next());
        }
        s.close();
    }


    /**
     * Funkcja tworzy uporządkowany słownik z danymi plansz.
     */
    public Map<String, ArrayList> createBoardsList(){
        Map<String, ArrayList> boardsInfo = new HashMap<>();
        for(int i = 0; i < inputStream.size(); i += 4){
            ArrayList tmp = new ArrayList<>();
            tmp.add(inputStream.get(i+1));
            tmp.add(inputStream.get(i+2));
            tmp.add(inputStream.get(i+3));
            boardsInfo.put(inputStream.get(i) ,tmp);
        }
        return boardsInfo;
    }

    /**
     * Getter danych wejściowych.
     */
    public List<String> getInputStream(){
        return inputStream;
    }
}
