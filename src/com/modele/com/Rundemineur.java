/**
 * 
 */
package com.modele.com;

import java.awt.Label;

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
	if (fenetre.resetter==false) {
		fenetre.EtatduJeu();
		System.out.println(" La vitoire" +fenetre.victoire+", defaite "+fenetre.defaite);
	}
	}
}
}
