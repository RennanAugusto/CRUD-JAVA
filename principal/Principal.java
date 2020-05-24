package principal;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.dao.daoPostgres.DaoPostgres;
import view.janela.Janela;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(
				new Runnable() {
			
			@Override
			public void run() {
				
				new Controller( new DaoPostgres(), new Janela());
				
			}
		});

	}

}
