import java.util.ArrayList;
/**
 *
 * @author Lix
 */
public class AlgoritmoDijkstraBH {
    
    final private double infinity = 999.9;//Ni cerca del infinito :D
    
    private Adyacencia[] listaAdyacencias;//Contiene las adyacencias entregadas por el grafo.
    private int n;//Corresponde a la cantidad de nodos.
    public long tiempoTotal;//Tiempo total de ejecución.
    public double sumaCostosAdyacencias;//Costo total del árbol,
    private boolean verboso;//Verificador función verboso
    private Nodo[] nodos;
    private Heap hp;

    public AlgoritmoDijkstraBH( Adyacencia[] listaAdyacencias, int n, boolean verboso) {
        this.hp = new Heap(); //Heap de mínimos
        this.verboso = verboso;
        long tiempoI = System.currentTimeMillis();
        this.n = n;
        this.listaAdyacencias = listaAdyacencias;
        this.nodos = new Nodo[n];

        for(int i = 0; i < n; i++){
            this.nodos[i] = new Nodo(i);
        }
        
        //Nuestro nodo inicial siempre será el 0.

        this.nodos[0].costoCaminoAcumulado = 0;//Le damos costo 0
        this.nodos[0].representado = 0;//Le asignamos su representante.
        
        this.hp.insert(this.nodos[0]);//Lo agregamos al heap.
        while(!this.hp.isEmpty()){//Preguntamos si está vacíp el heap
            int nodoEnRevision = (this.hp.extractMin()).id;//Obtenemos el nodo con el menor costo de la hp.
            if(this.nodos[nodoEnRevision].alcanzado){//Preguntamos si ya fue alcanzado
                continue;//Pasamos a la siguiente iteración.
            }
            this.nodos[nodoEnRevision].alcanzado = true;//Decimos que el nodo actual fue alcanzado
            for(int i = 0; i < this.listaAdyacencias[nodoEnRevision].nodosAdyancentes.size(); i++){//Recorremos los nodos adyacente al nodo elegido anteriormente.
                int nodoAdyacente = this.listaAdyacencias[nodoEnRevision].nodosAdyancentes.get(i);//Obtenemos el nodo adyacente
                double costo = this.listaAdyacencias[nodoEnRevision].costos.get(i);//Obtenemos el costo del arco que se forma entre los nodos.
                if(!this.nodos[nodoAdyacente].alcanzado){//Preguntamos si no fue alcanzado el nodo adyacente
                    if(this.nodos[nodoEnRevision].costoCaminoAcumulado + costo < this.nodos[nodoAdyacente].costoCaminoAcumulado){//Verificamos el costo de camino hasta ese nodo
                        this.nodos[nodoAdyacente].costoCaminoAcumulado = this.nodos[nodoEnRevision].costoCaminoAcumulado + costo;//Si es menor el nuevo costo, lo asignamos.
                        this.hp.insert(this.nodos[nodoAdyacente]);//Agregamos el nodo al heap.
                        this.nodos[nodoAdyacente].representado = nodoEnRevision;//Le agregamos un representante.
                    }
                }
            }
        }
        this.imprimirCaminoYSumaCostos();
        long tiempoII = System.currentTimeMillis();
        this.tiempoTotal = tiempoII-tiempoI;
    }
   
    
    public void imprimirCaminoYSumaCostos(){
        this.sumaCostosAdyacencias = 0.0;
        for(int i = 0; i < this.n; i++){
            this.sumaCostosAdyacencias = this.sumaCostosAdyacencias + this.listaAdyacencias[i].getCosto(this.nodos[i].representado);
            if(this.verboso){
                System.out.println("El Nodo: "+this.nodos[i].representado+" representa al Nodo "+i+" con un costo de "+this.nodos[i].costoCaminoAcumulado );
            }
        }
    }
    
}
