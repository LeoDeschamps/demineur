/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Léo
 */
public class Case {
    
    private int value;
    private boolean returned;
    
    public Case()
    {
        this.value = 0;
        this.returned = false;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public void setValue(int v)
    {
        this.value = v;
    }
    
    public void setReturned(boolean b)
    {
        this.returned = b;
    }
    
    public boolean getReturned()
    {
        return this.returned;
    }
}
