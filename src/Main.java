public class Main {

    public static void main(String[] args) {
        AlarmDisplay display = new AlarmDisplay();
        Monitor monitor = new Monitor(display);
        monitor.start();
    }
}
