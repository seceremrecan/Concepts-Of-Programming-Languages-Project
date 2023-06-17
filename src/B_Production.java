public class B_Production extends Production{
    @Override
    public int produce() {
        return (int) (Math.random() * 5);
    }
}
