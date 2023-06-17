
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.List;

public class Game {
    public void filterColonies(List<Colony> colonies) {
        colonies.removeIf(colony -> colony.getPopulation() <= 0 || colony.getFoodStock() <= 0);
    }

    public  void keepRandomSymbol(List<Colony> colonies) {
        Random random = new Random();

        for (Colony colony : colonies) {
            colony.symbol = randomSymbol();
        }
    }

    public  void increasePopulation(List<Colony> colonies) {
        for (Colony colony : colonies) {
            colony.population += colony.population * 0.2;
        }
    }

    public static char randomSymbol() {
        char[] used_symbols = new char[94];
        char symbol;
        Random random = new Random();

        do {
            symbol = (char) (33 + random.nextInt(94));
        } while (isInvalidSymbol(symbol, used_symbols));

        used_symbols[symbol - 33] = symbol;

        return symbol;
        
    }
    private static boolean isInvalidSymbol(char symbol, char[] usedSymbols) {
        return symbol >= '0' && symbol <= '9' ||
                symbol >= 'A' && symbol <= 'Z' ||
                symbol >= 'a' && symbol <= 'z' ||
                symbol == ' ' ||
                usedSymbols[symbol - 33] != '\0';
    }

    public  void initializeColonies(List<Colony> colonies) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the populations for colonies (separated by spaces): ");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");

        for (String token : tokens) {
            try {
                int population = Integer.parseInt(token);
                Colony colony = new Colony();
                colony.eliminationRound = -1;
                colony.population = population;
                colony.foodStock = colony.population * colony.population;
                colony.wins = 0;
                colony.losses = 0;
                colonies.add(colony);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter only numeric values.");
                colonies.clear();
                return;
            }
        }
    }

}


