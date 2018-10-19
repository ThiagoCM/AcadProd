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
    
    private String name;
    private Date date;
    private String researchproject;
    private String conference;
    
    private LinkedList <Collaborator> participants;
    
    public void AcademicProd() {
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
    
    @Override
    public String toString(){
        return "TODO";
    }
}
