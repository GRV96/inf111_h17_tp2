import java.io.Serializable;

/**
 * Classe représentant les noeuds de
 * l'arbre de connaissance du divinateur
 * @author Guyllaume Rousseau
 * @version H2017
 */
public class NoeudArbre implements Serializable{
	
	//Indice de la question ou de la réponse
	//dans le tableau de la base de données
	private int indice;
	
	//Noeud suivant à gauche si réponse positive
	private NoeudArbre noeudGauche;
	
	//Noeud suivant à droite si réponse négative
	private NoeudArbre noeudDroite;
	
	/**
	 * Constructeur par copie d'attributs
	 * @param indice
	 * @param noeudGauche
	 * @param noeudDroite
	 */
	public NoeudArbre(int indice, NoeudArbre noeudGauche,
			NoeudArbre noeudDroite){
		
		this.indice = indice;
		this.noeudGauche = noeudGauche;
		this.noeudDroite = noeudDroite;
	}
	
	/**
	 * Accesseur de l'entier
	 * @return la valeur de l'entier
	 */
	public int getIndice(){
		
		return indice;
	}
	
	/**
	 * Mutateur de l'indice
	 * @param indice nouvelle valeur de l'indice
	 */
	public void setIndice(int indice){
		
		this.indice = indice;
	}
	
	/**
	 * Accesseur du noeud à gauche
	 * @return noeud à guahce
	 */
	public NoeudArbre getNoeudGauche(){
		
		return noeudGauche;
	}
	
	/**
	 * Mutateur du noeud à gauche
	 * @param noeudGauche nouveau noeud à gauche
	 */
	public void setNoeudGauche(NoeudArbre noeudGauche){
		
		this.noeudGauche = noeudGauche;
	}
	
	/**
	 * Accesseur du noeud à droite
	 * @return noeud à droite
	 */
	public NoeudArbre getNoeudDroite(){
		
		return noeudDroite;
	}
	
	/**
	 * Mutateur du noeud à droite
	 * @param noeudDroite nouveau noeud à droite
	 */
	public void setNoeudDroite(NoeudArbre noeudDroite){
		
		this.noeudDroite = noeudDroite;
	}
}
