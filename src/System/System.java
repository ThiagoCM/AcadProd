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
public class System {
    public static Student createGraduate(String name, String email){
        
        Student _gradStudent = new Student();
        _gradStudent.setName(name);
        _gradStudent.setEmail(email);
        _gradStudent.setType(1);
        
        return _gradStudent;
    }
    
    public static Student createMaster(String name, String email){
        
        Student _masterStudent = new Student();
        _masterStudent.setName(name);
        _masterStudent.setEmail(email);
        _masterStudent.setType(2);
        
        return _masterStudent;
    }
    
    public static Student createDoctor(String name, String email){
        
        Student _doctorStudent = new Student();
        _doctorStudent.setName(name);
        _doctorStudent.setEmail(email);
        _doctorStudent.setType(3);
        
        return _doctorStudent;
    }
    
    public static Professor createProfessor(String name, String email) {
        
        Professor _professor = new Professor();
        _professor.setName(name);
        _professor.setEmail(email);
        
        return _professor;
    }
    
    public static Researcher createResearcher(String name, String email){
        
        Researcher _researcher = new Researcher();
        _researcher.setName(name);
        _researcher.setEmail(email);
        
        return _researcher;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
