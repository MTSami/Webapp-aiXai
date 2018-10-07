package prototype;

/**
 *
 * @author Mirza Tanzim Sami
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Prototype {

    /**
     * @param args the command line arguments
     */
    static List<Location> places = new ArrayList<Location>();
    static String userLocation;
    static String userDestination;
    public static void main(String[] args) {
        makeWorld();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your current location:");
        userLocation=scan.next();
        System.out.println("Please enter your destination:");
        userDestination=scan.next();
        
        findDriver(userLocation);
        findRoute(userLocation, userDestination);
    }
    
    public static void makeWorld(){
        Location l1 = new Location("Elephantroad");
        
        Neighbor n1 = new Neighbor("Dhanmondi", 35.0);
        Neighbor n2 = new Neighbor("Newmarket", 20.5);
        Driver d1 = new Driver("Robi", false);
        Driver d2 = new Driver("Zia", true);
        
        l1.neighbors.add(n1);
        l1.neighbors.add(n2);
        l1.drivers.add(d1);
        l1.drivers.add(d2);
        places.add(l1);
        
        //-------------------------------------------------------------
        Location l2 = new Location("Dhanmondi");
        
        //Neighbor n3 = new Neighbor("Elephantroad", 35.0);
        //Neighbor n4 = new Neighbor("Newmarket", 48.2);
        Driver d3 = new Driver("Akbar", true);
        Driver d4 = new Driver("Ahmed", false);
        Driver d5 = new Driver("Hasan", true);
        
        //l2.neighbors.add(n3);
        //l2.neighbors.add(n4);
        l2.drivers.add(d3);
        l2.drivers.add(d4);
        l2.drivers.add(d5);
        places.add(l2);
        
        //-------------------------------------------------------------
        Location l3 = new Location("Newmarket");
        
        //Neighbor n5 = new Neighbor("Elephantroad", 20.5);
        //Neighbor n6 = new Neighbor("Dhanmondi", 48.2);
        Driver d6 = new Driver("Alam", false);
        Driver d7 = new Driver("Abul", false);
        
        //l3.neighbors.add(n5);
        //l3.neighbors.add(n6);
        l3.drivers.add(d6);
        l3.drivers.add(d7);
        places.add(l3);
        
        
    }
    
    public static void findDriver(String userLoc){
        int psize = places.size();
        
        for (int i=0;i<psize;i++){
            if(userLoc.equals(places.get(i).name)) {
                //System.out.print("Match");
                int dsize=places.get(i).drivers.size();
                for (int j=0;j<dsize;j++){
                    if((places.get(i).drivers.get(j).available)==true){
                        System.out.println("Yay! " + places.get(i).drivers.get(j).name +" will be your driver.");
                        places.get(i).drivers.get(j).available=false;
                        return;
                    }
                }                
            }
        }
        System.out.println("Sorry, no drivers are currently available at "+ userLoc);
        
    }
    
    public static int getIndex(String place){
        
        for (int i=0;i<places.size();i++){
            if(places.get(i).name.equals(place)){
                return i;
            }
        }
        return -1;
    }
    
    public static void findRoute(String a, String b){
        
        int vertices = places.size();
        int source = getIndex(a);
        int destination = getIndex(b);
        int[] dist = new int[vertices];
        int[] prev = new int[vertices];
        int u, alt;
        Location x;
        
        for (int i = 0; i < vertices; i++) {
            dist[i] =999;
            prev[i] = 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(vertices, new Comparator<Integer>() {

            @Override
            public int compare(Integer f, Integer s) {
                if (dist[f] > dist[s]) {
                    return 1;
                }

                if (dist[f] < dist[s]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        
                
        dist[source] = 0;

        for (int i = 0; i < vertices; i++) {
            pq.add(i);
        }

        

//         while ( !(pq.isEmpty()) ) {
//            System.out.println("Gej"+pq.remove());
//        }

        while ( !(pq.isEmpty()) ) {
            
            u = pq.remove();
            //System.out.println("Name: "+ places.get(u).name);
            x = places.get(u);
            //System.out.print(u);
            for (int v = 0; v < x.neighbors.size(); v++) {

                    alt = (int) (dist[u] + x.neighbors.get(v).cost);
                    //System.out.println(alt);
                    if (alt < dist[getIndex(x.neighbors.get(v).name)]) {
                        dist[getIndex(x.neighbors.get(v).name)] = alt;
                        prev[getIndex(x.neighbors.get(v).name)] = u;
                    }

            }
        }
        
        System.out.println("Distance to your destination is approximately " + dist[destination] +"kms.");
        System.out.print("Your map: ");

        
//        for (int i = 0; i < vertices; i++) {
//            System.out.print(dist[i]+" ");
//        }
//        
//        System.out.println(" ");
//        
        for (int i = 0; i < destination; i++) {
            System.out.print(places.get(prev[i]).name+">");
        }
        System.out.println(b);
        
        
    }
    
    
    
}
