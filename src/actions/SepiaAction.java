package actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import state.AppModel;

public class SepiaAction implements ActionListener {

	
	private AppModel model;
	




	public SepiaAction(AppModel state) {
		// TODO Auto-generated constructor stub
		this.model= state ;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		model.sepiaScale();
        
		
	}

}
