import java.io.Serializable;

/**
 * Enregistrement définissant les réponses du divinateur
 * @author Guyllaume Rousseau
 * @version H2017
 */
public class Reponse implements Serializable{
	
	//Mot qui constitue la réponse
	private String nomReponse;
	
	//Listes d'indices menant à la réponse
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
	 * Accesseur du nom de la réponse
	 * @return nomReponse
	 */
	public String getNomReponse(){
		return nomReponse;
	}
	
	/**
	 * Mutateur du nom de la réponse
	 * @param nomReponse nouvelle réponse
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
