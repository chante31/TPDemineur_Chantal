package com.modele.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int espace=5;
	int mx = -100;
	int my= -100;
	Random rand = new Random();
	Date TempsDebutJeu=new Date();
	Date tempsFinJeu;
	/**
	 *
	 * @param mines spécifie l'état de la case si 0 pas de mines si 1, la case contient une mine
	 * @param case_voisine spécifie combien de case voisines contiennent les mines. L'état va de 0 à 8
	 */
	int [][]mines=new int[16][9];
	int [][] case_voisine =new int[16][9];
	boolean [][] case_devoile= new boolean[16][9];
	boolean [][] drapeau = new boolean[16][9];// permet de savoir si une case est marqué ou pas
	int voisin=0;
	boolean marque=false;
	String messageDeLaVictoire="rien encore!!";
	public int smileyX= 605;
	public int smileyY=5;
	public boolean Joyeux =true;
	public int timeX=1050;
	public int timeY=5;
	public int sec =0;
	public boolean victoire = false;
	public boolean defaite=false;
	public int smileyCentreX= smileyX+35;
	public int smileyCentreY= smileyY+35;
	public int drapeauX = 445;
	public int drapeauY = 5;
	public int drapeauCentreX = drapeauX+35;
	public int drapeauCentreY = drapeauY+35;
	public int espaceX =90;
	public int espaceY =10;
	public int moinsX=espaceX +160; //250
	public int minusY=espaceY; // pour avoir ce nombre on fait : (80-60)/2
	
	public int plusX=espaceX+ 240;// 250+80
	public int plusY=espaceY; //

	
	
	public boolean resetter =false;
	public int MessageVictoireX = 700;
	public int MessageVictoireY= -50;
	
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
		
		

		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				if (rand.nextInt(100)<20) {
					mines[i][j] =1;

				}else {
					mines[i][j]=0;

				}
				/* si on rajoute true à cet endroit on pourra dévoiler toutes les cases 
				 * et on gagne.
				 */
				case_devoile[i][j]=false;
			}
		}

		
		
		
		
		
		
		
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				// très important 
				voisin =0;
				for (int m =0;m<16; m++) {
					for (int n=0 ; n<9; n++) {
						if(!(m==i && n==j)) {
							if(EstVoisin(i,j,m,n)==true) {
								voisin++;
					}
					}
				}
				case_voisine[i][j]=voisin;
			}
			
		}
