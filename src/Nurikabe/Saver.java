package Nurikabe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    public void save(byte[][] tab, String location){
        StringBuilder builder = new StringBuilder();

        builder.append("D:");
        builder.append(tab.length+"x"+tab.length+"\n");
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab.length; j++)
            {
                builder.append(tab[i][j]+"");
                if(j < tab.length - 1)
                    builder.append(" ");
            }
            builder.append("\n");
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(location));
            writer.write(builder.toString());//save the string representation of the board
            writer.close();
        }
        catch(IOException e){
            System.out.println("Wystapil blad przy zapisywaniu");
        }

    }

}


