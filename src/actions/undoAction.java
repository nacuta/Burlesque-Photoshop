package actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;


import state.AppModel;

public class undoAction extends AbstractAction implements ActionListener {
	
		
		private AppModel model;
		public undoAction(AppModel model) {
			this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
			this.model = model;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.undo();
		}

	}