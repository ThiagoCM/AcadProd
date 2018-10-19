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
public class Orientation {
    
    // Attributes //
    private String name;
    private Professor orientator;
    private LinkedList <Student> participants;
    
    // Methods //
    //Constructor
    public void Orientation(){
        participants = new LinkedList<Student>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setOrientator(Professor orientator){
        this.orientator = orientator;
    }
    
    public Professor getOrientator(){
        return this.orientator;
    }
    
    public void addParticipant(Student participant){
        this.participants.add(participant);
    }
    
    public LinkedList<Student> getParticipant(){
        return this.participants;
    }
    
}
