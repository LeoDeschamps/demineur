/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;

/**
 *
 * @author fred
 */
public class Modele extends Observable {

    double lastValue;
    boolean err = false;

    
    
    public void calc(String expr) {
        StringBuffer input;
        StringBuffer output;

        input = new StringBuffer(expr);
        output = new StringBuffer(255);
        
        // notification de la vue, suite à la mise à jour du champ lastValue
        setChanged();
        notifyObservers();
    }
}