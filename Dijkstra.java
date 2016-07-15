/**
 *
 * @author Lix
 */
public class Dijkstra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean verboso = false;
        int n;
        double densidad;
        int nrep;
        int sumaAristas = 0;
        double sumaCostosAdyacencias = 0;
        double sumaCostosAdyacencias2 = 0;
        long sumaTiempos = 0;
        long sumaTiempos2 = 0;
        
        if(args[0].equals("-v")){
            verboso = true;
            n = Integer.parseInt(args[1]);
            densidad = Double.parseDouble(args[2]);
            nrep = Integer.parseInt(args[3]);
        }
        else{
            n = Integer.parseInt(args[0]);
            densidad = Double.parseDouble(args[1]);
            nrep = Integer.parseInt(args[2]);
        }
        for(int i = 0; i < nrep; i++){
            Grafo grafo = new Grafo(n, densidad);
            AlgoritmoDijkstra dijkstra = new AlgoritmoDijkstra(grafo.listaAdyacencias, n,verboso);
            AlgoritmoDijkstraBH dijkstraBH = new AlgoritmoDijkstraBH(grafo.listaAdyacencias, n,verboso);
            sumaTiempos = sumaTiempos + dijkstra.tiempoTotal;
            sumaTiempos2 = sumaTiempos2 + dijkstraBH.tiempoTotal;
            sumaCostosAdyacencias = sumaCostosAdyacencias + dijkstra.sumaCostosAdyacencias;
            sumaCostosAdyacencias2 = sumaCostosAdyacencias2 + dijkstraBH.sumaCostosAdyacencias;
            if(verboso){
                grafo.imprimirGrafo();
            }
        }

        System.out.println("D: El costo promedio del arbol de propagacion de camino mas corto es de: "+sumaCostosAdyacencias/nrep);
        System.out.println("DBH: El costo promedio del arbol de propagacion de camino mas corto es de: "+sumaCostosAdyacencias2/nrep);
        System.out.println("Tiempo promedio de ejecucion Dijkstra: "+sumaTiempos*0.001/nrep+" segundos.");
        System.out.println("Tiempo promedio de ejecucion DijkstraBH: "+sumaTiempos2*0.001/nrep+" segundos.");
        if((sumaTiempos*0.001/nrep)>(sumaTiempos2*0.001/nrep)){
            System.out.println("Gano Dijkstra BH");
        }
        else{
            System.out.println("Gano Dijkstra ");
        }
    }
}
