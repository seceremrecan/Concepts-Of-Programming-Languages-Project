import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Colony colony=new Colony();


        List<Colony> colonies = new ArrayList<>();
        List<Colony> eliminatedColonies = new ArrayList<>();
        int round = 1;

        game.initializeColonies(colonies);
        game.keepRandomSymbol(colonies);

       
        while (colonies.size() > 1) {
            colony.updateColonies(colonies, eliminatedColonies, round);

            colony.processWarAndEliminations(colonies, eliminatedColonies);

            game.increasePopulation(colonies);

            colony.printColonies(colonies, round, eliminatedColonies);

            game.filterColonies(colonies);

            round++;
        }

        System.out.println("\n------------------------------------------------------------------------------------");

        colonies.clear();
        eliminatedColonies.clear();
    }
}