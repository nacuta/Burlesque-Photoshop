package actions;

import state.AppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Blur implements ActionListener {
    private AppModel state;
    public Blur(AppModel state) {
        this.state =state;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        state.convolution(new float[][]{
                {1/9f,1/9f,1/9f,},
                {1/9f,1/9f,1/9f,},
                {1/9f,1/9f,1/9f,}
        });
    }
}
