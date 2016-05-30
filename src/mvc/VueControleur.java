
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mvc.graphique.Grille;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    private Modele m;
    // affiche la saisie et le résultat
    private Text affichage;
    // grille : ce qui est affiché à l'écran
    private Grille grille;
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
        
        //Initialisation de la grille graphique
        grille = new Grille(10);
        
        
        
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        /*border.setTop(affichage);*/
        
        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        m.addObserver(new Observer() {
            
            @Override
            public void update(Observable o, Object arg) {
                
            }
        });
        
        // on efface affichage lors du clic
        affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                affichage.setText("");
            }
            
        });
        
        Scene scene = grille.getScene();
        
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Démineur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
