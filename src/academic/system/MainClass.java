/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.system;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class MainClass {
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
        sortProject(collaborator.getProject());
        //for(Project p : collaborator.getProject()) {
         //   System.out.println("" + p.getName());
        //}
        
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
                } else System.out.println("This project is not in progress!");
            }
            
            System.out.println("Your publication has been added.");
            
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
        int day, month, year;
        int id;
        System.out.println("--- ADDING NEW PROJECT ---");
        
        try{
            
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
    
    public static void sortProject(LinkedList <Project> _projectList){
		int[] _projectAux = new int[_projectList.size()];
		int i = 0, j = 0;
		int aux;
                
                
		for(Project project : _projectList){
			_projectAux[i] = project.getDateEnded();
			i++;
		}

                for(i = 0; i < _projectList.size(); i++){
			for(j = i+1; j < _projectList.size(); j++){
				if(_projectAux[i] < _projectAux[j]){
					aux = _projectAux[i];
					_projectAux[i] = _projectAux[j];
					_projectAux[j] = aux;
				}
			}
		}
		
		for(i = 0; i < _projectList.size(); i++){
			for(Project project : _projectList){
				if(_projectAux[i] == project.getDateEnded()){
					System.out.println("Date: " + _projectAux[i] + " - " + project.getName());
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

                                System.out.println(_projectList.get(id) + " has been choosen!");
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
