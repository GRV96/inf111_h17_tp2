import java.io.Serializable;

/**
 * Classe liste g�n�rale avec position actuelle implicite
 * @author Pierre B�lisle
 * @version H2017
 */
public class Liste implements Serializable{
	
	/**
	 * Noeuds de la liste
	 * @author Pierre B�lisle
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
		 * Accesseur de l'�l�ment d'un noeud
		 * @return element
		 */
		public Object getElement(){
			
			return element;
		}
		
		/**
		 * Mutateur de l'�l�ment d'un noeud
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
		
	//Premier �l�ment de la liste
	private Noeud debut;
	
	//Dernier �l�ment de la liste
	private Noeud fin;
	
	//Position acutelle dans la liste
	private Noeud position;
	
	//Nombre d'�l�ments de la lise
	private int nbElements;
	
	/**
	 * Constructeur par d�faut
	 */
	public Liste(){
		
		//La liste est vide au moment de sa cr�ation.
		debut = fin = position = null;
		nbElements = 0;
	}
	
	/**
	 * D�termine si la liste est vide.
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
	 * Accesseur de l'�l�ment � la positon actuelle
	 * @return �l�ment actuel
	 */
	public Object getElementActuel(){
		
		return position.getElement();
	}
	
	/**
	 * Mutateur de l'�l�ment de la position actuelle
	 * @param element
	 */
	public void setElementActuel(Object element){
		
		position.setElement(element);
	}
	
	/**
	 * Met la positoin au d�but.
	 */
	public void setPstDebut(){
		
		position = debut;
	}
	
	/**
	 * Met la position � la fin.
	 */
	public void setPstFin(){
		
		position = fin;
	}
	
	/**
	 * Passe � l'�l�ment suivant si la position
	 * n'est pas d�j� � la fin de la liste.
	 */
	public void setPstSuivant(){
		
		if(position != fin){
			
			position = position.suivant;
		}
	}
	
	/**
	 * Ram�ne la positon � l'�l�ment pr�c�dent
	 * si elle n'est pas d�j� au d�but de la liste.
	 */
	public void setPstPrecedent(){
		
		//Fonctionne si la position n'est pas au d�but.
		if(position != debut){
			
			//Variable retenant temporairement un noeud.
			Noeud temporaire = debut;
			
			//Parcourt les �l�ments jusqu'� celui avant position.
			while(temporaire.suivant != position){
				
				temporaire = temporaire.suivant;
			}
			
			//La position devient l'�l�ment qui pr�c�de position,
			//retenu dans temporaire.
			position = temporaire;
		}
	}
	
	/**
	 * Ins�re  un �l�ment au d�but de la liste.
	 * @param element �l�ment � ins�rer
	 */
	public void insererDebut(Object element){
		
		//Le d�but est un nouveau noeud contenant l'�l�ment ins�r�.
		//Utilisation du constructeur par copie d'attributs
		debut = new Noeud(element, debut);
		
		//Si la liste �tait vide au d�part,
		//le d�but et la fin sont la m�me position.
		if(listeVide()){
			
			fin = debut;
		}
		
		//Par norme, la position devient celle de l'�l�ment ins�r�.
		position = debut;
		
		//Incr�mentation du nombre d'�l�ments
		nbElements ++;
	}
	
	/**
	 * Ins�re un �l�ment donn� � la fin de la liste.
	 * @param element element � ins�rer
	 */
	public void insererFin(Object element){
		
		//Si la liste est vide,
		//proc�de comme pour ins�rer au d�but.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, la fin est au nouveau noeud
			//contenant l'�l�ment � ins�rer.
			fin.suivant = new Noeud(element, null);
			
			//La fin devient l'�l�ment apr�s l'ancienne fin.
			fin = fin.suivant;
			
			//Par norme, la position devient celle de l'�l�ment ins�r�.
			position = fin;
			
			//Incr�mentation du nombre d'�l�ments
			nbElements ++;
		}
	}
	
	/**
	 * Ins�re un element apr�s la position actuelle.
	 * @param element �l�ment � ins�rer
	 */
	public void insererApres(Object element){
		
		//Si la liste est vide,
		//proc�de comme pour ins�rer au d�but.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, cr�ation d'un nouveau noeud contenant
			//l'�l�ment � ins�rer apr�s la positon actuelle.
			position.suivant = new Noeud(element, position.suivant);
			
			//Si la position �tait � la fin de la liste,
			//la fin devient la position suivante.
			if(position == fin){
				
				fin = position.suivant;
			}
			
			//Par norme, la position devient celle de l'�l�ment ins�r�.
			position = fin;
			
			//Incr�mentation du nombre d'�l�ments
			nbElements ++;
		}
	}
	
	/**
	 * Ins�re un �l�ment avant la position actuelle.
	 * @param element �l�ment � ins�rer
	 */
	public void insereAvant(Object element){
		
		//Si la liste est vide,
		//proc�de comme pour ins�rer au d�but.
		if(listeVide()){
			
			insererDebut(element);
		}
		
		else{
			//Sinon, la position est retenue dans une variable temporaire.
			Noeud temporaire = position;
			
			//L'�l�ment actuel est ins�r� apr�s lui-m�me.
			//Ce faisant, la position passe � ce double de l'�l�ment actuel.
			insererApres(position.element);
			
			//L'�l�ment actuel devient le nouvel �l�ment.
			temporaire.element = element;
			
			//La position redevient ce qu'elle �tait.
			//Elle repr�sente l'�l�ment nouvellement ins�r�.
			position = temporaire;
		}
	}
}
