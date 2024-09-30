import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Classe impl�mentant des sous programmes utilitaires
 * qui permettent de faire de la saisie au clavier pour le jeu du divinateur.
 *
 * Toutes les fonctionnalit�s importantes sont effectu�es par la classe
 * BdQuestionsReponses.
 *
 * @author pbelisle
 * @version hiver 2017
 */

public class UtilitaireES implements Serializable{

	static Scanner clavier = new Scanner(System.in);

	/**********************************
	 * AFFICHER PRESENTATION DU JEU
	 *
	 * Affiche un bo�te de message qui explique le jeu.
	 *
     **********************************/
	public static void afficherPresentationJeu(){


		String str = "***************************************\n" +
		                   "***********JEU DU DIVINATEURS*******\n" +
				           "***************************************\n" +
				           "***************************************\n" +
		                   "Il s'agit de penser � un animal, un objet ou un \n" +
				           "personnage et nous tentons de le trouver \n" +
		                   "en posant des questions auxquelles \n" +
				           "vous devrez r�pondre par  oui ou par non.\n\n\n" +
		                   "Si nous ne trouvons pas, vous pourrez nous dire ce � \n" +
				           "quoi vous pensiez et ajouter une question qui distingue\n" +
		                   "votre r�ponse des autres.\n\n\n" +
				           "Des mauvaises questions peuvent d�ranger" +
				           " le bon d�roulement du jeu." +
		                   "\n" +
		                   "************************************";

		JOptionPane.showMessageDialog(null, str);
	}





	/***************************
	 * DEMARRER DIVINATEUR
	 *************************
	 *Permet l'interaction avec l'utilisateur en
	 *posant les questions provenant de la base de donn�es des r�ponses et
	 *en agissant selon lles indices donn�s par l'utilisateur.
	 */
	public static void demarrerDivinateur(BdQuestionsReponses bd){

		// Sert a retenir s'il reste des question.
		boolean resteQuestion = true;

		// Sert � saisir la r�ponse de l'utilisateur.
		int reponse = JOptionPane.OK_OPTION;
		
		// Choisir une premi�re question dans la bd.
		bd.choisirPremiereQuestion();

		// Tant  qu'on a pas trouv� la r�ponse et qu'il reste des questions et
		// que l'utilisateur n'appuie pas sur X.
		while(reponse != JOptionPane.CLOSED_OPTION &&
				!bd.reponseTrouvee() &&
				resteQuestion){

			  String [] options =  {"Oui", "Non"};

			  String str = bd.getLaChaineActuelle();

			  // On pose la question courante dans l'arbre de connaissance de la bd.
			  reponse  = JOptionPane.showOptionDialog(null,
					   str +

					  // On met un ? s'il n'y en a pas
					 ((str.charAt(str.length() -1) == '?')? " " : "?"),
					  "Jeu du divinateur",
					  JOptionPane.CANCEL_OPTION,
					  JOptionPane.QUESTION_MESSAGE,
					  null,
					  options,
					  "Oui");

			  if(reponse != JOptionPane.CLOSED_OPTION){

				  // On se prom�ne dans l'arbre de connaissances.
					resteQuestion = bd.deplacerDansArbre(reponse);
			  }
		}

		// Si on est sorti de la boucle pr�c�dente c'est qu'il ne reste plus de
		// question ou qu'on a trouv�.  Donc s'il reste des questions c'est
		// qu'on a trouv�
		if (resteQuestion && reponse != JOptionPane.CLOSED_OPTION) {

						reponse = JOptionPane.showConfirmDialog(null,
					"La r�ponse est " + bd.getLaChaineActuelle() + "; Est-ce exact ?");

						// Si l'utilisateur n'annule pas.
						if(reponse != JOptionPane.CANCEL_OPTION &&
							reponse != JOptionPane.CLOSED_OPTION){

							// Si c'est oui, on a trouv�, bravo!
							if (reponse == 0){

								JOptionPane.showMessageDialog(null,
										"Bravo nous avons trouve votre r�ponse");
							}

							// Autrement, on demande quel est sa r�ponse.
							else{

								demanderReponseValide(bd);
							}

						}
		}

		// Il ne reste plus de questions alors si l'utilisateur n'annule pas
		else if(reponse != JOptionPane.CANCEL_OPTION &&
				reponse != JOptionPane.CLOSED_OPTION){

			// On demande quel est sa r�ponse.
			demanderReponseValide(bd);
		}
	}
	
	/**
	 * Ajoute une r�ponse dans la base de donn�es du divinateur.
	 * @param bd base de donn�es contenant questions et r�ponses
	 */
	public static void demanderReponseValide(BdQuestionsReponses bd) {
		
		//Messages des bo�tes de dialogue en constantes
		final String TITRE_DEMANDE_REPONSE = "Entr�e";
		final String DEMANDE_ENTREE = " Entrez ce � quoi vous pensiez:";
		final String AVEU_IGNORANCE =
				"Je ne connais rien.";
		final String AVEU_PAS_TROUVEE = "Je n'ai pas trouv� votre r�ponse.";
		final String DEMANDE_QUESTION = "Entrez une question concernant"
				+ " votre objet ou votre animal qui le distingue:";
		
		//Cha�ne vide inacceptable
		final String CHAINE_VIDE = "";
		
		//Cha�ne qui recevra la r�ponse
		String reponse;
		
		//Cha�ne qui recevra la question
		String question = CHAINE_VIDE;
		
		//Nombre de r�ponses dans la base de donn�es
		int nbReponses = bd.getNbReponses();
		
		//Si la BD est vide, avoue ignorance et demande r�ponse.
		if(bd.estVide()){
			
			//La r�ponse est retenue en minuscules.
			reponse = JOptionPane.showInputDialog(null,
					AVEU_IGNORANCE + DEMANDE_ENTREE,
					TITRE_DEMANDE_REPONSE,
					JOptionPane.QUESTION_MESSAGE).toLowerCase();
		}
		
		//Si la BD n'est pas vide, avoue ne pas trouver
		//la r�ponse et la demande.
		else{
			
			//La r�ponse est retenue en minuscules.
			reponse = JOptionPane.showInputDialog(null,
					AVEU_PAS_TROUVEE + DEMANDE_ENTREE,
					TITRE_DEMANDE_REPONSE,
					JOptionPane.QUESTION_MESSAGE).toLowerCase();
		}
		
		//Si la r�ponse existe d�j�
		if(bd.reponseExiste(reponse)){
			
			//Apr�s mercredi
			JOptionPane.showMessageDialog(null, bd.erreurUtilisateur(reponse),
					"Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//Si la BD n'est pas vide et si la r�ponse n'existe pas,
		//demande une question menant � la r�ponse.
		else if(!bd.estVide() && !bd.reponseExiste(reponse)){
			
			do {
				
				question = JOptionPane.showInputDialog(null, DEMANDE_QUESTION,
						TITRE_DEMANDE_REPONSE, JOptionPane.QUESTION_MESSAGE);
				
			} while(question == CHAINE_VIDE);
		}
	}
}