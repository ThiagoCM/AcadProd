/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

/**
 *
 * @author Thiago
 */
public class Student extends Collaborator{
    
    // Attributes //
    private int type;
    
    private int activeprojects = 0;
    
    // Methods //
    public void setType(int type){
        this.type = type;
    }
    
    public int getType(){
        return this.type;
    }
    
    public void incrementProjects(){
        this.activeprojects++;
    }
    
    public int getNumberProjects(){
        return this.activeprojects;
    }
}
