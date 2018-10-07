/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mirza Tanzim Sami
 */
public class Location {
    String name;
    List<Neighbor> neighbors = new ArrayList<Neighbor>();
    List<Driver> drivers = new ArrayList<Driver>();
    
    Location(String nam){
        name=nam;
    }
    
}
