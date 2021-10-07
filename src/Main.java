public class Main {
    protected static boolean shouldRun;
    public static void main(String[] args) {
        shouldRun = true;
        AlarmDisplay display = new AlarmDisplay();
        Monitor monitor = new Monitor(display);
        monitor.start();
    }
}
