import java.io.Serializable;

/**
 * Enregistrement d'informations utiles au parcours
 * de l'arbre de connaissance du divinateur
 * @author Guyllaume Rousseau
 * @version H2017
 */
public class InfoJeu implements Serializable{
	
	//Premier noeud de l'arbre
	private NoeudArbre premierNoeud;
	
	//Noeud auquel le jeu est rendu
	private NoeudArbre noeudActuel;
	
	//Noeud qui a précédé le noeud actuel
	private NoeudArbre noeudPrecedent;
	
	//Retient si le dernier indice donné est positif
	private boolean indiceEstPositif;
	
	/**
	 * Constructeur par défaut
	 */
	public InfoJeu(){
		
		premierNoeud = null;
		noeudActuel = null;
		noeudPrecedent = null;
		indiceEstPositif = false;
	}
	
	/**
	 * Constructeur par copie d'attributs
	 * @param premierNoeud
	 * @param noeudActuel
	 * @param noeudPrecedent
	 * @param indicePositif
	 */
	public InfoJeu(NoeudArbre premierNoeud, NoeudArbre noeudActuel,
			NoeudArbre noeudPrecedent, boolean indicePositif){
		
		this.premierNoeud = premierNoeud;
		this.noeudActuel = noeudActuel;
		this.noeudPrecedent = noeudPrecedent;
		this.indiceEstPositif = indicePositif;
	}
	
	/**
	 * Accesseur du premier noeud
	 * @return le premier noeud de l'arbre
	 */
	public NoeudArbre getPremierNoeud(){
		
		return premierNoeud;
	}
	
	/**
	 * Mutateur du premier noeud
	 * @param premierNoeud nouveau premier noeud de l'arbre
	 */
	public void setPremierNoeud(NoeudArbre premierNoeud){
		
		this.premierNoeud = premierNoeud;
	}
	
	/**
	 * Accesseur du noeud actuel
	 * @return noeudActuel le noeud auquel le jeu est rendu
	 */
	public NoeudArbre getNoeudActuel(){
		
		return noeudActuel;
	}
	
	/**
	 * Mutateur du noeud actuel
	 * @param noeudActuel nouveau noed actuel
	 */
	public void setNoeudActuel(NoeudArbre noeudActuel){
		
		this.noeudActuel = noeudActuel;
	}
	
	/**
	 * Accesseur du noeud précédent
	 * @return noeudPrecedent
	 */
	public NoeudArbre getNoeudPrecedent(){
		
		return noeudPrecedent;
	}
	
	/**
	 * Mutateur du noeud précédent
	 * @param noeudPrecedent nouveau noeud précédent
	 */
	public void setNoeudPrecedent(NoeudArbre noeudPrecedent){
		
		this.noeudPrecedent = noeudPrecedent;
	}
	
	/**
	 * Accesseur de la variable de valeur d'indice
	 * @return indiceEstPositif
	 */
	public boolean getIndiceEstPositif(){
		
		return indiceEstPositif;
	}
	
	/**
	 * Mutateur de la variable de valeur d'indice
	 * @param indiceEstPositif nouvelle valeur de l'indice
	 */
	public void setIndiceEstPositif(boolean indiceEstPositif){
		
		this.indiceEstPositif = indiceEstPositif;
	}
}
