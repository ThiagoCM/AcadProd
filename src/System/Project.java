/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Thiago
 */
public class Project {
    
    private String name;
    private Date begun;
    private Date ended;
    private String financier;
    private Float value;
    private String objective;
    private String description;
    private Collaborator manager;
    private String status;
    
    private LinkedList <Collaborator> participants;
    private LinkedList <Publication> publications;
    
    public Project(Professor professor){
        participants = new LinkedList <Collaborator>();
        publications = new LinkedList <Publication>();
        status = "Em elaboração.";
        participants.add(professor);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDateBegun(Date date){
        begun = date;
    }
    
    public Date getDateBegun(){
        return begun;
    }
    
    public void setDateEnded(Date date){
        ended = date;
    }
    
    public Date getDateEnded(){
        return ended;
    }
    
    public void setFinancier(String financier){
        this.financier = financier;
    }
    
    public String getFinancier(){
        return this.financier;
    }
    
    public void setValue(Float value){
        this.value = value;
    }
    
    public Float getValue(){
        return this.value;
    }
    
    public void setObjective(String objective){
        this.objective = objective;
    }
    
    public String getObjective(){
        return this.objective;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setManager(Collaborator manager){
        this.manager = manager;
    }
    
    public String getManager(){
        return manager.getName();
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void addParticipant(Collaborator participant){
        this.participants.add(participant);
    }
    
    public LinkedList<Collaborator> getParticipants(){
        return this.participants;
    }
    
    public void addPublication(Publication publication){
        this.publications.add(publication);
    }
    
    public LinkedList<Publication> getPublications(){
        return this.publications;
    }
    
    
    @Override
    public String toString(){
        return "TODO";
    }
}
