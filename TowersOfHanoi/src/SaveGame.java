import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; 

public class SaveGame {
    public static void saveToFile(Stack<TurnRecord> turns, int disks){
        Stack<TurnRecord> temp = new Stack<TurnRecord>();
        while(!turns.isEmpty()){
            TurnRecord lastTurn = turns.pop();
            temp.push(lastTurn);
        }
        try{
            File myFile = new File("game.hanoi");
            myFile.createNewFile();
            PrintStream fileWriter = new PrintStream(myFile);
            fileWriter.println(Integer.toString(disks));
            while(!temp.isEmpty()){
                TurnRecord lastTurn = temp.pop();
                fileWriter.println(lastTurn.toString());
                turns.push(lastTurn);
            }
            fileWriter.close();
        } catch(IOException o){
            System.out.println(o);
        }
        
    }

}
