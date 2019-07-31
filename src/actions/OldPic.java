package actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import state.AppModel;

public class OldPic implements ActionListener {

	private AppModel model;
	public OldPic( AppModel s) {
		this.model = s;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.grayscale();
		
	}

}
