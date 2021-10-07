import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Monitor extends Thread{

    Logger logger;
    AlarmDisplay display;
    ArrayList<NetworkInterface> networkList;

    public Monitor(AlarmDisplay display) {
        this.display = display;
        logger = Logger.getLogger("AlarmLogger");
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            networkList = Collections.list(nis);
            FileHandler fh = new FileHandler("AlarmLog.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Logger started");
        } catch (SecurityException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkConnections(){
        boolean detected = false;
        try {
            for (NetworkInterface ni : networkList) {
                if (ni.isUp() && !ni.isLoopback()){
                    logger.info(ni.toString());
                    detected = true;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
       display.connectionDetected(detected);
    }

    @Override
    public void run() {
        while (true) {
            checkConnections();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
