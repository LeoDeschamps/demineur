/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.graphique;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Angelo
 */
public class Case{
    
    private ImageView img;
    private String nomImg;
    
    public Case(String choix) {
         this.setImage(choix);
    }
    
    public ImageView getImage() {
        return this.img;
    }
    
    public void setImage(String choix)
    {
        nomImg = choix;
        if("vide".equals(choix))
        {
            Image image = new Image(getClass().getResource("/Images/vide.png").toExternalForm());     
            // simple displays ImageView the image as is
            img = new ImageView();
            img.setImage(image);
        }
        else if("drapeau".equals(choix))
        {
            Image image = new Image(getClass().getResource("/Images/drapeau.png").toExternalForm()); 
            // simple displays ImageView the image as is
            img = new ImageView();
            img.setImage(image);
        }
        else {
            Image image = new Image(getClass().getResource("/Images/mine.png").toExternalForm());
            // simple displays ImageView the image as is
            img = new ImageView();
            img.setImage(image);
        }
    }
    
    public String getNomImg()
    {
        return this.nomImg;
    }
}
