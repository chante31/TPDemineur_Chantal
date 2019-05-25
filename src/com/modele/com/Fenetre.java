package com.modele.com;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.modele.com.Fenetre.Grille;

public class Fenetre extends JFrame {
//ceci est un commentaire	
	public Fenetre() {
		this.setTitle("DEMINEUR");
		this.setSize(1200,800);
		 //Instanciation d'un objet JPanel
        JPanel couleur = new JPanel();
 
        //Définition de la couleur de fond
        couleur.setBackground(Color.DARK_GRAY);  
 
        //On informe  notre JFrame que notre JPanel qui sera son contentPane
        this.setContentPane(couleur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);


				
		//Grille grille =new Grille();
		//this.setContentPane(grille);//
	}


	public class Grille extends JPanel{
		public void grille(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(87, 348, 69, 234);
			
		}
		
	}
}
