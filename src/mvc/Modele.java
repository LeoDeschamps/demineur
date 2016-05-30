/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;
import java.util.Random;
import mvc.libInterpreteurExpr.Node;

/**
 *
 * @author fred
 */
public class Modele extends Observable {

    //private final int taille_grille = 50;
    Random rand = new Random();
    
    int[][] grille;
    int taille_grille;
    int nbrMine;
    int id_mine;
    
    public Modele(int taille, int mines)
    {
        this.taille_grille = taille;
        grille = new int[taille_grille][taille_grille];
        this.nbrMine = mines;
        this.id_mine = 9;
    }

    
    
    /*public void calc(String expr) {
        StringBuffer input;
        StringBuffer output;
        Node toto;

        input = new StringBuffer(expr);
        output = new StringBuffer(255);

        toto = Node.Construct_Tree(input, input.length(), 0);
        if (toto != null) {
            toto.Write_Tree(output);
            lastValue = toto.Compute_Tree(0, 0, 0);
            err = false;
        } else {
            err = true;
        }
        
        // notification de la vue, suite à la mise à jour du champ lastValue
        setChanged();
        notifyObservers();
    }*/
    
    public void genererMatrice(int taille_grille)
    {
        
        //Mise en place aléatoire des mines dans la grille
        for(int i=0;i<taille_grille;i++)
        {
            for(int j=0; j<taille_grille;j++)
            {
                grille[i][j] = 0;
            }
        }
        
        int abcisse = -1;
        int ordonnee = -1;
        while(nbrMine>0)
        {
            abcisse = rand.nextInt(50);
           ordonnee = rand.nextInt(50);
           if(grille[abcisse][ordonnee] == 0)
           {
               grille[abcisse][ordonnee] = id_mine;
               nbrMine--;
           }
        }
        
        //Mise en place des nombres autour des mines
        int compteur_mine = 0;
        for(int i=0;i<taille_grille;i++)
        {
            for(int j=0; j<taille_grille;j++)
            {
                compteur_mine = 0;
                if(grille[i][j] == 0)
                {
                    int i_moins = i-1;
                    int i_plus = i+1;
                    int j_moins = j-1;
                    int j_plus = j+1;
                    
                    if(i_moins>=0 && grille[i_moins][j] == id_mine) compteur_mine++;
                    if(j_moins>=0 && grille[i][j_moins] == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && grille[i_plus][j] == id_mine) compteur_mine++;
                    if(j_plus<taille_grille && grille[i][j_plus] == id_mine) compteur_mine++;
                    if(i_moins>=0 && j_moins>=0 && grille[i_moins][j_moins] == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && j_moins>=0 && grille[i_plus][j_moins] == id_mine) compteur_mine++;
                    if(i_moins>=0 && j_plus<taille_grille && grille[i_moins][j_plus] == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && j_plus<taille_grille && grille[i_plus][j_plus] == id_mine) compteur_mine++;
                    
                    grille[i][j] = compteur_mine;
                }
            }
        }
    }
    
    //Affichage console de la matrice
    public void afficherMatrice()
    {
        for(int i=0;i<taille_grille;i++)
        {
            for(int j=0; j<taille_grille;j++)
            {
                System.out.print(grille[i][j]);
            }
            System.out.println();
        }
    }
    
}