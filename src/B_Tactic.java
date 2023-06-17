import java.util.Random;

public class B_Tactic extends Tactic{

    @Override
    public int battle(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}