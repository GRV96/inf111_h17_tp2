import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe contenant l'intelligence du divinateur
 * @author Guyllaume Rousseau et Dino Kuy
 * @version H2017
 */
public class BdQuestionsReponses implements Serializable{
	
	//Nombre maximal de réponses enregistrées
	private final int MAX_REPONSES = 1000;
	
	//Chaînes utilisées dans la méthode erreurUtilisateur
	private final String EXISTE_DEJA =
			"existe déjà dans notre banque de données.";
	private final String DONNE_IND_ATTENDU =
			"Vous auriez dû répondre";
	private final String DONNE_QSTN_ERREUR = "à la question";
	private final String TIRETS = "----";
	private final String DONNE_IND_ERREUR =
			"mais vous avez répondu";
	private final String ESPACE = " ";
	private final String POINT = ".";
	private final String LIGNE = "\n";
	
	//Objet conservant l'état d'une partie
	private InfoJeu etatJeu;
	
	//Liste d'indices donnés par le joueur
	private Liste indicesDonnes;
	
	//Tableau de questions
	private ArrayList<String> questions;
	
	//Tableau de réponses
	private Reponse[] reponses;
	
	//Nombre de réponses dans le tableau
	private int nbReponses;
	
	/**
	 * Sous-programme qui initialise les attributs de BdQuestionsReponses
	 * 
	 * @param questions
	 * @param reponses
	 * @param nbReponses
	 */
	public static void initialiserBd(ArrayList<String> questions, 
			Reponse[] reponses, int nbReponses,
			InfoJeu etatJeu, Liste indicesDonnes){
		
		//Initialisation d'InfoJeu
		etatJeu = new InfoJeu(null, null, null, false);
		
		//Initialisation des indices données
		indicesDonnes = new Liste();
		
		questions = null;
		
		reponses = null;
		
		nbReponses = 0;
	}
	
