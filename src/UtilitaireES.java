import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Classe implémentant des sous programmes utilitaires
 * qui permettent de faire de la saisie au clavier pour le jeu du divinateur.
 *
 * Toutes les fonctionnalités importantes sont effectuées par la classe
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
	 * Affiche un boîte de message qui explique le jeu.
	 *
     **********************************/
	public static void afficherPresentationJeu(){


		String str = "***************************************\n" +
		                   "***********JEU DU DIVINATEURS*******\n" +
				           "***************************************\n" +
				           "***************************************\n" +
		                   "Il s'agit de penser à un animal, un objet ou un \n" +
				           "personnage et nous tentons de le trouver \n" +
		                   "en posant des questions auxquelles \n" +
				           "vous devrez répondre par  oui ou par non.\n\n\n" +
		                   "Si nous ne trouvons pas, vous pourrez nous dire ce à \n" +
				           "quoi vous pensiez et ajouter une question qui distingue\n" +
		                   "votre réponse des autres.\n\n\n" +
				           "Des mauvaises questions peuvent déranger" +
				           " le bon déroulement du jeu." +
		                   "\n" +
		                   "************************************";

		JOptionPane.showMessageDialog(null, str);
	}





	/***************************
	 * DEMARRER DIVINATEUR
	 *************************
	 *Permet l'interaction avec l'utilisateur en
	 *posant les questions provenant de la base de données des réponses et
	 *en agissant selon lles indices donnés par l'utilisateur.
	 */
	public static void demarrerDivinateur(BdQuestionsReponses bd){

		// Sert a retenir s'il reste des question.
		boolean resteQuestion = true;

		// Sert à saisir la réponse de l'utilisateur.
		int reponse = JOptionPane.OK_OPTION;
		
		// Choisir une première question dans la bd.
		bd.choisirPremiereQuestion();

		// Tant  qu'on a pas trouvé la réponse et qu'il reste des questions et
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

				  // On se promène dans l'arbre de connaissances.
					resteQuestion = bd.deplacerDansArbre(reponse);
			  }
		}

		// Si on est sorti de la boucle précédente c'est qu'il ne reste plus de
		// question ou qu'on a trouvé.  Donc s'il reste des questions c'est
		// qu'on a trouvé
		if (resteQuestion && reponse != JOptionPane.CLOSED_OPTION) {

						reponse = JOptionPane.showConfirmDialog(null,
					"La réponse est " + bd.getLaChaineActuelle() + "; Est-ce exact ?");

						// Si l'utilisateur n'annule pas.
						if(reponse != JOptionPane.CANCEL_OPTION &&
							reponse != JOptionPane.CLOSED_OPTION){

							// Si c'est oui, on a trouvé, bravo!
							if (reponse == 0){

								JOptionPane.showMessageDialog(null,
										"Bravo nous avons trouve votre réponse");
							}

							// Autrement, on demande quel est sa réponse.
							else{

								demanderReponseValide(bd);
							}

						}
		}

		// Il ne reste plus de questions alors si l'utilisateur n'annule pas
		else if(reponse != JOptionPane.CANCEL_OPTION &&
				reponse != JOptionPane.CLOSED_OPTION){

			// On demande quel est sa réponse.
			demanderReponseValide(bd);
		}
	}
	
	/**
	 * Ajoute une réponse dans la base de données du divinateur.
	 * @param bd base de données contenant questions et réponses
	 */
	public static void demanderReponseValide(BdQuestionsReponses bd) {
		
		//Messages des boîtes de dialogue en constantes
		final String TITRE_DEMANDE_REPONSE = "Entrée";
		final String DEMANDE_ENTREE = " Entrez ce à quoi vous pensiez:";
		final String AVEU_IGNORANCE =
				"Je ne connais rien.";
		final String AVEU_PAS_TROUVEE = "Je n'ai pas trouvé votre réponse.";
		final String DEMANDE_QUESTION = "Entrez une question concernant"
				+ " votre objet ou votre animal qui le distingue:";
		
		//Chaîne vide inacceptable
		final String CHAINE_VIDE = "";
		
		//Chaîne qui recevra la réponse
		String reponse;
		
		//Chaîne qui recevra la question
		String question = CHAINE_VIDE;
		
		//Nombre de réponses dans la base de données
		int nbReponses = bd.getNbReponses();
		
		//Si la BD est vide, avoue ignorance et demande réponse.
		if(bd.estVide()){
			
			//La réponse est retenue en minuscules.
			reponse = JOptionPane.showInputDialog(null,
					AVEU_IGNORANCE + DEMANDE_ENTREE,
					TITRE_DEMANDE_REPONSE,
					JOptionPane.QUESTION_MESSAGE).toLowerCase();
		}
		
		//Si la BD n'est pas vide, avoue ne pas trouver
		//la réponse et la demande.
		else{
			
			//La réponse est retenue en minuscules.
			reponse = JOptionPane.showInputDialog(null,
					AVEU_PAS_TROUVEE + DEMANDE_ENTREE,
					TITRE_DEMANDE_REPONSE,
					JOptionPane.QUESTION_MESSAGE).toLowerCase();
		}
		
		//Si la réponse existe déjà
		if(bd.reponseExiste(reponse)){
			
			//Après mercredi
			JOptionPane.showMessageDialog(null, bd.erreurUtilisateur(reponse),
					"Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//Si la BD n'est pas vide et si la réponse n'existe pas,
		//demande une question menant à la réponse.
		else if(!bd.estVide() && !bd.reponseExiste(reponse)){
			
			do {
				
				question = JOptionPane.showInputDialog(null, DEMANDE_QUESTION,
						TITRE_DEMANDE_REPONSE, JOptionPane.QUESTION_MESSAGE);
				
			} while(question == CHAINE_VIDE);
		}
	}
}