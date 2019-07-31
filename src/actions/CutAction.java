package actions;

import state.AppModel;
import wiews.CropWiew;
import wiews.CropWiewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CutAction implements ActionListener {
    private AppModel model;
    private JPanel p;
    private JFrame f;


    public CutAction(AppModel s, JFrame f, JPanel p) {
        this.model = s;
        this.f = f;
        this.p = p;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oldTittle = f.getTitle();
        f.setTitle("Cutting");
        CropWiew cv = new CropWiew();
        cv.setImage(model.getImage());
        p.setVisible(false);
        cv.setListener(new CropWiewListener() {

            @Override
            public void onCrop(Rectangle rect) {
                System.out.println(rect);
                model.cutPic(rect);
                f.remove(cv);
                p.setVisible(true);
                f.setTitle(oldTittle);


            }
        });
        f.add(cv);
    }

}
