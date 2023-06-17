public class A_Production extends Production{

    @Override
    public int produce() {
        return (int) (Math.random() * 6 + 5);
    }
}