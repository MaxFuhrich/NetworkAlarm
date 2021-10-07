import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AlarmDisplay extends JFrame {
    JPanel panel;
    public AlarmDisplay() throws HeadlessException {
        super("Network Alarm");
        this.setSize(200, 200);
        panel = new JPanel();
        panel.setBackground(Color.GREEN);
        this.add(panel);
        this.setAlwaysOnTop(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
    public void connectionDetected(boolean detected) {
        if (detected)
            panel.setBackground(Color.RED);
        else
            panel.setBackground(Color.GREEN);
    }


}
