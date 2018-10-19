/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;

/**
 *
 * @author Thiago
 */
public class MainClass {
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
    
    public static void consultCollaborator(Collaborator collaborator){
        
        System.out.println(" --- COLLABORATOR INFORMATION ---");
        System.out.println("- Name: " + collaborator.getName());
        System.out.println("- Email: " + collaborator.getEmail());
        
        if(collaborator instanceof Student){
            if(((Student) collaborator).getType() == 1)     System.out.println("- Occupation: Graduating Student");
            else if(((Student) collaborator).getType() == 2)    System.out.println("- Occupation: Master Student");
            else if(((Student) collaborator).getType() == 3)    System.out.println("- Occupation: Doctorate Student");
        }
        else if(collaborator instanceof Professor){
            System.out.println("- Occupation: Professor");
            System.out.println("- Orientation Projects: ");
            for(Orientation o : collaborator.getOrientation()) {
                System.out.println("Title: " + o.getName());
                for(Student s : o.getParticipant()) {
                    System.out.println("Student: " + s.getName());
                }
            }
        }
        else if(collaborator instanceof Researcher) {
            System.out.println("- Ocupation: Researcher");            
        }
        
        System.out.println("- Projects: ");
        for(Project p : collaborator.getProject()) {
            System.out.println("" + p.getName());
        }
        
        System.out.println("- Publications: ");
        sortPublication(collaborator.getPublication());
        
    }
    
    public static void consultProject(Project project){
        System.out.println("--- PROJECT INFORMATION ---");
        
        System.out.println("- Name: " + project.getName());
        System.out.println("- Date of Start" + project.getDateBegunDay() + "/" + project.getDateBegunMonth() + "/" 
                + project.getDateBegunYear());
        
        if(project.getStatus() == 1)    System.out.println("Status: Being developed...");    
        else if(project.getStatus() == 2)   System.out.println("Status: In progress...");
        
    }   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
