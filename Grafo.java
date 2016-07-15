import java.util.ArrayList;



public class Grafo {
    private final double infinity = 999.9;
    private double[][] coordenadaNodo;
    private double[][] matrizAdyacencias;
    private int n;
    private double densidad;
    private double probabilidadArco;
    public Adyacencia[] listaAdyacencias;

    public Grafo(int n,double densidad) {
        this.listaAdyacencias = new Adyacencia[n];
        this.coordenadaNodo = new double[n][2];
        this.matrizAdyacencias = new double[n][n];
        this.densidad = densidad;
        this.n = n;
        this.probabilidadArco = densidad*n*(n-1)/2;
        this.completarCoordenadas();
        this.completarMatrizPorProbabilidad();
        this.completarMatrizPorDiagonales();
        this.extraerAdyacencias();
        //this.imprimirGrafo();
        //this.imprimirMatriz();
        
    }
    private void completarCoordenadas(){

        
        for(int i = 0; i < n; i++){
            this.coordenadaNodo[i][0] = Math.random();
            
            this.coordenadaNodo[i][1] = Math.random();
        }
    }
    //Borrar
    private void inicializarMatriz(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                this.matrizAdyacencias[i][j] = 0.0;
            }
        }
    }
    private void completarMatrizPorDiagonales(){
        for(int i = 0, j = 1; i < n-1; i++,j++){
            this.matrizAdyacencias[i][j] = this.distanciaEuclidiana(i,j);
            this.matrizAdyacencias[j][i] = this.distanciaEuclidiana(j,i);
            
        }
    }
    private void completarMatrizPorProbabilidad(){
        double random;

        int aux = -1; 
        for(int i = 0; i < n; i++){
            aux += 1;
            int j = aux;
            while(j < n){
                if(i != j){
                    if(densidad > Math.random()){
                        this.matrizAdyacencias[i][j] = this.distanciaEuclidiana(i,j);
                        this.matrizAdyacencias[j][i] = this.distanciaEuclidiana(j,i);
                    }
                    else{
                        this.matrizAdyacencias[i][j] = this.infinity;
                        this.matrizAdyacencias[j][i] = this.infinity;
                    }
                }
                else{
                    this.matrizAdyacencias[i][j] = this.infinity;
                }
                j+=1;
            }
        }
        
        
    }

    private void extraerAdyacencias(){
        Adyacencia adyacencia;
        for(int i = 0; i < n; i++){
            adyacencia = new Adyacencia(i);
            for(int j = 0; j < n; j++){
                if(this.matrizAdyacencias[i][j] != this.infinity){
                    adyacencia.nodosAdyancentes.add(j);
                    adyacencia.costos.add(this.matrizAdyacencias[i][j]);
                }
            }
            this.listaAdyacencias[i] = adyacencia;
        }
    }
    private double distanciaEuclidiana(int i, int j){
        double p = Math.pow((this.coordenadaNodo[j][0] - this.coordenadaNodo[i][0]),2.0);
        double q = Math.pow((this.coordenadaNodo[j][1] - this.coordenadaNodo[i][1]),2.0);
        double distancia = Math.sqrt(q + p);
        return distancia;
    }
    public void imprimirNodos(){
        for(int i = 0; i < n; i++){

            System.out.print("Nodo: "+i+" es "+this.coordenadaNodo[i][0]+" ");
            System.out.println(this.coordenadaNodo[i][1]);
        }
    }
    
    public void imprimirMatriz(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(String.format("%.2f", this.matrizAdyacencias[i][j])+" ");

            }
            System.out.println("");
         }
    }

    public void imprimirGrafo(){
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                System.out.println("Nodo "+i+" Nodo: "+j+" Peso: "+String.format("%.3f", this.matrizAdyacencias[i][j]));
            }
        }
    }
    
}

