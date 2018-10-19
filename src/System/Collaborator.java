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
    
    protected LinkedList <Orientation> orientations;
    public Collaborator(){
        publications = new LinkedList <Publication>();
        projects = new LinkedList <Project>();
        orientations = new LinkedList <Orientation>();
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
    
    public void addPublication(Publication publication){
        this.publications.add(publication);
    }
    
    public LinkedList<Publication> getPublication(){
        return this.publications;
    }
    
    public void addProject(Project project){
        this.projects.add(project);
    }
    
    public LinkedList<Project> getProject(){
        return this.projects;
    }
    
    public void addOrientation(Orientation orientation){
        this.orientations.add(orientation);
    }
    
    public LinkedList<Orientation> getOrientation(){
        return this.orientations;
    }

    @Override
    public String toString(){
        return "TODO";
    }
}