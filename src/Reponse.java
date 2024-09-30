import java.io.Serializable;

/**
 * Enregistrement d�finissant les r�ponses du divinateur
 * @author Guyllaume Rousseau
 * @version H2017
 */
public class Reponse implements Serializable{
	
	//Mot qui constitue la r�ponse
	private String nomReponse;
	
	//Listes d'indices menant � la r�ponse
	private Liste indices;
	
	/**
	 * Constructeur par copie d'attributs
	 * @param nomReponse
	 * @param indices
	 */
	public Reponse(String nomReponse, Liste indices){
		this.nomReponse = nomReponse;
		this.indices = indices;
	}
	
	/**
	 * Accesseur du nom de la r�ponse
	 * @return nomReponse
	 */
	public String getNomReponse(){
		return nomReponse;
	}
	
	/**
	 * Mutateur du nom de la r�ponse
	 * @param nomReponse nouvelle r�ponse
	 */
	public void setNomReponse(String nomReponse){
		this.nomReponse = nomReponse;
	}
	
	/**
	 * Accesseur de la liste d'indices
	 * @return indices
	 */
	public Liste getListeIndices(){
		return indices;
	}
	
	/**
	 * Mutateur de la liste d'indices
	 * @param indices nouvelle liste d'indices
	 */
	public void setListeIndices(Liste indices){
		this.indices = indices;
	}
}
