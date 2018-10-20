/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;

import java.util.LinkedList;

/**
 *
 * @author Thiago
 */
public class Project {
    
    // Atributes //
    
    private String name;
    private int b_day, b_month, b_year;
    private int e_day, e_month, e_year;
    private String financier;
    private Double value;
    private String objective;
    private String description;
    private Collaborator manager;
    private int status;
   
    // Lists
    private LinkedList <Collaborator> participants;
    private LinkedList <Publication> publications;
    
    // Methods //
    // Constructor
    public Project(){
        participants = new LinkedList <Collaborator>();
        publications = new LinkedList <Publication>();
        status = 1;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDateBegun(int day, int month, int year){
        this.b_day = day;
        this.b_month = month;
        this.b_year = year;
    }
    
    public int getDateBegunDay(){
        return this.b_day;
    }
    
    public int getDateBegunMonth(){
        return this.b_month;
    }
        
    public int getDateBegunYear(){
        return this.b_year;
    }
    
    public void setDateEnded(int day, int month, int year){
        this.e_day = day;
        this.e_month = month;
        this.e_year = year;
    }
    
    public int getDateEnded(){
        return (this.e_day + (this.e_month*60) + (this.e_year*365));
    }
    
    public int getDateEndedDay(){
        return this.e_day;
    }
    public int getDateEndedMonth(){
        return this.e_month;
    }
        
    public int getDateEndedYear(){
        return this.e_year;
    }
    
    public void setFinancier(String financier){
        this.financier = financier;
    }
    
    public String getFinancier(){
        return this.financier;
    }
    
    public void setValue(Double value){
        this.value = value;
    }
    
    public Double getValue(){
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
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public int getStatus(){
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
