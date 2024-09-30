import java.io.Serializable;

/**
 * Classe repr�sentant les noeuds de
 * l'arbre de connaissance du divinateur
 * @author Guyllaume Rousseau
 * @version H2017
 */
public class NoeudArbre implements Serializable{
	
	//Indice de la question ou de la r�ponse
	//dans le tableau de la base de donn�es
	private int indice;
	
	//Noeud suivant � gauche si r�ponse positive
	private NoeudArbre noeudGauche;
	
	//Noeud suivant � droite si r�ponse n�gative
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
	 * Accesseur du noeud � gauche
	 * @return noeud � guahce
	 */
	public NoeudArbre getNoeudGauche(){
		
		return noeudGauche;
	}
	
	/**
	 * Mutateur du noeud � gauche
	 * @param noeudGauche nouveau noeud � gauche
	 */
	public void setNoeudGauche(NoeudArbre noeudGauche){
		
		this.noeudGauche = noeudGauche;
	}
	
	/**
	 * Accesseur du noeud � droite
	 * @return noeud � droite
	 */
	public NoeudArbre getNoeudDroite(){
		
		return noeudDroite;
	}
	
	/**
	 * Mutateur du noeud � droite
	 * @param noeudDroite nouveau noeud � droite
	 */
	public void setNoeudDroite(NoeudArbre noeudDroite){
		
		this.noeudDroite = noeudDroite;
	}
}
