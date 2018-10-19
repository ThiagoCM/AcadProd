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
    
    private String type;
    
    private int nprojects;
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void incrementProjects(){
        this.nprojects++;
    }
    
    public int getNumberProjects(){
        return this.nprojects;
    }
}
