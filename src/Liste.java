import java.io.Serializable;

/**
 * Classe liste générale avec position actuelle implicite
 * @author Pierre Bélisle
 * @version H2017
 */
public class Liste implements Serializable{
	
	/**
	 * Noeuds de la liste
	 * @author Pierre Bélisle
	 * @version H2017
	 */
	private class Noeud implements Serializable{
		
		//Objet contenu dans le noeud
		private Object element;
		
		//Noeud qui suit le noeud
		private Noeud suivant;
		
		//Constructeur par copie d'attributs
		public Noeud(Object element, Noeud suivant){
			
			this.element = element;
			this.suivant = suivant;
		}
		
		/**
		 * Accesseur de l'élément d'un noeud
		 * @return element
		 */
		public Object getElement(){
			
			return element;
		}
		
		/**
		 * Mutateur de l'élément d'un noeud
		 * @param element
		 */
		public void setElement(Object element){
			
			this.element = element;
		}
		
		/**
		 * Accesseur du noeud suivant un noeud
		 * @return suivant
		 */
		public Noeud getSuivant(){
			return suivant;
		}
		
		/**
		 * Mutateur du noeud suivant un noeud
		 * @param suivant
		 */
		public void setSuivant(Noeud suivant){
			this.suivant = suivant;
		}
	}
		
	//Premier élément de la liste
	private Noeud debut;
	
	//Dernier élément de la liste
	private Noeud fin;
	
	//Position acutelle dans la liste
	private Noeud position;
	
	//Nombre d'éléments de la lise
	private int nbElements;
	
	/**
	 * Constructeur par défaut
	 */
	public Liste(){
		
		//La liste est vide au moment de sa création.
		debut = fin = position = null;
		nbElements = 0;
	}
	
	/**
	 * Détermine si la liste est vide.
	 * @return true si la liste est vide
	 */
	public boolean listeVide(){
		return nbElements == 0;
	}
	
	/**
	 * Accesseur de la position actuelle dans la liste
	 * @return position
	 */
	public Noeud getPosition(){
		
		return position;
	}
	
	/**
	 * Accesseur de l'élément à la positon actuelle
	 * @return élément actuel
	 */
	public Object getElementActuel(){
		
		return position.getElement();
	}
	
	/**
	 * Mutateur de l'élément de la position actuelle
	 * @param element
	 */
	public void setElementActuel(Object element){
		
		position.setElement(element);
	}
	
	/**
	 * Met la positoin au début.
	 */
	public void setPstDebut(){
		
		position = debut;
	}
	
	/**
	 * Met la position à la fin.
	 */
	public void setPstFin(){
		
		position = fin;
	}
	
	/**
	 * Passe à l'élément suivant si la position
	 * n'est pas déjà à la fin de la liste.
	 */
	public void setPstSuivant(){
		
		if(position != fin){
			
			position = position.suivant;
		}
	}
	
	/**
	 * Ramène la positon à l'élément précédent
	 * si elle n'est pas déjà au début de la liste.
	 */
	public void setPstPrecedent(){
		
		//Fonctionne si la position n'est pas au début.
		if(position != debut){
			
			//Variable retenant temporairement un noeud.
			Noeud temporaire = debut;
			
			//Parcourt les éléments jusqu'à celui avant position.
			while(temporaire.suivant != position){
				
				temporaire = temporaire.suivant;
			}
			
			//La position devient l'élément qui précède position,
			//retenu dans temporaire.
			position = temporaire;
		}
	}
	
	/**
	 * Insère  un élément au début de la liste.
	 * @param element élément à insérer
	 */
	public void insererDebut(Object element){
		
		//Le début est un nouveau noeud contenant l'élément inséré.
		//Utilisation du constructeur par copie d'attributs
		debut = new Noeud(element, debut);
		
		//Si la liste était vide au départ,
		//le début et la fin sont la même position.
		if(listeVide()){
			
			fin = debut;
		}
		
		//Par norme, la position devient celle de l'élément inséré.
		position = debut;
		
		//Incrémentation du nombre d'éléments
		nbElements ++;
	}
	
	/**
	 * Insère un élément donné à la fin de la liste.
	 * @param element element à insérer
	 */
	public void insererFin(Object element){
		
		//Si la liste est vide,
		//procède comme pour insérer au début.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, la fin est au nouveau noeud
			//contenant l'élément à insérer.
			fin.suivant = new Noeud(element, null);
			
			//La fin devient l'élément après l'ancienne fin.
			fin = fin.suivant;
			
			//Par norme, la position devient celle de l'élément inséré.
			position = fin;
			
			//Incrémentation du nombre d'éléments
			nbElements ++;
		}
	}
	
	/**
	 * Insère un element après la position actuelle.
	 * @param element élément à insérer
	 */
	public void insererApres(Object element){
		
		//Si la liste est vide,
		//procède comme pour insérer au début.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, création d'un nouveau noeud contenant
			//l'élément à insérer après la positon actuelle.
			position.suivant = new Noeud(element, position.suivant);
			
			//Si la position était à la fin de la liste,
			//la fin devient la position suivante.
			if(position == fin){
				
				fin = position.suivant;
			}
			
			//Par norme, la position devient celle de l'élément inséré.
			position = fin;
			
			//Incrémentation du nombre d'éléments
			nbElements ++;
		}
	}
	
	/**
	 * Insère un élément avant la position actuelle.
	 * @param element élément à insérer
	 */
	public void insereAvant(Object element){
		
		//Si la liste est vide,
		//procède comme pour insérer au début.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, la position est retenue dans une variable temporaire.
			Noeud temporaire = position;
			
			//L'élément actuel est inséré après lui-même.
			//Ce faisant, la position passe à ce double de l'élément actuel.
			insererApres(position.element);
			
			//L'élément actuel devient le nouvel élément.
			temporaire.element = element;
			
			//La position redevient ce qu'elle était.
			//Elle représente l'élément nouvellement inséré.
			position = temporaire;
		}
	}
}
