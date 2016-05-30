/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.graphique;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Angelo
 */
public class Grille {
    
    private int nbCase;
    private final int tailleCase = 50;
    private Scene scene;
    
    public Grille(int nbCaseLigne)
    {
        this.nbCase = nbCaseLigne;
        initialise();
    }

    public void initialise() {
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        gPane.resize(600, 600);
        for(int i = 0; i < nbCase; i++) {
            for(int j = 0; j < nbCase; j++) {
                Text t = new Text(" ");
                gPane.add(t, i, j);
                t.setTextAlignment(TextAlignment.CENTER);

                // un controleur écoute le bouton et déclenche l'appel du modèle
                t.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Case X");
                    }
                });
            }
        }
        
        gPane.setPrefSize(600, 600);
        gPane.setMinSize(600, 600);
        System.out.println("H : " + gPane.getHeight() + " W : " + gPane.getWidth());
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        this.scene = new Scene(border, Color.LIGHTGRAY);
    }
    
    public Scene getScene()
    {
        return this.scene;
    }
}