	/**
	 * Sous-programme qui initialise les attributs de
	 * BdQuestionsReponses.
	 * 
	 * @param questions
	 * @param reponses
	 * @param nbReponses
	 */
	public static void InitialiseTableau(ArrayList<String> questions,
			Reponse[] reponses, int nbReponses){
		
		questions = null;
		reponses = null;
		nbReponses = 0;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public BdQuestionsReponses(){
		
		etatJeu = new InfoJeu();
		indicesDonnes = new Liste();
		questions = new ArrayList<String>();
		reponses = new Reponse[MAX_REPONSES];
		nbReponses = 0;
	}
	
	/**
	 * Constructeur par copie d'attributs
	 * @param questions
	 * @param reponses
	 * @param nbReponses
	 */
	public BdQuestionsReponses(InfoJeu etatJeu, Liste indicesDonnes,
			ArrayList<String> questions, Reponse[] reponses, int nbReponses){
		
		this.etatJeu = etatJeu;
		
		this.indicesDonnes = indicesDonnes;
		
		this.questions = questions;
		
		this.reponses = reponses;
		
		this.nbReponses = nbReponses;
	}
		
	/**
	 * Constructeur par copie d'objet	
	 * @param BdQuestionsReponses
	 */
	public BdQuestionsReponses(BdQuestionsReponses BdQuestionsReponses){
		
		etatJeu = BdQuestionsReponses.etatJeu;
		
		indicesDonnes = BdQuestionsReponses.indicesDonnes;
		
		questions = BdQuestionsReponses.questions;
		
		reponses = BdQuestionsReponses.reponses;
		
		nbReponses = BdQuestionsReponses.nbReponses;
	}
	
	/**
	 * Accesseur du tableau de questions
	 * @return questions
	 */
	public 	ArrayList<String> getQuestions(){
		
		return questions;
	}
	
	/**
	 * Mutateur du tableau de questions
	 * @param questions
	 */
	public void setQuestions(ArrayList<String> questions){
		
		this.questions = questions;
	}
	
	public int getPstQuestion(String question){
		
		//Itérateur
		int i = 0;
		
		//Retient si la question est trouvée.
		boolean trouvee = false;
		
		//Parcourt la collection de questions jusqu'à
		//ce que la question soit repérée.
		while(i < questions.size() && !trouvee){
			
			//Si la question est trouvée.
			if(question == questions.get(i)){
				
				//L'information est retenue.
				trouvee = true;
			}
			
			//Incrémentation de l'itérateur
			i++;
		}
		
		//La valeur de l'itérateur correspond
		//à la position de la question cherchée.
		return i;
	}
	
	/**
	 * Accesseur du tableau de réponses
	 * @return reponses
	 */
	public 	Reponse[] getReponses(){
		
		return reponses;
	}
	
	/**
	 * Mutateur du tableau de réponses
	 * @param reponses
	 */
	public void setReponses(Reponse[] reponses){
		
		this.reponses = reponses;
	}	
	
	/**
	 * Accesseur du nombre de réponses
	 * @return nbReponses
	 */
	public 	int getNbReponses(){
		
		//Itérateur
		int i = 0;
		
		//Retient s'il reste des réponses
		//dans le tableau de réponses.
		boolean resteReponses = true;
		
		//Parcourt le tableau de réponses jusqu'au bout
		//ou jusqu'à ce qu'il ne reste plus de réponses.
		while(i < MAX_REPONSES && resteReponses){
			
			//Si une case du tablea ne contient rien,
			//il ne reste plus de réponses.
			if(reponses[i] == null){
				
				resteReponses = false;
			}
			
			//Sinon, incrémentation de l'itérateur
			else{
				
				i++;
			}
		}
		
		//Puisque les indices des tableaux commencent à zéro,
		//le nombre de réponse est égal au dernier indice plus un.
		nbReponses = i + 1;
		
		//Le nombre de réponses est actualisé et retourné.
		return nbReponses;
	}
	
	/**
	 * Mutateur du nombre de réponses
	 * @param nbReponses
	 */
	public void setNbReponses(int nbReponses){
		
		this.nbReponses = nbReponses;
	}
	
	/**
	 * Accesseur de l'indice d'une réponse du tableau
	 * @param reponse
	 * @return indice de la réponse
	 */
	public int getIndiceReponse(String reponse){
		
		//Itérateur
		int i = 0;
				
		//Retient si la réponse a été retrouvée dans le tableau.
		boolean reponseReperee = false;
		
		//Parcourt les réponses du tableau
		//jusqu'à ce que la réponse soit repérée.
		while(i < getNbReponses() && !reponseReperee){
			
			//Détermine si la réponse est trouvée.
			if(reponse.toLowerCase() == reponses[i].getNomReponse()){
				
				reponseReperee = true;
			}
			
			//Sinon, incrémentation de l'itérateur
			else{
				
				i++;
			}
		}
		
		return i;
	}
	
	/**
	 * Accède à l'objet correspondant à une réponse du divinateur
	 * @param reponse
	 * @return la réponse en tant qu'objet
	 */
	public Reponse getObjetReponse(String reponse){
		
		return reponses[getIndiceReponse(reponse)];
	}
	
	/**
	 * Accesseur de l'état du jeu
	 * @return etatJeu
	 */
	public InfoJeu getEtatJeu(){
		
		return etatJeu;
	}
	
	/**
	 * Mutateur de l'état du jeu
	 * @param etatJeu
	 */
	public void setEtatJeu(InfoJeu etatJeu){
		
		this.etatJeu = etatJeu;
	}
	
	/**
	 * Accesseur de la liste des indices donnés
	 * @return indicesDonnes
	 */
	public Liste getIndicesDonnes(){
		
		return indicesDonnes;
	}
	
	/**
	 * Mutateur de la liste d'indices donnés
	 * @param indicesDonnes
	 */
	public void setIndicesDonnes(Liste indicesDonnes){
		
		this.indicesDonnes = indicesDonnes;
	}

	/**
	* Ceci est un procedure qui permet choisir la 
	* premiere questions qui est mise dans la bd.
	*/
	public void choisirPremiereQuestion() {
		
		//Le noeud actuel est le premier noeud du jeu.
		etatJeu.setNoeudActuel(etatJeu.getPremierNoeud());
		
		//La dernière réponse du joueur est négative.
		etatJeu.setIndiceEstPositif(false);
		
		//Il n'existe pas encore de noeud précédent.
		etatJeu.setNoeudPrecedent(null);
		
		//La liste d'indices donnés est vide.
		Liste indicesDonnes = new Liste();	
	}
	
	/**
	 * Détecte qu'une réponse est trouvée si les noeuds
	 * à gauche et à droite sont inexistants.
	 * 
	 * @return true si une réponse est trouvée sinon false
	 */
	public boolean reponseTrouvee() {
		
		return etatJeu.getNoeudActuel().getNoeudGauche() == null
				&& etatJeu.getNoeudActuel().getNoeudDroite() == null;
	}

	/**
	 * Retourne la chaine associée au noeud actuel.
	 * @return la chaîne actuelle
	 */
	public String getLaChaineActuelle() {
		
		//Chaîne à retourner
		String chaine = "Chaîne absente";
		
		//Indice de la chaîne à retourner
		int i = etatJeu.getNoeudActuel().getIndice();
		
		//Si le noeud actuel est une réponse
		if(reponseTrouvee()){
			
			//Obtient la réponse dans le tableau de réponses.
			chaine = reponses[i].getNomReponse();
		}
		
		//Sinon, le noeud actuel est une question.
		else {
			
			//Obitent la question dans la collection de questions.
		}
		
		return chaine;
	}
	
	/**
	 * Détermine si la réponse reçue existe déjà dans la BD.
	 * @param reponse
	 * @return true si la réponse existe déja
	 */
	public boolean reponseExiste(String reponse){
		
		//Itérateur
		int i=0;
		
		//Retient si une réponse existe
		boolean reponseExiste = false;
		
		//Parcourt le tableau de réponses jusqu'à
		//la découverte d'une réponse.
		while(i<nbReponses && !reponseExiste){
			
			//Détermine si une réponse est trouvée.
			if(reponse == getReponses()[i].getNomReponse()){
				
				reponseExiste = true;
			}
		}
		
		return reponseExiste;
	}
	
	/**
	 * Procédure servant à recevoir deux chaine.
	 * Elle permet d'ajouter la question dans sa collection et
	 * d'ajouter la réponse dans sa collection. Elle
	 * permet également d'ajuster les noeuds de l'arbre et la liste
	 * des indices données si cela peut s'appliquer.
	 * @return 
	 */
	public void ajouterReponseEtQuestion(String question, String reponse){
		
		//Nom de fichier sécurisé
		String bdSecurise = null;
		
		//Position de la question
		int pstQuestion;
		
		//Sauvegarde de la bd dans un fichier de sécurité
		UtilitaireFichier.sauvegarde
				(UtilitaireFichier.obtenirBd(), bdSecurise);
		
		//Si la question existe dans la collection
		if(questions.contains(question)){
			
			pstQuestion = getPstQuestion(question);
		}
		
		//Sinon, retient la position de la nouvelle question.
		else{
			
			//La question est ajoutée dans la collection.
			questions.add(question);
			
			//Sa position est retenue.
			pstQuestion = questions.size() - 1;
		}
		
		creerNoeudEtAjusterArbre(pstQuestion, reponse);
		
		//Sauvegarde de la bd
		UtilitaireFichier.sauvegarde
				(UtilitaireFichier.obtenirBd(), Constantes.NOM_FICHIER_BD);
	}
	
	/**
	 * Procédure servant a créer les noeuds et à les lier ensemble. 
	 */
	public void creerNoeudEtAjusterArbre(int indiceQst, String reponse){
		
		//La réponse est ajoutée à la suite des autres.
		int indiceRpse = getNbReponses();
		
		//Noeud contenant l'indice de la réponse
		NoeudArbre noeudReponse = new NoeudArbre(indiceRpse, null, null);
		
		//Noeud à créer
		NoeudArbre nouveauNoeud = null;
		
		//Reçoit la copie d'une liste d'indices.
		Liste copieIndices = null;
		
		//Si la bd est vide
		if(estVide()){
			
			//Crée un noeud avec l'indice de la question.
			NoeudArbre noeudQuestion =
					new NoeudArbre(indiceQst, noeudReponse, null);
		}
		
		//Sinon 
		else{
			
			//Liste d'indices du noeud actuel
			copieIndices =
					reponses[etatJeu.getNoeudActuel().getIndice()]
							.getListeIndices();
			
			//Insersion de l'indice négatif à la fin de copieIndices
			copieIndices.insererFin(Constantes.REPONSE_NEGATIVE);
			
			//Si la dernière réponse de l'utilisateur est positive
			if(etatJeu.getIndiceEstPositif()){
				
				//Noeud avec l'indice de la question,
				//la réponse à sa gauche et le noeud actuel à droite
				nouveauNoeud = new NoeudArbre(indiceQst,
						noeudReponse, etatJeu.getNoeudActuel());
				
				//Le nouveau noeud est à gauche du noeud précédent.
				etatJeu.getNoeudPrecedent().setNoeudGauche(nouveauNoeud);
				
				//La réponse du noeud actuel obtient la copie des indices.
				reponses[etatJeu.getNoeudActuel().getIndice()]
					.setListeIndices(copieIndices);
			}
		
			//Sinon si la réponse est négative et
			//si le noeud acutel contient une question
			else if(!etatJeu.getIndiceEstPositif()
					&& questions.contains(etatJeu.getNoeudActuel().getIndice())){
			
				//Le nouveau noeud est à droite du noeud actuel.
				etatJeu.getNoeudActuel().setNoeudDroite(nouveauNoeud);
			}
			
			//Sinon
			else{
			
				//Deuxième nouveau noeud contenant l'indice de la réponse
				//avec le premier nouveau noeud à gauche
				//et le noeud actuel à droite
				NoeudArbre nouveau2 = new NoeudArbre(indiceRpse,
						nouveauNoeud, etatJeu.getNoeudActuel());
				
				//Le deuxième nouveau noeud est à droite du noeud précédent.
				etatJeu.getNoeudPrecedent().setNoeudDroite(nouveau2);
				
				//La copie de la liste d'indices devient
				//la liste du noeud actuel.
				reponses[etatJeu.getNoeudActuel().getIndice()]
						.setListeIndices(copieIndices);
			}
		}
		
		//Ajouter un 'O' à la liste d'indices originale
		copieIndices.insererFin(Constantes.REPONSE_POSITIVE);
		
		//Cette liste devient celle de la réponse ajoutée.
		reponses[indiceRpse].setListeIndices(copieIndices);
	}
	
	/**
	 * Booléen servant a dire que la BD est vide.
	 * 
	 * @return true s'il n'y a aucune question dans la collection
	 * de questions ni de réponses dans le tableau de réponses.
	 */
	public boolean estVide() {
		
		return questions == null && reponses == null;
	}
	
	/**
	 * Fonction qui se déplace dans l'arbre de connaissance
	 * @param reponse réponse provenant d'une boîte de dialogue
 	 * @return resteQuestions
	 */
	public boolean deplacerDansArbre(int reponse) {
	//public boolean seDeplacerDansArbre(char indice){
		
		//True s'il reste des questions à poser.
		//Sinon, false
		boolean resteQuestions = true;
		
		//Si le joueur répond oui
		if(reponse == JOptionPane.OK_OPTION){
			
			//Le noeud actuel devient le noeud précédent.
			etatJeu.setNoeudPrecedent(etatJeu.getNoeudActuel());
			
			//Le noeud à gauche du noeud actuel devient le noeud actuel.
			etatJeu.setNoeudActuel(etatJeu.getNoeudActuel().
					getNoeudGauche());
			
			//Retient que la dernière réponse est positive.
			etatJeu.setIndiceEstPositif(true);
		}
		
		//Si le joueur répond non et s'il y a un noeud à droite
		else if(reponse == JOptionPane.NO_OPTION){
			
			//Le noeud actuel devient le noeud précédent.
			etatJeu.setNoeudPrecedent(etatJeu.getNoeudActuel());
			
			//Le noeud à droite du noeud actuel devient le noeud actuel.
			etatJeu.setNoeudActuel(etatJeu.getNoeudActuel().
					getNoeudDroite());
			
			//Retient que la dernière réponse est négative.
			etatJeu.setIndiceEstPositif(false);
		}
		
		//Dans les autres cas, pas de déplacement
		else{
			
			//Retient que la dernière réponse est négative.
			etatJeu.setIndiceEstPositif(false);
			
			//Il ne reste plus de questions à poser.
			resteQuestions = false;
		}
		
		return resteQuestions;
	}
	
	/**
	 * Donne la première erreur commise par le joueur 
	 * @param reponse
	 * @return description de l'erreur
	 */
	public String erreurUtilisateur(String reponse){
		
		//Objet dans le tableau de réponses correspondant
		//à la réponse reçue en paramètre
		Reponse reponseObjet = getObjetReponse(reponse);
		
		//Position au début de la liste d'indices donnés
		indicesDonnes.setPstDebut();
		
		//Copie de la liste d'indices donnés
		Liste copieIndicesDonnes = indicesDonnes;
		
		//Retient les indices attendus pour trouver la réponse.
		Liste indicesAttendus = reponseObjet.getListeIndices();
		
		//Position au début de la liste d'indices attendus
		indicesAttendus.setPstDebut();
		
		//Le premier noeud de l'arbre est conservé dans une
		//variable temporaire en prévision du parcours de l'arbre.
		NoeudArbre noeudTemporaire = etatJeu.getPremierNoeud();
		
		//Retient que les indices comparés sont pareils.
		boolean indicesIdentiques = true;
		
		//Contiendra des indices pendant le parcours de la liste
		//des indices provenant de la réponse.
		//Initialisé à une valeur arbitraire
		char indiceAttenduActuel = 'X';
		
		//Contiendra des indices pendant le parcours de la liste 
		//des indices donnés par le joueur.
		//Initialisé à une valeur arbitraire
		char indiceDonneActuel = 'X';
		
		//Tant que les indices comparés sont pareils
		while(indicesIdentiques){
			
			//Retient l'indice attendu auquel le programme est rendu.
			indiceAttenduActuel = (Character)
					indicesAttendus.getElementActuel();
			
			//Retient l'indice donné auquel le programme est rendu.
			indiceDonneActuel = (Character)
					copieIndicesDonnes.getElementActuel();
			
			//Si les indices comparés sont différents,
			//l'information est retenue pour arrêter la boucle.
			if(indiceAttenduActuel != indiceDonneActuel){
				
				indicesIdentiques = false;
			}
			
			//Si les indices sont identiques
			else{
				
				//Passe à la position suivante
				//dans la liste d'indices donnés.
				copieIndicesDonnes.setPstSuivant();
				
				//Passe à la position suivante
				//dans la liste d'indices attendus.
				indicesAttendus.setPstSuivant();
				
				//Si les indices positifs, passe au noeud à gauche.
				if(indiceDonneActuel == Constantes.REPONSE_POSITIVE){

					noeudTemporaire = noeudTemporaire.getNoeudGauche();
				}
			
				//Si les indices sont négatifs, passe au noeud à droite.
				else{

					noeudTemporaire = noeudTemporaire.getNoeudDroite();
				}
			}
		}
		
		//Retiendra comme "oui" ou "non" l'indice incorrect.
		String indiceFautif;
		
		//L'indice incorrect reçoit sa valeur
		if(indiceDonneActuel == Constantes.REPONSE_POSITIVE){
			
			indiceFautif = "oui";
		}
		
		else{
			
			indiceFautif = "non";
		}
		
		//L'indice attendu correspondant est retnu comme un String.
		String indiceAttendu = Character.toString(indiceAttenduActuel);
		
		//La question à laquelle le joueur a mal répondu
		String questionFautive = questions.get(noeudTemporaire.getIndice());
		
		return reponse + ESPACE + EXISTE_DEJA + LIGNE + DONNE_IND_ATTENDU
				+ ESPACE + indiceAttendu + ESPACE + DONNE_QSTN_ERREUR
				+ LIGNE + TIRETS + questionFautive + TIRETS + LIGNE
				+ DONNE_IND_ERREUR + ESPACE + indiceFautif + POINT;
	}
}