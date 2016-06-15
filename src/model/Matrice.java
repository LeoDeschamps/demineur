/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Léo
 */
public class Matrice extends Observable {
    Case[][] grille;
    int taille_grille;
    int nbrMine;
    int id_mine; 
    boolean aPerdu = false;
    boolean aGagne = false;
    
    Random rand = new Random();
    
    public Matrice(int taille)
    {
        this.taille_grille = taille;
        grille = new Case[taille_grille][taille_grille];
        this.nbrMine = taille;
        this.id_mine = 9;
    }
    
    public void genererMatrice()
    {
        
        //Mise en place aléatoire des mines dans la grille
        System.out.println("taille : " + taille_grille);
        for(int i=0;i<taille_grille;i++)
        {
            for(int j=0; j<taille_grille;j++)
            {
                grille[i][j] = new Case();
            }
        }
        
        int abcisse = -1;
        int ordonnee = -1;
        while(nbrMine>0)
        {
            abcisse = rand.nextInt(taille_grille);
           ordonnee = rand.nextInt(taille_grille);
           if(grille[abcisse][ordonnee].getValue() == 0)
           {
               grille[abcisse][ordonnee].setValue(id_mine);
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
                if(grille[i][j].getValue() == 0)
                {
                    int i_moins = i-1;
                    int i_plus = i+1;
                    int j_moins = j-1;
                    int j_plus = j+1;
                    
                    if(i_moins>=0 && grille[i_moins][j].getValue() == id_mine) compteur_mine++;
                    if(j_moins>=0 && grille[i][j_moins].getValue() == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && grille[i_plus][j].getValue() == id_mine) compteur_mine++;
                    if(j_plus<taille_grille && grille[i][j_plus].getValue() == id_mine) compteur_mine++;
                    if(i_moins>=0 && j_moins>=0 && grille[i_moins][j_moins].getValue() == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && j_moins>=0 && grille[i_plus][j_moins].getValue() == id_mine) compteur_mine++;
                    if(i_moins>=0 && j_plus<taille_grille && grille[i_moins][j_plus].getValue() == id_mine) compteur_mine++;
                    if(i_plus<taille_grille && j_plus<taille_grille && grille[i_plus][j_plus].getValue() == id_mine) compteur_mine++;
                    
                    grille[i][j].setValue(compteur_mine);
                }
            }
        }
    }
    
    public void afficherMatrice()
    {
        for(int i=0;i<taille_grille;i++)
        {
            for(int j=0; j<taille_grille;j++)
            {
                System.out.print(grille[j][i].getValue());
            }
            System.out.println();
        }
    }
    
    public int verifCase(int x, int y)
    {
        if(grille[x][y].getValue() == 9) return -1;
        else if(grille[x][y].getValue() == 0) return 0;
        else return grille[x][y].getValue();
    }
    
    public int getTaille()
    {
        return this.taille_grille;
    }
    
    public int getContenuCase(int x, int y)
    {
        return this.grille[x][y].getValue();
    }
    
    public int getIdMine()
    {
        return this.id_mine;
    }
    
    public Case getCase(int x, int y)
    {
        return grille[x][y];
    }
    
    public boolean estUneMine(Case c)
    {
        if(c.getValue() == 9)
        {
            aPerdu = true;
        }
        
        return aPerdu;
    }
    
    public boolean aGagne()
    {
        int noReturned = 0;
        for(int i = 0; i < taille_grille; i++) {
            for(int j = 0; j < taille_grille; j++){
                Case c = grille[i][j];
                
                if(!c.getReturned())
                {
                    noReturned++;
                }
            }
        }
        
        if(noReturned == taille_grille)
            aGagne = true;
        
        return aGagne;
    }
}
