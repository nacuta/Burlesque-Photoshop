package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import state.AppModel;

public class ImportAction implements ActionListener {
	private JPanel p;
	private AppModel state;
    public ImportAction(JPanel x, AppModel s) {
		this.p= x;
		this.state = s;
	}




	@Override
	 public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter( new FileNameExtensionFilter( "poze", "jpg", "jpeg", "png" ) );

        fc.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = fc.getSelectedFile();
                if (f == null) {
                    return;
                }
                try {
                	BufferedImage image = ImageIO.read( f );
                    state.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } );
        
        fc.showOpenDialog(null);
    }
}
