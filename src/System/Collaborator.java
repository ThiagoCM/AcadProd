/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.util.LinkedList;

/**
 *
 * @author Thiago
 */
public class Collaborator {
    
    private String name;
    
    private String email;
        
    protected LinkedList <Publication> publications;
    
    protected LinkedList <Project> projects;

    public Collaborator(){
        publications = new LinkedList <Publication>();
        projects = new LinkedList <Project>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString(){
        return "TODO";
    }
}
