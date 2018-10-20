/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;

import static academic.system.MainClass.printParticipants;
import static academic.system.MainClass.printProjects;
import static academic.system.MainClass.sortPublication;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class Consults {
        public static void consult(LinkedList <Collaborator> _collaboratorList, LinkedList <Project> _projectList){
		
        Scanner input  = new Scanner(System.in);

        String s, option;
        Short d;
        int id;

        try{
            do{
                System.out.println("--- Consult");

                System.out.println("(1) - Developers");
                System.out.println("(2) - Projects");
                System.out.println("\n(0) - Back to menu");
                d = input.nextShort();
                s = input.nextLine();

                if(d == 1){
                    if(_collaboratorList.size() > 0){
                        System.out.println("--- Developers");
                        printParticipants(_collaboratorList);
                        System.out.println("---");

                        System.out.println("Choose Developer ID");
                        id = input.nextInt();

                        Collaborator participant = _collaboratorList.get(id);
                        consultCollaborator(participant);
                    }
                    else{
                        System.out.println("No Collaborators.");
                    }
                }
                else if(d == 2){

                        if(_projectList.size() > 0){
                                printProjects(_projectList);

                                System.out.println("Choose Project ID");
                                id = input.nextInt();

                                Project project = _projectList.get(id);
                                consultProject(project);
                        }
                        else{
                                System.out.println("No Projects available.");
                        }
                }
                else if(d != 0){
                        System.out.println("Error! Please insert a valid option: 1, 2 or 0");
                }
            }while(d != 0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("You inserted an ID that doesn't exist!");        
            System.out.println("Press any key to continue.");

            input.nextLine();
            System.out.println("(1) - Back to consult menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) consult(_collaboratorList, _projectList);

            }catch(InputMismatchException e){
                System.out.println("This command is invalid! Please insert one of the correct characters (1, 2).");        
                System.out.println("Press any key to continue.");

                input.nextLine();
                System.out.println("(1) - Back to consult menu.");
                System.out.println("(2) - Back to main menu.");
                option = input.nextLine();
                if(option.equals("1")) consult(_collaboratorList, _projectList);
            }
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
            for(Collaborator c : ((Professor) collaborator).getOrientateds()) {
                System.out.println("Student: " + c.getName());
            }
        }
        else if(collaborator instanceof Researcher) {
            System.out.println("- Ocupation: Researcher");            
        }
        
        System.out.println("- Projects: ");
        for(Project p : collaborator.getProject()){
            System.out.println("" + p.getDateBegunDay() + "/" + p.getDateBegunMonth() + "/" + p.getDateBegunYear() + " - " + p.getName());
        }
        
        System.out.println("- Publications: ");
        sortPublication(collaborator.getPublication());
        
    }
    
    public static void consultProject(Project project){
        System.out.println("--- PROJECT INFORMATION ---");
        
        System.out.println("- Name: " + project.getName());
        System.out.println("- Date of Start:" + project.getDateBegunDay() + "/" + project.getDateBegunMonth() + "/" 
                + project.getDateBegunYear());
        
        if(project.getStatus() == 1)    System.out.println("Status: Being developed...");    
        else if(project.getStatus() == 2)   System.out.println("Status: In progress...");
        else if(project.getStatus() == 3){
            System.out.println("Status: Finished.");
            System.out.println("- Date of Finish:" + project.getDateEndedDay() + "/" + project.getDateEndedMonth() + "/" 
                + project.getDateEndedYear());
        }
        
        System.out.println("- Funder: " + project.getFinancier());
        System.out.println("- Financed Value: " + project.getValue());
        System.out.println("- Objective: " + project.getObjective());
        System.out.println("- Description: " + project.getDescription());
        System.out.println("- Participants: ");
        
        LinkedList<Collaborator> participants = project.getParticipants();
        
        for(Collaborator c : participants) {
            System.out.println("" + c.getName() + ".");
        }
        System.out.println("- Publications: ");
        sortPublication(project.getPublications());   
    }
}
