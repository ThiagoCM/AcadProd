/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;

import static academic.system.Consults.consult;
import static academic.system.Creators.addOrientation;
import static academic.system.Creators.createCollaborator;
import static academic.system.Creators.createProject;
import static academic.system.Creators.createPublication;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class MainClass {
    
    public static void sortPublication(LinkedList<Publication> _publicationList){
        
        int[] _publicationAux = new int[_publicationList.size()];
        int i = 0, j = 0;
        int aux;
        
        for(Publication publication : _publicationList){
                _publicationAux[i] = publication.getYear();
                i++;
        }

        for(i = 0; i < _publicationList.size(); i++){
                for(j = i+1; j < _publicationList.size(); j++){
                        if(_publicationAux[i] < _publicationAux[j]){
                                aux = _publicationAux[i];
                                _publicationAux[i] = _publicationAux[j];
                                _publicationAux[j] = aux;
                        }
                }
        }

        for(i = 0; i < _publicationList.size(); i++){
                for(Publication publication : _publicationList){
                        if(_publicationAux[i] == publication.getYear()){
                                System.out.println("Date: " + publication.getYear() + " - " + publication.getName());
                        }
                }
        }
    }      
    
    public static void printParticipants(LinkedList <Collaborator> _collaboratorList){
        for(Collaborator c : _collaboratorList){
            if(c instanceof Student){
                System.out.print("ID: " + _collaboratorList.indexOf(c) + " - " + c.getName());
                if(((Student) c).getType() == 1)    System.out.println(" - Graduating Student");
                else if (((Student) c).getType() == 2)    System.out.println(" - Master Student");
                else if (((Student) c).getType() == 3)    System.out.println(" - Doctorate Student");
            }
            else if(c instanceof Professor){
                System.out.println("ID: " + _collaboratorList.indexOf(c) + " - " + c.getName() + " - Professor");
            }
            else if(c instanceof Researcher){
                System.out.println("ID: " + _collaboratorList.indexOf(c) + " - " + c.getName() + " - Researcher");
            }
        }
    }
    
    
    public static void printProjects(LinkedList <Project> _projectList){
        for(Project p : _projectList){
                System.out.print("ID: " + _projectList.indexOf(p) + " - " + p.getName() + " - ");
                if(p.getStatus() == 1){
                        System.out.println("In preparing...");
                }
                else if(p.getStatus() == 2){
                        System.out.println("... In progress ...");
                }
                else if(p.getStatus() == 3){
                        System.out.println("... Accomplished!");
                }
        }
    }
    
    
        public static void editProject(LinkedList <Collaborator> _collaboratorList, LinkedList <Project> _projectList){
		
            Scanner input  = new Scanner(System.in);

            System.out.println("--- Editing Project ---");
            String option, s;
            short d;
            int i, id;
            Project project;
            
            try{
                    do{
                        System.out.println("(1) - Allocate");
                        System.out.println("(2) - Project Status");
                        System.out.println("(0) - Back to the Main Menu");

                        d = input.nextShort();
                        s = input.nextLine();

                        switch(d){
                            case 0: 
                                break;

                            case 1:
                                System.out.println("- Editing Collaborators");
                                printParticipants(_collaboratorList);
                                System.out.println("Insert the ID of the Collaborator");
                                id = input.nextInt();
                                Collaborator collaborator = _collaboratorList.get(id);

                                if(collaborator instanceof Student && ((Student) collaborator).getType() == 1){
                                    if(((Student) collaborator).getNumberProjects() >= 2){
                                        System.out.println("Two projects are the maximum for a graduating student!");
                                        break;
                                    }
                                }

                                System.out.println(_collaboratorList.get(id) + " has been choosen!");
                                System.out.println("--- Available Projects");
                                printProjects(_projectList);
                                System.out.println("\nPlease insert the ID of the Project");

                                id = input.nextInt();

                                project = _projectList.get(id);

                                if(project.getStatus() != 1){
                                    System.out.println("The project already begun or ended!");
                                    break;
                                }

                                project.addParticipant(collaborator);
                                collaborator.addProject(project);

                                System.out.println(collaborator.getName() + " was allocated in " + project.getName());
                                break;

                            case 2:

                                System.out.println("- Editing Project");
                                printProjects(_projectList);
                                System.out.println("Please insert the ID of the Project");
                                id = input.nextInt();

                                project = _projectList.get(id);

                                System.out.println("Choose a new Status:");
                                System.out.println("(2) - In Progress");
                                System.out.println("(3) - Finished");
                                Short status = input.nextShort();

                                if(status == 2){
                                    project.setStatus(2);
                                }
                                else if(status == 3){
                                    LinkedList <Publication> _publicationList = project.getPublications();
                                    if(_publicationList.size() > 0){
                                        System.out.println("Please report the end date:");
                                        System.out.print("Day: ");
                                        int day = input.nextInt();
                                        System.out.print("Month: ");
                                        int month = input.nextInt();
                                        System.out.print("Year: ");
                                        int year = input.nextInt();
                                        project.setDateEnded(day, month, year);
                                        project.setStatus(3);
                                        System.out.println("Done!");
                                        }
                                        else{
                                            System.out.println("You need to have at least one publication to close a project!");
                                        }
                                }
                                else{
                                        System.out.println("Invalid Command!");
                                }
                                break;
                            default:
                                System.out.println("Invalid Command! Please try again");
                                break;
                        }
                        System.out.println("---");

                    } while(d != 0);

            }catch(IndexOutOfBoundsException e){
                System.out.println("You inserted an ID that doesn't exist!");        
                System.out.println("Press any key to continue.");

                input.nextLine();
                System.out.println("(1) - Back to editing menu.");
                System.out.println("(2) - Back to main menu.");
                option = input.nextLine();
                if(option.equals("1")) editProject(_collaboratorList, _projectList);
                
            }catch(InputMismatchException e){
                System.out.println("This command is invalid! Please insert one of the correct characters (1, 2).");        
                System.out.println("Press any key to continue.");

                input.nextLine();
                System.out.println("(1) - Back to editing menu.");
                System.out.println("(2) - Back to main menu.");
                option = input.nextLine();
                if(option.equals("1")) editProject(_collaboratorList, _projectList);
            }
        }
        

            

    
    public static void report(LinkedList <Collaborator> _collaboratorList, LinkedList <Project> _projectList, int _orientationNumber, int _publicationNumber){
        
        System.out.println("--- Report");
        int _graduationNumber = 0, _masterNumber = 0, _doctorateNumber = 0, _professorNumber = 0, _researcherNumber = 0;

        for(Collaborator c : _collaboratorList){
                if(c instanceof Student){
                    if(((Student) c).getType() == 1)        _graduationNumber++;
                    
                    else if(((Student) c).getType() == 2)   _masterNumber++;
                    
                    else if(((Student) c).getType() == 3)   _doctorateNumber++;  
                }
                else if(c instanceof Professor)     _professorNumber++;
                else if(c instanceof Researcher)      _researcherNumber++;
        }

        System.out.println("-- Collaborators:");

        System.out.println("Graduating Students: " + _graduationNumber);
        System.out.println("Master Students: " + _masterNumber);
        System.out.println("Doctorate Students: " + _doctorateNumber);
        System.out.println("Professors: " + _professorNumber);
        System.out.println("Researchers: " + _researcherNumber + "\n");

        System.out.println("Total of Collaborators: " + _collaboratorList.size());
        System.out.println("---");

        int _preparingNumber = 0, _progressNumber = 0, _concludedNumber = 0;

        for(Project p : _projectList){
                if(p.getStatus() == 1)      _preparingNumber++;
                
                else if(p.getStatus() == 2)     _progressNumber++;
                
                else if(p.getStatus() == 3)     _concludedNumber++;
        }

        System.out.println("-- Projects");
        System.out.println("Projects being prepared: " + _preparingNumber);
        System.out.println("Projects in progress: " + _progressNumber);
        System.out.println("Projects completed: " + _concludedNumber);
        System.out.println("\n # Total of Projects: " + _projectList.size());
        System.out.println("---");

        System.out.println("-- Academic Production");
        System.out.println("Orientations: " + _orientationNumber);
        System.out.println("Publications:  " + _publicationNumber);
        System.out.println("\n # Total of The Academic production:  " + (_orientationNumber + _publicationNumber));
        System.out.println("---");
		
    }

    public static void Main_Loop(){
		
        Scanner input  = new Scanner(System.in);

        LinkedList <Collaborator> _collaboratorList = new LinkedList <Collaborator>();
        LinkedList <Project> _projectList = new LinkedList <Project>();
        LinkedList <Publication> _publicationList = new LinkedList <Publication>();

        int option = 1; 
        short d;
        String dump;
        int _nOrientation = 0, _nPublication = 0;
        
        try{
            while(option != 0){
                System.out.println("--- Academic System ---");
                System.out.println("Insert the number of your action.");
                System.out.println("(1) - Open a new Project");
                System.out.println("(2) - Add a new Developer");
                System.out.println("(3) - Edit a Research Project");
                System.out.println("(4) - Publish");
                System.out.println("(5) - Orientation");
                System.out.println("(6) - Consult");
                System.out.println("(7) - Report");
                System.out.println("(0) - Exit\n");

                option = input.nextInt();
                dump = input.nextLine(); 
                System.out.println();
                    switch(option){
                        case 0: 
                            break;

                        case 1:
                            Project project = createProject(_collaboratorList);
                            if(project != null){
                                    _projectList.add(project);
                            }
                            break;

                        case 2: 
                            Collaborator participant = createCollaborator();
                            if(participant != null){
                                System.out.println(participant.getName() + " was add to our lab!");
                                _collaboratorList.add(participant);
                            }
                            break;

                        case 3:
                            editProject(_collaboratorList, _projectList);
                            break;

                        case 4: 
                            _publicationList.add(createPublication(_collaboratorList, _projectList));
                            _nPublication++;
                            break;

                        case 5:
                            addOrientation(_collaboratorList);
                            _nOrientation++;
                            break;

                        case 6:
                            consult(_collaboratorList, _projectList);
                            break;

                        case 7:
                            report(_collaboratorList, _projectList, _nOrientation, _nPublication);
                            break;

                        default:
                                System.out.println("Invalid Option! please try again\n");
                                System.out.println("Press any button to continue");

                                dump = input.nextLine();
                                break;
                    }
            }
        }catch(InputMismatchException e){
                System.out.println("This command is invalid! Please insert one of the correct characters (1, 2, 3, 4, 5, 6, 7).");        
                System.out.println("Press any key to continue.");

                input.nextLine();
                System.out.println("(1) - Back to main menu.");
                System.out.println("(0) - Exit.");
                String action = input.nextLine();
                if(action.equals("1")) Main_Loop();
            }
        }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main_Loop();
         System.out.println("GoodBye!");
    }
    
}
