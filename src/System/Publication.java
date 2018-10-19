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
public class Publication {
    
    // Attributes //
    private String name;
    private Date date;
    private String researchproject;
    private String conference;
    
    // Lists
    private LinkedList <Collaborator> participants;
    
    // Methods //
    // Constructor
    public void Publication() {
        participants = new LinkedList <Collaborator>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public void setResearchProject(String researchproject){
        this.researchproject = researchproject;
    }
    
    public String getResearchProject(){
        return this.researchproject;
    }
    
    public void setConference(String conference){
        this.conference = conference;
    }
    
    public String getConference(){
        return this.conference;
    }
    
    public void addParticipant(Collaborator participant){
        this.participants.add(participant);
    }
    
    public LinkedList<Collaborator> getParticipant(){
        return this.participants;
    }
    @Override
    public String toString(){
        return "TODO";
    }
}
