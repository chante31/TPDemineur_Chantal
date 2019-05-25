/**
 * 
 */
package com.modele.com;

import com.modele.com.Fenetre.Grille;

/**
 * @author chantal.roukatchinde
 *
 */
public class Rundemineur implements Runnable {
	Fenetre fenetre=new Fenetre();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Rundemineur()).start();

	}
	
	
public void run() {
	while(true) {
	fenetre.repaint();
	}
}
}
