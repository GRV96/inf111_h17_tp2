import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
* Il s'agit pour ce travail de la simulation d' un jeu d'intelligence
* artificielle . Ce jeu consiste à demander à son utilisateur de penser à
* un animal ou à un objet ou un personnage, et le programme tente de le
* trouver en lui posant des questions.  L'utilisateur ne doit  répondre que
* par :  oui, non.
*
* À noter : Le constructeur de la classe BdQuestionsReponses tente
* d'ouvrir un fichier au nom défini dans Constantes.NOM_FICHIER_BD.
* S'il existe, il contient toutes les données du jeu.  Dans le cas contraire,
* la bd est vide alors le programme principal saisit une réponse et au
* moins une question pour  l'ajouter à la bd.  C'est alors que ce fichier est
* créé et maintenu à jour à chaque nouvelle réponse qui s'ajoute dans le
* jeu.
*
* @author Pierre Bélisle
* @version janvier 2017
*/
public class DemarrerJeuDivinateur implements Serializable{

	public static void main(String[] args) {

		/*
		 * Stratégie : Une classe BdQuestionsReponse retient les questions,
		 * les réponses et le chemin pour les retrouver.  La classe JOptionPane
		 * est utilisée pour les ES ainsi que le module UtilitaireES.
		 *
		 * La boucle "principale" du jeu est définie localement, les autres sont
		 * dans l'utilitaireES.
		 */

		// Pour avoir une fenêtre en fond d'écran.
		JFrame cadre = new JFrame();
		cadre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		cadre.setVisible(true);

		// La bd qui contient les réponses, les questions
 	// et les liens entre les questions et les réponses.
		BdQuestionsReponses bd = UtilitaireFichier.obtenirBd();

		// Sert à saisir la reponse de l'utilisateur aux questions.
		int reponse = 0;

		// Lorsquel le jeu fonctionne, enlever le commentaire pour
		// dans la version officielle.
		UtilitaireES.afficherPresentationJeu();

		//  Si la bd est vide, on demande simplement un objet ou un animal et
	    // au moins une question qui le distingue.  Autrement dit, on s'assure
		// d'avoir au moins une réponse dans la bd avant de jouer.
		if (bd.estVide()) {

			UtilitaireES.demanderReponseValide(bd);

			// On demande si l'utilisateur veut jouer immédiatement.
			reponse =
					JOptionPane.showConfirmDialog(null,
							"Voulez-vous jouez maintenant ?");
		}

		// Tant que l'utilisateur veut jouer (il n'a pas annulé)
		while(reponse == JOptionPane.OK_OPTION){

			// On démarre la partie.
			UtilitaireES.demarrerDivinateur(bd);

			// On demande s'il veut rejouer.
			reponse =
					JOptionPane.showConfirmDialog(null,
							"Voulez-vous rejouez  ?");
		}

		// On sort de la boucle c'est que la partie est terminée.


		// Lorsquel le jeu fonctionne, enlever le commentaire pour
		// dans la version officielle.
		JOptionPane.showMessageDialog(null, "Merci d'avoir joué avec nous!");

		// Fermeture de la fenêtre en arrière.
		cadre.dispose();
	}

}