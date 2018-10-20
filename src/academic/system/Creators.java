/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;


import static academic.system.MainClass.printParticipants;
import static academic.system.MainClass.printProjects;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class Creators {
        public static Collaborator createCollaborator(){
        Scanner input = new Scanner(System.in);
        
        short occupation;
        
        System.out.println("-- NEW COLLABORATOR --");
        
        try{
            System.out.println("Name:");
            String name = input.nextLine();
            
            System.out.println("Email:");
            String email = input.nextLine();
            
            System.out.println("Ocupation:");
            System.out.println("(1) - Student");
            System.out.println("(2) - Professor");
            System.out.println("(3) - Researcher");
            occupation = input.nextShort();
            
            String c = input.nextLine();
            
            switch(occupation){
                case 1:
                    System.out.println(" -- ADDING NEW STUDENT --");
                    System.out.println("(1) - Bachelor");
                    System.out.println("(2) - Master");
                    System.out.println("(3) - Doctorate");
                    occupation = input.nextShort();
                    
                    switch(occupation){
                        case 1: 
                            return createGraduate(name, email);
                            
                        case 2:
                            return createMaster(name, email);
                            
                        case 3:
                            return createDoctor(name, email);
                            
                        default:
                            System.out.println("This command is invalid!");
                    }
                    
                case 2:
                    return createProfessor(name, email);
                    
                case 3:
                    return createResearcher(name, email);
                    
                default:
                    System.out.println("This command is invalid!");
                    
            }
                    
                    
        } catch(InputMismatchException e){
            System.out.println("This command is invalid! Please insert one of the correct characters (1, 2, 3, 4).");
            System.out.println("Press any key to continue.");
            
            input.nextLine();
            
            System.out.println("(1) - Back to collaborator menu.");
            System.out.println("(2) - Back to main menu.");
            String option = input.nextLine();
            if(option.equals("1")) createCollaborator();
            else return null;
        }
        return null;
    }

    public static Publication createPublication(LinkedList<Collaborator> _participantlist, LinkedList<Project> _projectlist){
        
        int id;
        int control;
        String option;

        Scanner input = new Scanner(System.in);
        
        Publication publication = new Publication();
        Collaborator participant;
        Project project;
        
        System.out.println("--- ADDING NEW PUBLICATION ---");
        
        try{
            System.out.println("Insert the publication title.");
            publication.setName(input.nextLine());
            
            System.out.println("Conference where it was published.");
            publication.setConference(input.nextLine());
            
            System.out.println("Insert the year when it was published");
            publication.setYear(input.nextInt());
            
            String dump = input.nextLine();
            
            System.out.println("Add the participants.");
            
            
            do{
                printParticipants(_participantlist);
                System.out.println("Participant ID:");
                id = input.nextInt();
                
                participant = _participantlist.get(id);
                participant.addPublication(publication);
                System.out.println("(1) Add other participant.");
                System.out.println("(2) Finished");
                control = input.nextInt();
                dump = input.nextLine();
            }while(control == 1);
            
            System.out.println("Is this publication related to a project?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            control = input.nextInt();
            if(control == 1){
                printProjects(_projectlist);
                System.out.println("Project ID:");
                id = input.nextInt();
                project = _projectlist.get(id);
                
                if(project.getStatus() == 2) {
                    project.addPublication(publication);
                    System.out.println("Your publication has been added.");
                } else System.out.println("This project is not in progress!");
            }
            
            
        }catch(InputMismatchException e){
            System.out.println("This command is invalid! Please insert one of the correct characters (1, 2).");        
            System.out.println("Press any key to continue.");
            
            input.nextLine();
            System.out.println("(1) - Back to publication menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) createPublication(_participantlist, _projectlist);
            else return null;
        }catch(IndexOutOfBoundsException e){
            System.out.println("You inserted an ID that doesn't exist!");        
            System.out.println("Press any key to continue.");

            input.nextLine();
            System.out.println("(1) - Back to publication menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) createPublication(_participantlist, _projectlist);
            else return null;
        }
        return publication;
    }
    
    public static Project createProject(LinkedList<Collaborator> _participantlist){
        
        Scanner input = new Scanner(System.in);
        
        Project project = new Project();
        String option;

        System.out.println("--- ADDING NEW PROJECT ---");
        
        try{
            int day, month, year;
            int id;
            System.out.println("Project Name: ");
            project.setName(input.nextLine());
            
            System.out.println("Initial Date: (dd mm aaaa) ");
            System.out.print("Day: ");
            day = input.nextInt();
            System.out.print("Month: ");
            month = input.nextInt();
            System.out.print("Year: ");
            year = input.nextInt();
            project.setDateBegun(day, month, year);
            
            String dump = input.nextLine();
            
            System.out.print("Funder Agency:");
            project.setFinancier(input.nextLine());

            System.out.print("Value:");
            project.setValue(input.nextDouble());
            
            dump = input.nextLine();

            System.out.println("Objectives of the project:");
            project.setObjective(input.nextLine());
            
            System.out.println("Description of the project:");
            project.setDescription(input.nextLine());
            
            System.out.println("Add professors.");
            for(Collaborator c : _participantlist){
                if(c instanceof Professor){
                    System.out.println("ID: " + _participantlist.indexOf(c) + " - " + c.getName());
                }
            }
            
            System.out.println("Inser the ID of the respective professor.");
            id = input.nextInt();
            
            while(!(_participantlist.get(id) instanceof Professor)){
                System.out.println("This ID is not from a professor. Please add a value ID.");
                id = input.nextInt();
            }
            
            Collaborator participant = _participantlist.get(id);
            
            project.addParticipant(participant);
            participant.addProject(project);
            
            System.out.println("The participant has been added.");
            System.out.println("The project" + project.getName() + "has been created.");
                
        }catch(IndexOutOfBoundsException e){
            System.out.println("You inserted an ID that doesn't exist!");        
            System.out.println("Press any key to continue.");

            input.nextLine();
            System.out.println("(1) - Back to project menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) createProject(_participantlist);
            else return null;
        }catch(InputMismatchException e){
            System.out.println("This command is invalid! Please insert one of the correct characters (1, 2).");        
            System.out.println("Press any key to continue.");
            
            input.nextLine();
            System.out.println("(1) - Back to project menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) createProject(_participantlist);
            else return null;            
        }
        
        return project;
    }
    
            public static void addOrientation(LinkedList <Collaborator> _collaboratorsList){
		
            Scanner input  = new Scanner(System.in);
            String option;
            int id;
            
            System.out.println("--- Professors");
            try{
                for(Collaborator c : _collaboratorsList){
                    if(c instanceof Professor){
                        System.out.println("ID: " + _collaboratorsList.indexOf(c) + " - " + c.getName());
                    }
                }
                System.out.println("---\n");

                System.out.println("Please insert the ID of the Professor:");
                id = input.nextInt();

                Collaborator professor = _collaboratorsList.get(id);
                if(!(professor instanceof Professor)){
                        System.out.println("Invalid Command!! The ID is not from a professor");
                        addOrientation(_collaboratorsList);
                }
                System.out.println("--- Students");

                for(Collaborator c : _collaboratorsList){
                        if(c instanceof Student){
                                System.out.println("ID: " + _collaboratorsList.indexOf(c) + " - " + c.getName());
                        }
                }
                System.out.println("---");

                System.out.println("Please insert the ID of the Student:");
                id = input.nextInt();
                Collaborator student = _collaboratorsList.get(id);
                if(!(student instanceof Student)){
                        System.out.println("Invalid Command!! The ID is not from an Student");
                        addOrientation(_collaboratorsList);
                }

                LinkedList <Collaborator> _orientatedList = ((Professor)professor).getOrientateds();
                _orientatedList.add(student);

                System.out.println(professor.getName() + " is Advising " + student.getName());
            }catch(IndexOutOfBoundsException e){
            System.out.println("You inserted an ID that doesn't exist!");        
            System.out.println("Press any key to continue.");

            input.nextLine();
            System.out.println("(1) - Back to orientation menu.");
            System.out.println("(2) - Back to main menu.");
            option = input.nextLine();
            if(option.equals("1")) addOrientation(_collaboratorsList);

            }catch(InputMismatchException e){
                System.out.println("This command is invalid! Please insert one of the correct characters (1, 2).");        
                System.out.println("Press any key to continue.");

                input.nextLine();
                System.out.println("(1) - Back to orientation menu.");
                System.out.println("(2) - Back to main menu.");
                option = input.nextLine();
                if(option.equals("1")) addOrientation(_collaboratorsList);
            }
        }
    
    public static Collaborator createGraduate(String name, String email){
        
        Student _gradStudent = new Student();
        _gradStudent.setName(name);
        _gradStudent.setEmail(email);
        _gradStudent.setType(1);
        
        return _gradStudent;
    }
    
    public static Collaborator createMaster(String name, String email){
        
        Student _masterStudent = new Student();
        _masterStudent.setName(name);
        _masterStudent.setEmail(email);
        _masterStudent.setType(2);
        
        return _masterStudent;
    }
    
    public static Collaborator createDoctor(String name, String email){
        
        Student _doctorStudent = new Student();
        _doctorStudent.setName(name);
        _doctorStudent.setEmail(email);
        _doctorStudent.setType(3);
        
        return _doctorStudent;
    }
    
    public static Collaborator createProfessor(String name, String email) {
        
        Professor _professor = new Professor();
        _professor.setName(name);
        _professor.setEmail(email);
        
        return _professor;
    }
    
    public static Collaborator createResearcher(String name, String email){
        
        Researcher _researcher = new Researcher();
        _researcher.setName(name);
        _researcher.setEmail(email);
        
        return _researcher;
    }
    
    
}

