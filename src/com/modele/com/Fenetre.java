package com.modele.com;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.modele.com.Fenetre.Grille;

public class Fenetre extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int spacing=5;

	//ceci est un commentaire d'essai	
	public Fenetre() {
		this.setTitle("DEMINEUR");
		this.setSize(1200,850);
		 //Instanciation d'un objet JPanel
        JPanel couleur = new JPanel();
 
        //Définition de la couleur de fond
        //couleur.setBackground(Color.DARK_GRAY);  
 
        //On informe  notre JFrame que notre JPanel qui sera son contentPane
        this.setContentPane(couleur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		
				
		Grille grille =new Grille();
		this.setContentPane(grille);//
	}


	public class Grille extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(0, 0, 1200, 800);
			g.setColor(Color.DARK_GRAY);
			for (int i =0;i<16; i++) {
				for (int j=0 ; j<9; j++) {
					
					g.fillRect(spacing +i*80,spacing +j*80+80, 80-2*spacing,80-2*spacing);
				}
			}
			
			
			
		}
		
	}
}
