import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import actions.*;
import state.AppModel;
import state.AppModelListener;
import wiews.ImageView;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("imagini/johnny.jpg"));
        AppModel state = new AppModel();
        JFrame f = new JFrame();

        f.setTitle("BurlesquePhotoshop");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.setSize(700, 700);
        JPanel p = new JPanel();
        ImageView iv = new ImageView();

        state.addModelListeners(new AppModelListener() {

            @Override
            public void onUpdate() {
                iv.setImage(state.getImage());
            }
        });
        p.add(iv);

        state.setImage(image);

        Map<String, ActionListener> menuButtons = new LinkedHashMap<String, ActionListener>();
        menuButtons.put("Save", new SaveAction(f, state));
        menuButtons.put("Import", new ImportAction(p, state));
        menuButtons.put("Exit", new ExitAction());
        JMenuBar mb = new JMenuBar();
        mb.add(MenuBuilder.createMenu("File", menuButtons));


        Map<String, ActionListener> menuButtons2 = new LinkedHashMap<String, ActionListener>();

        menuButtons2.put("Old Pic ", new OldPic(state));
        menuButtons2.put("Sepia ", new SepiaAction(state));
        menuButtons2.put("Undo ", new undoAction(state));
        menuButtons2.put("Cut", new CutAction(state, f, p));

        mb.add(MenuBuilder.createMenu("Edit", menuButtons2));

        Map<String, ActionListener> menuButtons3 = new LinkedHashMap<String, ActionListener>();

        menuButtons3.put("NoOp ", new NoOp(state));
        menuButtons3.put("EdgeDetect ", new EdgeDetect(state));
        menuButtons3.put("Blur ", new Blur(state));
        menuButtons3.put("Sharpen", new Sharpen(state));
        menuButtons3.put("Filters", new FiltersAction(state, p));

        mb.add(MenuBuilder.createMenu("Convolution Filters", menuButtons3));

        f.add(p);
        f.setJMenuBar(mb);
        f.setVisible(true);


    }
}
