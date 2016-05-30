/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.graphique;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Matrice;
import mvc.Modele;

/**
 *
 * @author Angelo
 */
public class Grille {
    
    private int nbCase;
    private final int tailleCase = 50;
    private Scene scene;
    private Matrice m;
    private int row;
    private int column;
    GridPane gPane;
    
    public Grille(Matrice m)
    {
        this.nbCase = m.getTaille();
        this.m = m;
        initialise();
    }

    public void initialise() {
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        gPane = new GridPane();

        for(int i = 0; i < nbCase; i++) {
            for(int j = 0; j < nbCase; j++) {
                int x = i;
                int y = j;
                final HBox pictureRegion = new HBox();
                Case cell = new Case("vide");
                pictureRegion.getChildren().add(cell.getImage());
                gPane.add(pictureRegion, i, j);

                // un controleur écoute le bouton et déclenche l'appel du modèle
                pictureRegion.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        devoilerCases(x, y);
                        
                        
                    }
                });
            }
        }
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        this.scene = new Scene(border, Color.LIGHTGRAY);
    }
    
    public Scene getScene()
    {
        return this.scene;
    }
    
    public void devoilerCases(int x, int y)
    {
        m.getCase(x, y).setReturned(true);
        if(m.verifCase(x, y) == -1)
        {
            System.out.println("MINE");
        }
        else if(m.verifCase(x, y) == 0)
        {
            System.out.println("dévoilage récursif");
            if(x-1>=0 && m.getContenuCase(x-1, y) != m.getIdMine() && !m.getCase(x-1, y).getReturned())
            {
                devoilerCases(x-1, y);
            }
            if(y-1>=0 && m.getContenuCase(x, y-1) != m.getIdMine() && !m.getCase(x, y-1).getReturned())
            {
                devoilerCases(x, y-1);
            }
            if(x+1<nbCase && m.getContenuCase(x+1, y) != m.getIdMine() && !m.getCase(x+1, y).getReturned())
            {
                devoilerCases(x+1, y);
            }
            if(y+1<nbCase && m.getContenuCase(x, y+1) != m.getIdMine() && !m.getCase(x, y+1).getReturned())
            {
                devoilerCases(x, y+1);
            }
        }
        else
        {
            System.out.println("juste à coté");
        }
    }
}
