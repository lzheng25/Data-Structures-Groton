import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LoadGame {

    int numDisks;
    Queue<LoadedRecord> turns = new LinkedList<LoadedRecord>();

    public LoadGame(String fileName){
        loadFromFile(fileName);
    }

    public int getNumDisks(){
        return numDisks;
    }

    public int getNumTurns(){
        return turns.size();
    }

    public LoadedRecord getNextTurn(){
        if(!turns.isEmpty()) return turns.remove();
        else return null;
    }

    public void loadFromFile(String file){
        
        try{
            File myFile = new File("game.hanoi");
            Scanner fileReader = new Scanner(myFile);

            numDisks = Integer.parseInt(fileReader.nextLine());
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] lineParts = line.split(" ");
                turns.add(new LoadedRecord(Integer.parseInt(lineParts[0]), Integer.parseInt(lineParts[1])));
            }


        }catch(FileNotFoundException e){
            System.out.println("File doesn't exist");
            System.exit(1);
        }

        System.out.println(turns.size());
    }
}
