import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe contenant l'intelligence du divinateur
 * @author Guyllaume Rousseau et Dino Kuy
 * @version H2017
 */
public class BdQuestionsReponses implements Serializable{
	
	//Nombre maximal de r�ponses enregistr�es
	private final int MAX_REPONSES = 1000;
	
	//Cha�nes utilis�es dans la m�thode erreurUtilisateur
	private final String EXISTE_DEJA =
			"existe d�j� dans notre banque de donn�es.";
	private final String DONNE_IND_ATTENDU =
			"Vous auriez d� r�pondre";
	private final String DONNE_QSTN_ERREUR = "� la question";
	private final String TIRETS = "----";
	private final String DONNE_IND_ERREUR =
			"mais vous avez r�pondu";
	private final String ESPACE = " ";
	private final String POINT = ".";
	private final String LIGNE = "\n";
	
	//Objet conservant l'�tat d'une partie
	private InfoJeu etatJeu;
	
	//Liste d'indices donn�s par le joueur
	private Liste indicesDonnes;
	
	//Tableau de questions
	private ArrayList<String> questions;
	
	//Tableau de r�ponses
	private Reponse[] reponses;
	
	//Nombre de r�ponses dans le tableau
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
		
		//Initialisation des indices donn�es
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
	 * Constructeur par d�faut
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
		
		//It�rateur
		int i = 0;
		
		//Retient si la question est trouv�e.
		boolean trouvee = false;
		
		//Parcourt la collection de questions jusqu'�
		//ce que la question soit rep�r�e.
		while(i < questions.size() && !trouvee){
			
			//Si la question est trouv�e.
			if(question == questions.get(i)){
				
				//L'information est retenue.
				trouvee = true;
			}
			
			//Incr�mentation de l'it�rateur
			i++;
		}
		
