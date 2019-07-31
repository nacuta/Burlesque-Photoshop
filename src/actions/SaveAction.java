package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import state.AppModel;

public class SaveAction<f> implements ActionListener {
    private final JFrame f;
    private final AppModel state;

    public SaveAction(JFrame f, AppModel s) {
        this.f = f;
        this.state = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int rVal = fc.showSaveDialog(f);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(state.getImage(), "png", fc.getSelectedFile());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
        }
    }
}