resetter =false;
	}

		

		Grille grille =new Grille();
		// on relie la grille à notre fenetre
		this.setContentPane(grille);//

		Bouger bouger= new Bouger();
		this.addMouseMotionListener(bouger);

		Click click = new Click();
		this.addMouseListener(click);

	}

	/**
	 * Tout ce qu'on mettra dans cette méthode va 
	 * apparaitre dans notre fenetre 
	 */
	public class Grille extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 1286, 829);
			for (int i =0;i<16; i++) {
				for (int j=0 ; j<9; j++) {
					g.setColor(Color.GRAY);
					/**
					 * s'il y'a une mine, la case doit être égale à 1 et coloré en jaune.
					 */		
					if (mines[i][j]== 1) {
						g.setColor(Color.yellow);
					}
					if (case_devoile[i][j]== true) {
						g.setColor(Color.white);
						if (mines[i][j]==1){
							g.setColor(Color.red);
						}
					}
					if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
						g.setColor(Color.green);
					}
					g.fillRect(espace +i*80,espace +j*80+80, 80-2*espace,80-2*espace);
					if (case_devoile[i][j]== true) {
						g.setColor(Color.black);

						if (mines[i][j]==0 && case_voisine[i][j] !=0){
							switch (case_voisine[i][j]) {
							case 1:
								g.setColor(Color.blue);
								break;
							case 2:
								g.setColor(Color.green);
								break;
							case 3:
								g.setColor(Color.red);
								break;
							case 4:
								g.setColor(new Color(0,0,128));
								break;
							case 5:
								g.setColor(new Color(178,34,34));
								break;
							case 6:
								g.setColor(new Color(72,209,204));
								break;
							case 7:
								g.setColor(Color.black);
								break;
							case 8:
								g.setColor(Color.darkGray);
								break;
							}

							g.setFont(new Font("Tahoma", Font.BOLD,40));
							g.drawString(Integer.toString(case_voisine[i][j]),i*80+27, j*80+80+55);
						}else if( mines[i][j]==1){
							g.fillRect(i*80+10+20, j*80+80+20, 20, 40);
							g.fillRect(i*80+20, j*80+80+10+20, 40, 20);
							g.fillRect(i*80+5+20, j*80+80+5+20, 30, 30);
							g.fillRect(i*80+5+20, j*80+80+5+20, 30, 30);
							g.fillRect(i*80+38, j*80+80+15, 4, 50);
							g.fillRect(i*80+15, j*80+80+38, 50, 4);
						}
					}

					
					
					//drapeau painting
					
					if (drapeau[i][j]==true) {
						g.setColor(Color.BLACK);
						g.fillRect(i*80+32+5, j*80+80+15+5, 5, 40);
						g.setColor(Color.red);
						g.fillRect(i*80+16+5,j*80+80+15+5,20,15);
						g.setColor(Color.BLACK);
						g.drawRect(i*80+16+5, j*80+80+15+5, 20, 15);
						g.drawRect(i*80+17+5, j*80+80+16+5, 18, 13);
						g.drawRect(i*80+18+5, j*80+80+17+5, 16, 11);
						
					}

				}
			}
			
			
			
			/*Configuration du ZOUM 
			 *  dans  une boite on va  dessiner un rectangle noi, et das ce rectanle, 
			 * on va dessiner un rectangle blanc.et dans ce rectangle on va afficher plus ou moins, 
			 * 
			 */
		 
			g.setColor(Color.BLACK);
			g.fillRect(espaceX, espaceY, 300, 60);
			g.setColor(Color.white);
			g.fillRect(moinsX+5, minusY+10, 40, 40);
			g.fillRect(plusX+5,plusY+10 ,40, 40);
			
		
			
			g.setFont(new Font("Tahoma",Font.PLAIN,35));
			g.drawString("espace", espaceX+15,espaceY+45);
			
			g.setColor(Color.BLACK);
			g.fillRect(moinsX+15, minusY+27, 20, 6);// 40-6/2 +10=27
			g.fillRect(plusX+15,plusY+27 ,20, 6);
			/* trace un - vertical pour en faire un plus */
			g.fillRect(plusX+22,plusY+20 ,6, 20);
			g.setColor(Color.white);
			g.setFont(new Font("Tahoma",Font.PLAIN,30));
			if(espace<10) {
				g.drawString("0"+Integer.toString(espace), moinsX+49, 55);
			}else {
			g.drawString(Integer.toString(espace), moinsX+49,55);
			}
			
			
			// mise en forme du smile 
			g.setColor(Color.yellow);
			g.fillOval(smileyX, smileyY, 70, 70);
			g.setColor(Color.BLACK);
			g.fillOval(smileyX+15, smileyY+20, 10, 10);
			g.fillOval(smileyX+45, smileyY+20, 10, 10);
			/**
			 *Cette condition permet au smiler d'afficher son humeur
			 *s'il est content, la barre du milieu sera tourné vers le haut,
			 *sinon elle sera tournée vers le bas pour montrer sa tristesse 
			 * 
			 * 
			 */
			if (Joyeux==true)
			{
				g.fillRect(smileyX+20, smileyY+50, 30, 5);
				g.fillRect(smileyX+15, smileyY+45, 5, 5);
				g.fillRect(smileyX+50, smileyY+45, 5, 5);
			} else {
				g.fillRect(smileyX+20, smileyY+45, 30, 5);
				g.fillRect(smileyX+15, smileyY+50, 5, 5);
				g.fillRect(smileyX+50, smileyY+50, 5, 5);
			}
			

			/**drapeau 
			 * 
			 */
			g.setColor(Color.BLACK);
			g.fillRect(drapeauX+32, drapeauY+15, 5, 40);
			g.fillRect(drapeauX+20, drapeauY+50, 30, 10);
			g.setColor(Color.red);
			g.fillRect(drapeauX+16,drapeauY+15,20,15);
			g.setColor(Color.BLACK);
			g.drawRect(drapeauX+16, drapeauY+15, 20, 15);
			g.drawRect(drapeauX+17, drapeauY+16, 18, 13);
			g.drawRect(drapeauX+18, drapeauY+17, 16, 11);
			if (marque == true) {
				g.setColor(Color.red);
			}
			g.drawOval(drapeauX, drapeauY, 70, 70);
			g.drawOval(drapeauX+1, drapeauY+1, 68, 68);
			g.drawOval(drapeauX+2, drapeauY+2, 66, 66);
			
			
			/**COMPTEUR DE TEMPS
			 * 
			 * 
			 */
			g.setColor(Color.black);
			g.fillRect(timeX, timeY,140, 70);
			if (defaite == false && victoire== false) {
			sec = (int) ((new Date().getTime()- TempsDebutJeu.getTime())/1000);
			}
			if (sec>999) {
				sec=999;
			}
			g.setColor(Color.white);
			if (victoire==true) {
				g.setColor(Color.green);
			}
			else if(defaite==true) {g.setColor(Color.red);
					}
			
			g.setFont(new Font("Tahoma", Font.PLAIN,80));
			if(sec<10) {
				g.drawString("00"+Integer.toString(sec),timeX,timeY+65);
			}
			else if (sec<100)
			{
				g.drawString("0"+Integer.toString(sec),timeX,timeY+65);
			}
			else {
			g.drawString(Integer.toString(sec),timeX,timeY+65);
			}
			// message de la victoire
			if (victoire==true) {
				g.setColor(Color.GREEN);
				messageDeLaVictoire="Gagné";
			} else if (defaite==true) {
				g.setColor(Color.red);
				messageDeLaVictoire="Perdu";
			}
			if(victoire== true || defaite ==true) {
				MessageVictoireY=(int) (-50+(new Date().getTime()-tempsFinJeu.getTime()))/10;
				if(MessageVictoireY>67) {
					MessageVictoireY=67;
				}
				g.setFont(new Font("Tahoma",Font.PLAIN,70));
				g.drawString(messageDeLaVictoire, MessageVictoireX, MessageVictoireY);
			}
		}
	}


	
	
	public class Bouger implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {


		}

		@Override
		public void mouseMoved(MouseEvent e) {

		
			
			/*System.out.println("Votre souris a bougé");
			System.out.println("X: " + mx+",Y: "+ my);*/

		}
	}


	
	
	
	public class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			/**
			 * Si on click la souris et qu'elle est dans la boite, on la devoile.
			 * 
			 */
			//if (DanslaboiteX()!=-1 && DanslaboiteY()!=-1) {
			//	case_devoile[DanslaboiteX()][DanslaboiteY()]=true;}
			
			mx = e.getX();
			my = e.getY();
			
			
			
			if(mx >= moinsX+20 && mx<moinsX+60 && my >= minusY+20 && my<minusY+60)
			{
				espace --;
				if(espace<1) {
					espace=1;
				}
			} else if(mx >= plusX+20 && mx<plusX+60 && my >= plusY+20 && my<plusY+60) {
				
				espace ++;
				if(espace>15) {
					espace=15;
				}
				
			}
					
			if(DanslaboiteX()!=-1 && DanslaboiteY()!=-1) {
			


				System.out.println("la souris est dans le [" +DanslaboiteX()+ ","+DanslaboiteY()+"],Nombrede mines voisine:" 
						+ case_voisine[DanslaboiteX()][DanslaboiteY()]);
				
				 if (marque== true && case_devoile[DanslaboiteX()][DanslaboiteY()]==false) 
				 	{
					 
					 if(drapeau[DanslaboiteX()][DanslaboiteY()]==false) {
						 drapeau[DanslaboiteX()][DanslaboiteY()]=true; 
						 
					 }else {
						 drapeau[DanslaboiteX()][DanslaboiteY()]=false;
					 }
					 	
				 	} else {
				 		
				 		if(drapeau[DanslaboiteX()][DanslaboiteY()]==false) {
				 		
				 		case_devoile[DanslaboiteX()][DanslaboiteY()]=true;
				 		}
				 	}
				
			 }
			
			
			
			
			else {System.out.println("the pointer is not inside the box");
			 }
		
			
			 if (DansLeSmiley()==true) {
			RedemarrerLaPartie();
			System.out.println("Dans le smile= true!");
			 }
			 if (DansLedrapeau()==true) 
			 {
				 if (marque==false) {
					 marque= true;
					 System.out.println("Dans la drapeau = true");
				 }else {
					 marque= false;
					 System.out.println("Dans la drapeau = false");
				 }		
			 
			 
		}
		
		}
		
		
		
		
		

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}		
		
	}
	
	public void EtatduJeu() {
		if(defaite==false) {
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
			if(case_devoile[i][j]==true && mines[i][j]==1) {
				defaite = true;
				Joyeux = false;
				tempsFinJeu= new Date();
			
					}
				}
			}
		}
	
		if(TotalBoitesDevoiles() >=144-TotalDesMines()&& victoire==false) {
			
			victoire= true;
			System.out.println("la victoire ");
			tempsFinJeu=new Date();
		}
	}
	
	public int TotalDesMines() {
		int total =0;
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
			if(mines[i][j]==1) {
				total++;
			}
				
			}
		}
		return total;
	}
	
	
	public int TotalBoitesDevoiles() {
		int total = 0;
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
			if(case_devoile[i][j]==true) {
				total++;
					}
			
				}
			}	
		
		return total;
	}
	public void RedemarrerLaPartie(){
		resetter=true;
		marque=false;
		TempsDebutJeu= new Date();
		 MessageVictoireY= -50;
		Joyeux=true;
		victoire = false;
		defaite=false;
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				if (rand.nextInt(100)<20) {
					mines[i][j] =1;

				}else {
					mines[i][j]=0;

				}
				// si on rajoute true à cet endroit on pourra dévoiler toutes les cases 
				case_devoile[i][j]=false;
				drapeau[i][j]=false;
				
			}
		}
		
		
	}
	
	
	
	public boolean DansLeSmiley() {
		int different=(int) Math.sqrt(Math.abs(mx-smileyCentreX)*Math.abs(mx-smileyCentreX)+(my-smileyCentreY)*Math.abs(my-smileyCentreY));
		if(different< 35) {
			return true;
		} else {return false;}
		
	}
	

	public boolean DansLedrapeau() {
		int different=(int) Math.sqrt(Math.abs(mx-drapeauCentreX)*Math.abs(mx-drapeauCentreX)+(my-drapeauCentreY)*Math.abs(my-drapeauCentreY));
		if(different< 35) {
			return true;
		} else {return false;}
		
	}
	
	
	
	/** elle permet des savoir si la souris est dans la boite en donnant des précisions sur la valeur prise par x et y
	 * l'attribut mx a été déclaré public, donc la fonction n'a pas besoin de parametre on peut
	 * directement l'utiliser dans notre fonction.Si la souris est dans la boite ou non
	 */
	public int DanslaboiteX() {
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
					return i;
				}
			}
		}
		return -1;
	}
	public int DanslaboiteY() {
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
					return j;
				}
			}
		}
		return -1;
	}
	/***
	 *Avec cette fonction, on veut vérifier si les deux fonctions sont côte à côte
	 *
	 * 
	 * */
	public boolean EstVoisin(int mX,int mY, int cX, int cY) {
		if ((mX-cX<2) &&( mX - cX > -2) && (mY - cY<2) && (mY - cY>-2)&&mines[cX][cY]==1) {
			return true;
		}
		return false;
	}

}


