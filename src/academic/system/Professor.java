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
public class Professor extends Collaborator {
    private LinkedList <Collaborator> oriented;
    
    public Professor(){
        oriented = new LinkedList<Collaborator>();
    }
    
    public LinkedList <Collaborator> getOrientateds(){
        return oriented;
    }
    
    public void setOrientated(Collaborator student){
        oriented.add(student);
    }
    
    
}
