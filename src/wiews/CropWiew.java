package wiews;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CropWiew extends Component {
    private BufferedImage image;

    private Point p1;
    private Point p2;

    private CropWiewListener cropWiewListener;

    public CropWiew(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(p1==null){
                    p1=new Point(e.getX(),e.getY());
                    repaint();
                    return;
                }
                if(p2==null){
                    p2=new Point(e.getX(),e.getY());
                    cropWiewListener.onCrop(new Rectangle(p1,new Dimension(p2.x-p1.x,p2.y-p1.y)));
                    repaint();
                    p1=null;
                    p2=null;
                    return;
                }

            }
        });
    }


    public void paint(Graphics g){
        if(image!= null){
            g.drawImage(image,0,0,null);
        }
        if(p1 !=null) {
            g.setColor(Color.RED);
            g.fillOval(p1.x, p1.y, 10, 10);
        }
        if(p2 !=null) {
            g.setColor(Color.RED);
            g.fillOval(p2.x, p2.y, 10, 10);
        }
    }


    public void setListener(CropWiewListener cropWiewListener) {
        this.cropWiewListener= cropWiewListener;
    }

    public void setImage(BufferedImage image){
        this.image= image;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.setSize(new Dimension(image.getWidth(), image.getHeight()));
        this.repaint();
    }
    }
