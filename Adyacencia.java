import java.util.ArrayList;
/**
 *
 * @author Lix
 */
public class Adyacencia {
    public int nodo;
    public ArrayList<Integer> nodosAdyancentes;
    public ArrayList<Double> costos;
    

    public Adyacencia(int nodo) {
        this.nodo = nodo;
        this.nodosAdyancentes = new ArrayList<>();
        this.costos = new ArrayList<>();
    }
    
    public void ToString(){
        System.out.println("Nodo : "+nodo);
        
        for(int i =0; i < nodosAdyancentes.size();i++){
            System.out.println("Nodo "+nodosAdyancentes.get(i)+" y Costo "+costos.get(i));
        }
        System.out.println("");
    }
    
    public double getCosto(int indice){
        for(int i = 0; i < this.nodosAdyancentes.size(); i++){
            if(this.nodosAdyancentes.get(0) == indice){
                return costos.get(i);
            }
        }
        
        return 0;
    }
}
