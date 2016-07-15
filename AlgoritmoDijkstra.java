import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lix
 */
public class AlgoritmoDijkstra {
    
    final private double infinity = 999.9;//Ni cerca del infinito :D
    
    private double[] costoDeCamino;//Contiene el costo del camino hace el nodo que corresponde al propio índice.
    private boolean[] alcanzado;//Para cada índice representate de un nodo, sabremos si fue alcanzado o no.
    private Adyacencia[] listaAdyacencias;//Contiene las adyacencias entregadas por el grafo.
    private int n;//Corresponde a la cantidad de nodos.
    private ArrayList<Integer> cola;//Cola de espera para los nodos.
    private int[] representado;//Contiene por cada índice(representante de un nodo) al nodo que lo representa.
    public long tiempoTotal;//Tiempo total de ejecución.
    public double sumaCostosAdyacencias;//Costo total del árbol,
    private boolean verboso;//Verificador función verboso

    public AlgoritmoDijkstra( Adyacencia[] listaAdyacencias, int n, boolean verboso) {
        this.verboso = verboso;
        long tiempoI = System.currentTimeMillis();
        this.n = n;
        this.cola = new ArrayList<>();
        this.representado = new int[this.n];
        this.alcanzado = new boolean[this.n];
        this.listaAdyacencias = listaAdyacencias;
        this.costoDeCamino = new double[this.n];
        for(int i = 0; i < n; i++){
            this.costoDeCamino[i] = this.infinity;//Para comparar, le asignamos un valor muy grande.
            this.alcanzado[i] = false;//Ningun nodo esta alcanzado
            this.representado[i] = -1;//Ningun nodo esta representado por otro nodo.
        }
        
        //Nuestro nodo inicial siempre será el 0.
        this.costoDeCamino[0] = 0;//Le damos costo 0
        this.representado[0] = 0;//Le asignamos su representante.
        
        cola.add(0);//Lo agregamos a la cola.
        while(!cola.isEmpty()){//Preguntamos si está vacía
            int[] menor = nodoMenorCosto();//Obtenemos el nodo con el menor costo de la cola.
            int indice = menor[0];//Le entregamos el nodo a otra variable por efecto de comodidad
            this.cola.remove(menor[1]);//Eliminamos el nodo de la cola.
            if(this.alcanzado[indice]){//Preguntamos si ya fue alcanzado
                continue;//Pasamos a la siguiente iteración.
            }
            this.alcanzado[indice] = true;//Decimos que el nodo actual fue alcanzado
            for(int i = 0; i < this.listaAdyacencias[indice].nodosAdyancentes.size(); i++){//Recorremos los nodos adyacente al nodo elegido anteriormente.
                int adyacencia = this.listaAdyacencias[indice].nodosAdyancentes.get(i);//Obtenemos el nodo adyacente
                double costo = this.listaAdyacencias[indice].costos.get(i);//Obtenemos el costo del arco que se forma entre los nodos.
                if(!this.alcanzado[adyacencia]){//Preguntamos si no fue alcanzado el nodo adyacente
                    if(this.costoDeCamino[indice] + costo < this.costoDeCamino[adyacencia]){//Verificamos el costo de camino hasta ese nodo
                        this.costoDeCamino[adyacencia] = this.costoDeCamino[indice] + costo;//Si es menor el nuevo costo, lo asignamos.
                        this.cola.add(adyacencia);//Agregamos el nodo a la cola.
                        this.representado[adyacencia] = indice;//Le agregamos un representante.
                    }
                }
            }
        }
        this.imprimirCaminoYSumaCostos();
        long tiempoII = System.currentTimeMillis();
        this.tiempoTotal = tiempoII-tiempoI;
    }
   
    
    private int[] nodoMenorCosto(){
        double menor = this.infinity;
        int[] retornarNodo = new int[2];
        for(int i = 0; i < this.cola.size(); i++){
            if(this.costoDeCamino[this.cola.get(i)] < menor){
                menor = this.costoDeCamino[this.cola.get(i)];
                retornarNodo[0] = this.cola.get(i);
                retornarNodo[1] = i;
            }
        }
        return  retornarNodo;
    }
    
    public void imprimirCaminoYSumaCostos(){
        this.sumaCostosAdyacencias = 0.0;
        for(int i = 0; i < this.n; i++){
            this.sumaCostosAdyacencias = this.sumaCostosAdyacencias + this.listaAdyacencias[i].getCosto(this.representado[i]);
            if(this.verboso){
                System.out.println("El Nodo: "+this.representado[i]+" representa al Nodo "+i+" con un costo de "+this.costoDeCamino[i] );
            }
        }
    }
    
}
