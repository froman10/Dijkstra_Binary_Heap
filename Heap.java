import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Lix
 */
public class Heap {
    private ArrayList<Nodo> items;
     
    public Heap() {
        items = new ArrayList<Nodo>();
    }
         
    private void heapifyUp() {
        int k = items.size() - 1;
        while (k > 0) {
            int p = (k-1)/2;
            Nodo item = items.get(k);
            Nodo parent = items.get(p);
            if (item.compareTo(parent)) {
                // swap
                items.set(k, parent);
                items.set(p, item);
                 
                // move up one level
                k = p;
            } else {
                break;
            }
        }
    }
     
    public void insert(Nodo item) {
        items.add(item);
        heapifyUp();
    }
     
    private void percolateDown() {
        int k = 0;
        int l = 2*k+1;
        while (l < items.size()) {
            int max=l, r=l+1;
            if (r < items.size()) { // there is a right child
                if (items.get(r).compareTo(items.get(l))) {
                    max++;
                }
            }
            if (!items.get(k).compareTo(items.get(max))) {
                    // switch
                    Nodo temp = items.get(k);
                    items.set(k, items.get(max));
                    items.set(max, temp);
                    k = max;
                    l = 2*k+1;
            } else {
                break;
            }
        }
    }
     
    public Nodo extractMin() 
    throws NoSuchElementException {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        Nodo hold = items.get(0);
        items.set(0, items.remove(items.size()-1));
        percolateDown();
        return hold;
    }
 
    public int size() {
        return items.size();
    }
     
    public boolean isEmpty() {
        return items.isEmpty();
         
    }
     
    public String toString() {
        return items.toString();
    }
    public void imprimirHeap(){
        for(int i = 0; i < items.size();i++){
            System.out.println("Heap: "+items.get(i).id+" con costo "+items.get(i).costoCaminoAcumulado);
        }
    }
}
