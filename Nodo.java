
/**
 *
 * @author Lix
 */
public class Nodo {
	public int id;
	public double costoCaminoAcumulado; //Contiene el costo del camino hace el nodo que corresponde al propio índice.
	public boolean alcanzado;//Ssabremos si fue alcanzado el nodo.
	public int representado;//Indica quien representa al nodo que lo representa.

	public Nodo(int id){
		this.id = id;
                this.costoCaminoAcumulado = 999.9;
                this.alcanzado = false;
                this.representado = -1;
	}

	public boolean compareTo(Nodo nodo){
		if(this.costoCaminoAcumulado < nodo.costoCaminoAcumulado){
			return true;
		}
		return false;
	}
}