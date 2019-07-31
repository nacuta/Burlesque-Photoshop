
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuBuilder {

	public static JMenu createMenu(String menuName, Map<String, ActionListener> buttons) {
        
        
        
        JMenu file= new JMenu( menuName );

        for(Entry<String, ActionListener> e : buttons.entrySet()) {
        	JMenuItem m = new JMenuItem(e.getKey());
        	m.addActionListener(e.getValue());
        	file.add(m);
        }
        return file;
	}

}
