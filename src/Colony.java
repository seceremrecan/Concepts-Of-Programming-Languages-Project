import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class Colony {
    char symbol;
    int rndmValue;
    int population;
    int foodStock;
    int wins;
    int losses;
    boolean eliminated;
    int eliminationRound;


    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getRndmValue() {
        return rndmValue;
    }

    public void setRndmValue(int rndmValue) {
        this.rndmValue = rndmValue;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(int foodStock) {
        this.foodStock = foodStock;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getEliminationRound() {
        return eliminationRound;
    }

    public void setEliminationRound(int eliminationRound) {
        this.eliminationRound = eliminationRound;
    }
    public void processWarAndEliminations(List<Colony> colonies, List<Colony> eliminatedColonies) {
        int colonyCount = colonies.size();
        int eliminatedCount = eliminatedColonies.size();

        for (int i = 0; i < colonyCount - 1; i++) {
            for (int j = i + 1; j < colonyCount; j++) {
                warColonies(colonies.get(i), colonies.get(j));

                if (colonies.get(i).getPopulation() <= 0 || colonies.get(i).getFoodStock() <= 0) {
                    colonies.get(i).setPopulation(0);
                    colonies.get(i).setFoodStock(0);
                    eliminatedCount++;
                    eliminatedColonies.add(colonies.get(i));
                }
                if (colonies.get(j).getPopulation() <= 0 || colonies.get(j).getFoodStock() <= 0) {
                    colonies.get(j).setPopulation(0);
                    colonies.get(j).setFoodStock(0);
                    eliminatedCount++;
                    eliminatedColonies.add(colonies.get(j));
                }
            }
        }
    }

    public void printColonies(List<Colony> colonies, int round, List<Colony> eliminatedColonies) {
        System.out.println("\n------------------------------------------------------------------------------------\n");
        System.out.println("Tur Sayisi: " + round);
        System.out.println("Koloni\t\tPopulasyon\tYemek Stogu\t   Kazanma\t   Kaybetme");

        for (Colony colony : colonies) {
            if (colony.getPopulation() > 0 && colony.getFoodStock() > 0) {
                System.out.printf("   %c\t\t%7d\t\t%7d\t\t%7d\t\t%7d\n",
                        colony.getSymbol(),
                        colony.getPopulation(),
                        colony.getFoodStock(),
                        colony.getWins(),
                        colony.getLosses());
            }
        }

        for (int i = 0; i < eliminatedColonies.size(); i++) {
            if (i == 0 || (eliminatedColonies.size() > 0 && eliminatedColonies.get(i).getSymbol() != eliminatedColonies.get(i - 1).getSymbol())) {
                if (eliminatedColonies.get(i).getEliminationRound() <= round) {
                    System.out.printf("   %c\t\t    --\t\t    --\t\t     --\t\t     --\n", eliminatedColonies.get(i).getSymbol());
                }
            }
        }


    }
    public void updateColonies(List<Colony> colonies, List<Colony> eliminatedColonies, int round) {
        A_Tactic A_tactic = new A_Tactic();
        B_Tactic B_tactic = new B_Tactic();
        A_Production A_production = new A_Production();
        B_Production B_production = new B_Production();
        Random random = new Random();


        for (Colony colony : colonies) {
            if (round == 1) {
                colony.setFoodStock(colony.getPopulation() * colony.getPopulation());

                if (random.nextBoolean()) {
                    int production = A_production.produce();
                    colony.setFoodStock(colony.getFoodStock() + production);
                } else {
                    int production = B_production.produce();
                    colony.setFoodStock(colony.getFoodStock() + production);
                }
            } else {
                if (random.nextBoolean()) {
                    int production = A_production.produce();
                    colony.setFoodStock(colony.getFoodStock() + production);
                } else {
                    int production = B_production.produce();
                    colony.setFoodStock(colony.getFoodStock() + production);
                }
            }

            if (random.nextBoolean()) {
                colony.setRndmValue(A_tactic.battle(100, 500));
            } else {
                colony.setRndmValue(B_tactic.battle(500, 1000));
            }

        }
    }
    public static void warColonies(Colony colony1, Colony colony2) {
        Random random = new Random();

        int difference = Math.abs(colony1.getRndmValue() - colony2.getRndmValue());
        int calculate = (difference * 100) / 1000;
        int tmpRndmValue = 0;
   

        if (colony1.getRndmValue() < colony2.getRndmValue()) {
            colony1.setPopulation(colony1.getPopulation() * (100 - calculate) / 100);
            tmpRndmValue = colony1.getFoodStock() * (calculate) / 100;
            colony1.setFoodStock(colony1.getFoodStock() - colony1.getFoodStock() * (calculate) / 100);

            colony2.setFoodStock(colony2.getFoodStock() + tmpRndmValue);

            colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
            colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
            colony1.setLosses(colony1.getLosses() + 1);
            colony2.setWins(colony2.getWins() + 1);
            tmpRndmValue = 0;
        } else if (colony1.getRndmValue() > colony2.getRndmValue()) {
            colony2.setPopulation(colony2.getPopulation() * (100 - calculate) / 100);
            tmpRndmValue = colony2.getFoodStock() * (calculate) / 100;
            colony2.setFoodStock(colony2.getFoodStock() - colony2.getFoodStock() * (calculate) / 100);

            colony1.setFoodStock(colony1.getFoodStock() + tmpRndmValue);
            colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
            colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
            colony2.setLosses(colony2.getLosses() + 1);
            colony1.setWins(colony1.getWins() + 1);
            tmpRndmValue = 0;
        } else {
            if (colony1.getPopulation() < colony2.getPopulation()) {
                colony1.setPopulation(colony1.getPopulation() * (100 - calculate) / 100);
                tmpRndmValue = colony1.getFoodStock() * (calculate) / 100;
                colony1.setFoodStock(colony1.getFoodStock() * (100 - calculate) / 100);

                colony2.setFoodStock(colony2.getFoodStock() + tmpRndmValue);

                colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
                colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
                colony2.setLosses(colony2.getLosses() + 1);
                colony1.setWins(colony1.getWins() + 1);
                tmpRndmValue = 0;
            } else if (colony1.getPopulation() > colony2.getPopulation()) {
                colony2.setPopulation(colony2.getPopulation() * (100 - calculate) / 100);
                tmpRndmValue = colony2.getFoodStock() * (calculate) / 100;

                colony2.setFoodStock(colony2.getFoodStock() * (100 - calculate) / 100);
                colony1.setFoodStock(colony1.getFoodStock() + tmpRndmValue);
                colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
                colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
                colony1.setLosses(colony1.getLosses() + 1);
                colony2.setWins(colony2.getWins() + 1);
                tmpRndmValue = 0;
            } else {
                int rand = random.nextInt(2);

                if (rand == 0) {
                    colony2.setPopulation(colony2.getPopulation() * (100 - calculate) / 100);
                    tmpRndmValue = colony2.getFoodStock() * (calculate) / 100;

                    colony2.setFoodStock(colony2.getFoodStock() * (100 - calculate) / 100);
                    colony1.setFoodStock(colony1.getFoodStock() + tmpRndmValue);
                    colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
                    colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
                    colony2.setLosses(colony2.getLosses() + 1);
                    colony1.setWins(colony1.getWins() + 1);
                    tmpRndmValue = 0;
                } else {
                    colony1.setPopulation(colony1.getPopulation() * (100 - calculate) / 100);
                    tmpRndmValue = colony1.getFoodStock() * (calculate) / 100;

                    colony1.setFoodStock(colony1.getFoodStock() * (100 - calculate) / 100);
                    colony2.setFoodStock(colony2.getFoodStock() + tmpRndmValue);
                    colony1.setFoodStock(colony1.getFoodStock() - colony1.getPopulation() * 2);
                    colony2.setFoodStock(colony2.getFoodStock() - colony2.getPopulation() * 2);
                    colony1.setLosses(colony1.getLosses() + 1);
                    colony2.setWins(colony2.getWins() + 1);
                    tmpRndmValue = 0;
                }
            }
        }
        if (colony1.getPopulation() <= 0) {
            colony1.setPopulation(0);
        }
        if (colony2.getPopulation() <= 0) {
            colony2.setPopulation(0);
        }
    }


}