		//La valeur de l'it�rateur correspond
		//� la position de la question cherch�e.
		return i;
	}
	
	/**
	 * Accesseur du tableau de r�ponses
	 * @return reponses
	 */
	public 	Reponse[] getReponses(){
		
		return reponses;
	}
	
	/**
	 * Mutateur du tableau de r�ponses
	 * @param reponses
	 */
	public void setReponses(Reponse[] reponses){
		
		this.reponses = reponses;
	}	
	
	/**
	 * Accesseur du nombre de r�ponses
	 * @return nbReponses
	 */
	public 	int getNbReponses(){
		
		//It�rateur
		int i = 0;
		
		//Retient s'il reste des r�ponses
		//dans le tableau de r�ponses.
		boolean resteReponses = true;
		
		//Parcourt le tableau de r�ponses jusqu'au bout
		//ou jusqu'� ce qu'il ne reste plus de r�ponses.
		while(i < MAX_REPONSES && resteReponses){
			
			//Si une case du tablea ne contient rien,
			//il ne reste plus de r�ponses.
			if(reponses[i] == null){
				
				resteReponses = false;
			}
			
			//Sinon, incr�mentation de l'it�rateur
			else{
				
				i++;
			}
		}
		
		//Puisque les indices des tableaux commencent � z�ro,
		//le nombre de r�ponse est �gal au dernier indice plus un.
		nbReponses = i + 1;
		
		//Le nombre de r�ponses est actualis� et retourn�.
		return nbReponses;
	}
	
	/**
	 * Mutateur du nombre de r�ponses
	 * @param nbReponses
	 */
	public void setNbReponses(int nbReponses){
		
		this.nbReponses = nbReponses;
	}
	
	/**
	 * Accesseur de l'indice d'une r�ponse du tableau
	 * @param reponse
	 * @return indice de la r�ponse
	 */
	public int getIndiceReponse(String reponse){
		
		//It�rateur
		int i = 0;
				
		//Retient si la r�ponse a �t� retrouv�e dans le tableau.
		boolean reponseReperee = false;
		
		//Parcourt les r�ponses du tableau
		//jusqu'� ce que la r�ponse soit rep�r�e.
		while(i < getNbReponses() && !reponseReperee){
			
			//D�termine si la r�ponse est trouv�e.
			if(reponse.toLowerCase() == reponses[i].getNomReponse()){
				
				reponseReperee = true;
			}
			
			//Sinon, incr�mentation de l'it�rateur
			else{
				
				i++;
			}
		}
		
		return i;
	}
	
	/**
	 * Acc�de � l'objet correspondant � une r�ponse du divinateur
	 * @param reponse
	 * @return la r�ponse en tant qu'objet
	 */
	public Reponse getObjetReponse(String reponse){
		
		return reponses[getIndiceReponse(reponse)];
	}
	
	/**
	 * Accesseur de l'�tat du jeu
	 * @return etatJeu
	 */
	public InfoJeu getEtatJeu(){
		
		return etatJeu;
	}
	
	/**
	 * Mutateur de l'�tat du jeu
	 * @param etatJeu
	 */
	public void setEtatJeu(InfoJeu etatJeu){
		
		this.etatJeu = etatJeu;
	}
	
	/**
	 * Accesseur de la liste des indices donn�s
	 * @return indicesDonnes
	 */
	public Liste getIndicesDonnes(){
		
		return indicesDonnes;
	}
	
	/**
	 * Mutateur de la liste d'indices donn�s
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
		
		//La derni�re r�ponse du joueur est n�gative.
		etatJeu.setIndiceEstPositif(false);
		
		//Il n'existe pas encore de noeud pr�c�dent.
		etatJeu.setNoeudPrecedent(null);
		
		//La liste d'indices donn�s est vide.
		Liste indicesDonnes = new Liste();	
	}
	
	/**
	 * D�tecte qu'une r�ponse est trouv�e si les noeuds
	 * � gauche et � droite sont inexistants.
	 * 
	 * @return true si une r�ponse est trouv�e sinon false
	 */
	public boolean reponseTrouvee() {
		
		return etatJeu.getNoeudActuel().getNoeudGauche() == null
				&& etatJeu.getNoeudActuel().getNoeudDroite() == null;
	}

	/**
	 * Retourne la chaine associ�e au noeud actuel.
	 * @return la cha�ne actuelle
	 */
	public String getLaChaineActuelle() {
		
		//Cha�ne � retourner
		String chaine = "Cha�ne absente";
		
		//Indice de la cha�ne � retourner
		int i = etatJeu.getNoeudActuel().getIndice();
		
		//Si le noeud actuel est une r�ponse
		if(reponseTrouvee()){
			
			//Obtient la r�ponse dans le tableau de r�ponses.
			chaine = reponses[i].getNomReponse();
		}
		
		//Sinon, le noeud actuel est une question.
		else {
			
			//Obitent la question dans la collection de questions.
		}
		
		return chaine;
	}
	
	/**
	 * D�termine si la r�ponse re�ue existe d�j� dans la BD.
	 * @param reponse
	 * @return true si la r�ponse existe d�ja
	 */
	public boolean reponseExiste(String reponse){
		
		//It�rateur
		int i=0;
		
		//Retient si une r�ponse existe
		boolean reponseExiste = false;
		
		//Parcourt le tableau de r�ponses jusqu'�
		//la d�couverte d'une r�ponse.
		while(i<nbReponses && !reponseExiste){
			
			//D�termine si une r�ponse est trouv�e.
			if(reponse == getReponses()[i].getNomReponse()){
				
				reponseExiste = true;
			}
		}
		
		return reponseExiste;
	}
	
	/**
	 * Proc�dure servant � recevoir deux chaine.
	 * Elle permet d'ajouter la question dans sa collection et
	 * d'ajouter la r�ponse dans sa collection. Elle
	 * permet �galement d'ajuster les noeuds de l'arbre et la liste
	 * des indices donn�es si cela peut s'appliquer.
	 * @return 
	 */
	public void ajouterReponseEtQuestion(String question, String reponse){
		
		//Nom de fichier s�curis�
		String bdSecurise = null;
		
		//Position de la question
		int pstQuestion;
		
		//Sauvegarde de la bd dans un fichier de s�curit�
		UtilitaireFichier.sauvegarde
				(UtilitaireFichier.obtenirBd(), bdSecurise);
		
		//Si la question existe dans la collection
		if(questions.contains(question)){
			
			pstQuestion = getPstQuestion(question);
		}
		
		//Sinon, retient la position de la nouvelle question.
		else{
			
			//La question est ajout�e dans la collection.
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
	 * Proc�dure servant a cr�er les noeuds et � les lier ensemble. 
	 */
	public void creerNoeudEtAjusterArbre(int indiceQst, String reponse){
		
		//La r�ponse est ajout�e � la suite des autres.
		int indiceRpse = getNbReponses();
		
		//Noeud contenant l'indice de la r�ponse
		NoeudArbre noeudReponse = new NoeudArbre(indiceRpse, null, null);
		
		//Noeud � cr�er
		NoeudArbre nouveauNoeud = null;
		
		//Re�oit la copie d'une liste d'indices.
		Liste copieIndices = null;
		
		//Si la bd est vide
		if(estVide()){
			
			//Cr�e un noeud avec l'indice de la question.
			NoeudArbre noeudQuestion =
					new NoeudArbre(indiceQst, noeudReponse, null);
		}
		
		//Sinon 
		else{
			
			//Liste d'indices du noeud actuel
			copieIndices =
					reponses[etatJeu.getNoeudActuel().getIndice()]
							.getListeIndices();
			
			//Insersion de l'indice n�gatif � la fin de copieIndices
			copieIndices.insererFin(Constantes.REPONSE_NEGATIVE);
			
			//Si la derni�re r�ponse de l'utilisateur est positive
			if(etatJeu.getIndiceEstPositif()){
				
				//Noeud avec l'indice de la question,
				//la r�ponse � sa gauche et le noeud actuel � droite
				nouveauNoeud = new NoeudArbre(indiceQst,
						noeudReponse, etatJeu.getNoeudActuel());
				
				//Le nouveau noeud est � gauche du noeud pr�c�dent.
				etatJeu.getNoeudPrecedent().setNoeudGauche(nouveauNoeud);
				
				//La r�ponse du noeud actuel obtient la copie des indices.
				reponses[etatJeu.getNoeudActuel().getIndice()]
					.setListeIndices(copieIndices);
			}
		
			//Sinon si la r�ponse est n�gative et
			//si le noeud acutel contient une question
			else if(!etatJeu.getIndiceEstPositif()
					&& questions.contains(etatJeu.getNoeudActuel().getIndice())){
			
				//Le nouveau noeud est � droite du noeud actuel.
				etatJeu.getNoeudActuel().setNoeudDroite(nouveauNoeud);
			}
			
			//Sinon
			else{
			
				//Deuxi�me nouveau noeud contenant l'indice de la r�ponse
				//avec le premier nouveau noeud � gauche
				//et le noeud actuel � droite
				NoeudArbre nouveau2 = new NoeudArbre(indiceRpse,
						nouveauNoeud, etatJeu.getNoeudActuel());
				
				//Le deuxi�me nouveau noeud est � droite du noeud pr�c�dent.
				etatJeu.getNoeudPrecedent().setNoeudDroite(nouveau2);
				
				//La copie de la liste d'indices devient
				//la liste du noeud actuel.
				reponses[etatJeu.getNoeudActuel().getIndice()]
						.setListeIndices(copieIndices);
			}
		}
		
		//Ajouter un 'O' � la liste d'indices originale
		copieIndices.insererFin(Constantes.REPONSE_POSITIVE);
		
		//Cette liste devient celle de la r�ponse ajout�e.
		reponses[indiceRpse].setListeIndices(copieIndices);
	}
	
	/**
	 * Bool�en servant a dire que la BD est vide.
	 * 
	 * @return true s'il n'y a aucune question dans la collection
	 * de questions ni de r�ponses dans le tableau de r�ponses.
	 */
	public boolean estVide() {
		
		return questions == null && reponses == null;
	}
	
	/**
	 * Fonction qui se d�place dans l'arbre de connaissance
	 * @param reponse r�ponse provenant d'une bo�te de dialogue
 	 * @return resteQuestions
	 */
	public boolean deplacerDansArbre(int reponse) {
	//public boolean seDeplacerDansArbre(char indice){
		
		//True s'il reste des questions � poser.
		//Sinon, false
		boolean resteQuestions = true;
		
		//Si le joueur r�pond oui
		if(reponse == JOptionPane.OK_OPTION){
			
			//Le noeud actuel devient le noeud pr�c�dent.
			etatJeu.setNoeudPrecedent(etatJeu.getNoeudActuel());
			
			//Le noeud � gauche du noeud actuel devient le noeud actuel.
			etatJeu.setNoeudActuel(etatJeu.getNoeudActuel().
					getNoeudGauche());
			
			//Retient que la derni�re r�ponse est positive.
			etatJeu.setIndiceEstPositif(true);
		}
		
		//Si le joueur r�pond non et s'il y a un noeud � droite
		else if(reponse == JOptionPane.NO_OPTION){
			
			//Le noeud actuel devient le noeud pr�c�dent.
			etatJeu.setNoeudPrecedent(etatJeu.getNoeudActuel());
			
			//Le noeud � droite du noeud actuel devient le noeud actuel.
			etatJeu.setNoeudActuel(etatJeu.getNoeudActuel().
					getNoeudDroite());
			
			//Retient que la derni�re r�ponse est n�gative.
			etatJeu.setIndiceEstPositif(false);
		}
		
		//Dans les autres cas, pas de d�placement
		else{
			
			//Retient que la derni�re r�ponse est n�gative.
			etatJeu.setIndiceEstPositif(false);
			
			//Il ne reste plus de questions � poser.
			resteQuestions = false;
		}
		
		return resteQuestions;
	}
	
	/**
	 * Donne la premi�re erreur commise par le joueur 
	 * @param reponse
	 * @return description de l'erreur
	 */
	public String erreurUtilisateur(String reponse){
		
		//Objet dans le tableau de r�ponses correspondant
		//� la r�ponse re�ue en param�tre
		Reponse reponseObjet = getObjetReponse(reponse);
		
		//Position au d�but de la liste d'indices donn�s
		indicesDonnes.setPstDebut();
		
		//Copie de la liste d'indices donn�s
		Liste copieIndicesDonnes = indicesDonnes;
		
		//Retient les indices attendus pour trouver la r�ponse.
		Liste indicesAttendus = reponseObjet.getListeIndices();
		
		//Position au d�but de la liste d'indices attendus
		indicesAttendus.setPstDebut();
		
		//Le premier noeud de l'arbre est conserv� dans une
		//variable temporaire en pr�vision du parcours de l'arbre.
		NoeudArbre noeudTemporaire = etatJeu.getPremierNoeud();
		
		//Retient que les indices compar�s sont pareils.
		boolean indicesIdentiques = true;
		
		//Contiendra des indices pendant le parcours de la liste
		//des indices provenant de la r�ponse.
		//Initialis� � une valeur arbitraire
		char indiceAttenduActuel = 'X';
		
		//Contiendra des indices pendant le parcours de la liste 
		//des indices donn�s par le joueur.
		//Initialis� � une valeur arbitraire
		char indiceDonneActuel = 'X';
		
		//Tant que les indices compar�s sont pareils
		while(indicesIdentiques){
			
			//Retient l'indice attendu auquel le programme est rendu.
			indiceAttenduActuel = (Character)
					indicesAttendus.getElementActuel();
			
			//Retient l'indice donn� auquel le programme est rendu.
			indiceDonneActuel = (Character)
					copieIndicesDonnes.getElementActuel();
			
			//Si les indices compar�s sont diff�rents,
			//l'information est retenue pour arr�ter la boucle.
			if(indiceAttenduActuel != indiceDonneActuel){
				
				indicesIdentiques = false;
			}
			
			//Si les indices sont identiques
			else{
				
				//Passe � la position suivante
				//dans la liste d'indices donn�s.
				copieIndicesDonnes.setPstSuivant();
				
				//Passe � la position suivante
				//dans la liste d'indices attendus.
				indicesAttendus.setPstSuivant();
				
				//Si les indices positifs, passe au noeud � gauche.
				if(indiceDonneActuel == Constantes.REPONSE_POSITIVE){

					noeudTemporaire = noeudTemporaire.getNoeudGauche();
				}
			
				//Si les indices sont n�gatifs, passe au noeud � droite.
				else{

					noeudTemporaire = noeudTemporaire.getNoeudDroite();
				}
			}
		}
		
		//Retiendra comme "oui" ou "non" l'indice incorrect.
		String indiceFautif;
		
		//L'indice incorrect re�oit sa valeur
		if(indiceDonneActuel == Constantes.REPONSE_POSITIVE){
			
			indiceFautif = "oui";
		}
		
		else{
			
			indiceFautif = "non";
		}
		
		//L'indice attendu correspondant est retnu comme un String.
		String indiceAttendu = Character.toString(indiceAttenduActuel);
		
		//La question � laquelle le joueur a mal r�pondu
		String questionFautive = questions.get(noeudTemporaire.getIndice());
		
		return reponse + ESPACE + EXISTE_DEJA + LIGNE + DONNE_IND_ATTENDU
				+ ESPACE + indiceAttendu + ESPACE + DONNE_QSTN_ERREUR
				+ LIGNE + TIRETS + questionFautive + TIRETS + LIGNE
				+ DONNE_IND_ERREUR + ESPACE + indiceFautif + POINT;
	}
}