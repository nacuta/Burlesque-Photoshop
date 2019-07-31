package actions;

import state.AppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdgeDetect implements ActionListener {
    private AppModel state;
    float[][] edgeKernel = {
//            {0.0f, -1.0f, 0.0f},
//            {-1.0f, 0.0f, -1.0f},
//            {0.0f, -1.0f, 0.0f},
            {-1,-1,-1},
            {-1,8,-1},
            {-1,-1,-1}
    };


    public EdgeDetect(AppModel state) {
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        state.convolution(edgeKernel);

    }
}
