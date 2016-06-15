/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.graphique;

import java.awt.Menu;
import java.awt.MenuBar;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Scene scene;
    private Matrice m;
    GridPane gPane;
    private boolean aPerdu;
    private boolean aGagne;
    private HBox root;
    
    public Grille(Matrice m)
    {
        this.nbCase = m.getTaille();
        this.m = m;
        initialise();
    }

    public void initialise() {
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        root = new HBox();
        
        
        BorderPane top = new BorderPane();
        VBox block = new VBox();
        
        MenuBar menuBar = new MenuBar();
 
        // --- Menu File
        Menu options = new Menu("Options");
 
        menuBar.add(options);
        //top.setTop((Node) menuBar);
        //block.getChildren().addAll(menuBar, top);
        root.getChildren().add(top);
        
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
                        if(!aPerdu && !aGagne) {
                            if(event.getButton() == MouseButton.PRIMARY) {
                                devoilerCases(x, y);
                            }
                            else if(event.getButton() == MouseButton.SECONDARY)
                            {
                                System.out.println(cell.getNomImg());
                                if(!"drapeau".equals(cell.getNomImg())) {
                                    m.getCase(x, y).setFlag(true);
                                    cell.setImage("drapeau");
                                    pictureRegion.getChildren().clear();
                                    pictureRegion.getChildren().add(cell.getImage());
                                    gPane.add(pictureRegion, x, y);
                                }
                                else {
                                    cell.setImage("vide");
                                    m.getCase(x, y).setFlag(false);
                                    pictureRegion.getChildren().clear();
                                    pictureRegion.getChildren().add(cell.getImage());
                                    gPane.add(pictureRegion, x, y);
                                }
                            } 
                        }
                    }
                });
            }
        }
        
        gPane.setGridLinesVisible(true);
        
        root.getChildren().add(gPane);
        
        this.scene = new Scene(root, Color.LIGHTGRAY);
    }
    
    public Scene getScene()
    {
        return this.scene;
    }
    
    public void devoilerCases(int x, int y)
    {
        model.Case c = m.getCase(x, y);
        c.setReturned(true);
        aPerdu = m.estUneMine(c);
        aGagne = m.aGagne();
        if(m.verifCase(x, y) == -1)
        {
            final HBox pictureRegion = new HBox();
            Case cell = new Case("mine");
            pictureRegion.getChildren().add(cell.getImage());
            gPane.add(pictureRegion, x, y);
        }
        else if(m.verifCase(x, y) == 0)
        {
            final HBox pictureRegion = new HBox();
            Text t = new Text(" ");
            t.setTextAlignment(TextAlignment.CENTER);
            t.setWrappingWidth(30);
            pictureRegion.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,CornerRadii.EMPTY,Insets.EMPTY)));
            pictureRegion.setStyle("-fx-border-color: #7f7f7f;");
            pictureRegion.getChildren().add(t);
            gPane.add(pictureRegion, x, y);
            
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
            final HBox pictureRegion = new HBox();
            Text t = new Text(String.valueOf(m.verifCase(x, y)));
            t.setTextAlignment(TextAlignment.CENTER);
            t.setWrappingWidth(30);
            t.setFont(new Font(15));
            t.setFill(Color.RED);
            pictureRegion.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,CornerRadii.EMPTY,Insets.EMPTY)));
            pictureRegion.setStyle("-fx-border-color: #7f7f7f;");
            pictureRegion.getChildren().add(t);
            gPane.add(pictureRegion, x, y);
        }
        
        if(aPerdu || aGagne){
            afficheScore();
        }
    }
    
    public void afficheScore()
    {
        Text score;
        
        if(aPerdu) {
            System.out.println("Vous avez perdu !");
            score = new Text("Vous avez perdu ...");
        }
        else {
            System.out.println("Vous avez gagné !");
            score = new Text("Vous avez gagné !");
        }
        
        VBox affichage = new VBox();
        affichage.setSpacing(10);
        affichage.setPadding(new Insets(0, 10, 10, 10)); 
        affichage.getChildren().addAll(score);
        
        affichage.setAlignment(Pos.CENTER);
        
        root.getChildren().add(affichage);
    }
}
