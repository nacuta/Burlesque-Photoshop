package actions;

import state.AppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sharpen implements ActionListener {
    private AppModel state;
    public Sharpen(AppModel state) {
        this.state =state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        state.convolution(new float [][]{
            {  0, -1,  0,},
            { -1,  5, -1,},
            {0, -1,  0 }
        });

    }
}
