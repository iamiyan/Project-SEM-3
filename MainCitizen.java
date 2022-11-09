import java.util.*;
import java.io.*;
import java.util.LinkedList;
import javax.swing.*;
import java.util.Queue;

public class MainCitizen {
    
    public static void main(String[] args){

        try{
            BufferedReader reader = new BufferedReader(new FileReader("citizen.txt"));

            //declaration of linkedList citizenList
            LinkedList<Citizen> citizenList = new LinkedList<>();
            //declaration of linkedList completedList
            LinkedList<Citizen> completedList = new LinkedList<>();

            //declaration of linkedList for first dose process for every category
            LinkedList<Citizen> firstDoseCategory1 = new LinkedList<>();
            LinkedList<Citizen> firstDoseCategory2 = new LinkedList<>();
            LinkedList<Citizen> firstDoseCategory3 = new LinkedList<>();

            Citizen c;
            String indata = null;

            while((indata = reader.readLine()) != null){
                StringTokenizer st = new StringTokenizer(indata, ";");
                String name = st.nextToken();
                String IC = st.nextToken();
                String state = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                String category = st.nextToken();
                String firstDoseStat = st.nextToken();
                String secondDoseStat = st.nextToken();
                String vaccineCompCert = st.nextToken();

                c = new Citizen( name,  IC,  state,  age,  category,  firstDoseStat, secondDoseStat,  vaccineCompCert);

                //add data from text file into citizenList
                citizenList.add(c);
            }
            reader.close();

            //declaration of Stack stCenter for every age category
            Stack<Citizen> stCenter1 = new Stack<>();
            Stack<Citizen> stCenter2 = new Stack<>();
            Stack<Citizen> stCenter3 = new Stack<>();

            //declaration of Queue qCenter for every age category
            Queue<Citizen> qCenter1 = new LinkedList<>();
            Queue<Citizen> qCenter2 = new LinkedList<>();
            Queue<Citizen> qCenter3 = new LinkedList<>();

            //declaration of main menu
            int menu = 0;
            do{

                //main menu
                menu = Integer.parseInt(JOptionPane.showInputDialog("Please choose the following menu  "+
                                                                "\n Menu 1 - Add new citizen data" +
                                                                "\n Menu 2 - Remove citizen data " + 
                                                                "\n Menu 3 - Demonstrate 1st Vaccination Process" +
                                                                "\n Menu 4 - Demonstrate 2nd Vaccination Process" + 
                                                                "\n Menu 5 - Exit"));

            if (menu == 1) {//add new citizen data

                    
                    String name = JOptionPane.showInputDialog("Please enter new citizen name");
                    while(name.equalsIgnoreCase("")) {
                        //show warning message if name is not entered
                        JOptionPane.showMessageDialog(null,"Please Enter a Name!","Warning",
                        JOptionPane.WARNING_MESSAGE);
                        name = JOptionPane.showInputDialog("Please enter new citizen name");
                    }


                    String IC = JOptionPane.showInputDialog("Please enter new citizen IC");
                    while(IC.equalsIgnoreCase("")){
                        //show warning message if IC is not entered
                        JOptionPane.showMessageDialog(null,"Please Enter valid IC number!","Warning",
                         JOptionPane.WARNING_MESSAGE);
                         IC = JOptionPane.showInputDialog("Please enter new citizen IC");
                    }


                    String state = JOptionPane.showInputDialog("Please enter new citizen state");
                    while(state.equalsIgnoreCase("")){
                        //show warning message if state is not entered
                        JOptionPane.showMessageDialog(null,"Please Enter a State!","Warning",
                         JOptionPane.WARNING_MESSAGE);
                         state = JOptionPane.showInputDialog("Please enter new citizen state");
                    }


                    int age = Integer.parseInt(JOptionPane.showInputDialog("Please enter new citizen age"));
                    while (age < 18) {
                        //show warning message when input is negative
                        JOptionPane.showMessageDialog(null, "Age entered is not eligible for vaccination!", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                                age = Integer.parseInt(JOptionPane.showInputDialog("Please enter new citizen age"));
                    } 


                    String category = JOptionPane.showInputDialog("Please enter new citizen category");
                    while(state.equalsIgnoreCase("")){
                        //show warning message if category is not entered
                        JOptionPane.showMessageDialog(null,"Please Enter a Category between High risk or Low risk!","Warning",
                         JOptionPane.WARNING_MESSAGE);
                         state = JOptionPane.showInputDialog("Please enter new citizen category");
                    }

                    String firstDoseStat = "null";
                    String secondDoseStat = "null";
                    String vaccineCompCert = "null";

                    c = new Citizen(name, IC, state, age, category,  firstDoseStat , secondDoseStat, vaccineCompCert);

                    //add new citizen data into citizenList
                    citizenList.add(c);
                }    

            if (menu == 2){//remove citizen data

                String numIC = JOptionPane.showInputDialog("Enter citizen IC number");

                for (int i = 0; i < citizenList.size(); i++){
                    c = citizenList.get(i); 
                    //find citizen with same IC number from citizenList
                    if(c.getIC().equalsIgnoreCase(numIC)){
                        citizenList.remove(c);
                    }
                }
            }

            if (menu == 3){

                //inserting data from citizenList to stack according to age category
                while(!citizenList.isEmpty()){
                    c = citizenList.removeLast();
                    if(c.getAge() >= 18 && c.getAge() <= 30){
                        stCenter1.push(c);
                    }
                    else if(c.getAge() >= 31 && c.getAge() <= 49){
                        stCenter2.push(c);
                    }
                    else if(c.getAge() >= 50){
                        stCenter3.push(c);
                    }
                }
                
                //process for 1st dose from null to complete for citizen age 18-30
                while(!stCenter1.isEmpty()){
                c = stCenter1.pop();
                    if(c.getFirstDoseStat().equalsIgnoreCase("null")){
                        c.setFirstDoseStat("COMPLETED");
                        qCenter1.add(c);//add data into queue for citizen age 18-30
                        firstDoseCategory1.add(c);//add data into linkedList firstDose for citizen age 18-30
                    }
                }
                
                //process for 1st dose from null to complete for citizen age 31-49
                while(!stCenter2.isEmpty()){
                c = stCenter2.pop();
                    if(c.getFirstDoseStat().equalsIgnoreCase("null")){
                        c.setFirstDoseStat("COMPLETED");
                        qCenter2.add(c);//add data into queue for citizen age 31-49
                        firstDoseCategory2.add(c);//add data into linkedList firstDose for citizen age 31-49
                    }
                }
    
                //process for 3rd dose from null to complete for citizen age 50 and above
                while(!stCenter3.isEmpty()){
                c = stCenter3.pop();
                    if(c.getFirstDoseStat().equalsIgnoreCase("null")){
                        c.setFirstDoseStat("COMPLETED");
                        qCenter3.add(c);//add data into queue for citizen age 50 and above
                        firstDoseCategory3.add(c);//add data into linkedList firstDose for citizen age 50 and above               
                    }
                }

                //declaration of submenu to display 1st dose vaccination process
                int nextMenu = 0;
                do{
    
                    nextMenu = Integer.parseInt(JOptionPane.showInputDialog(" Menu 1 - Display 1st Dose Recipient" +
                                                                            "\n Menu 2 - Exit"));
                
                if(nextMenu == 1){

                    //display 1st dose Recipient of age 18 - 30
                    System.out.println("\n\nFirst Dose Vaccination Process for 1st Category Recipient :");
                    System.out.println((String.format("\t________________________________________________________________________________________________________________________________________________________________")));
                    System.out.print((String.format("\t| %30s | %18s | %23s | %5s | %10s | %15s | %15s | %19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                    System.out.print(firstDoseCategory1.toString().replace("[","").replace("]","").replace(',',' '));
                    System.out.println((String.format("\t|______________________________________________________________________________________________________________________________________________________________|" + "\n")));
                    
                    //display 1st dose Recipient of age 31 - 49
                    System.out.println("\n\nFirst Dose Vaccination Process for 2nd Category Recipient :");
                    System.out.print((String.format("\t________________________________________________________________________________________________________________________________________________________________")));
                    System.out.print((String.format("\n\t| %30s | %18s | %23s | %5s | %10s | %15s | %15s | %19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                    System.out.print(firstDoseCategory2.toString().replace("[","").replace("]","").replace(',',' '));
                    System.out.println((String.format("\t|______________________________________________________________________________________________________________________________________________________________|" + "\n")));
    
                    //display 1st dose Recipient of age 50 and above
                    System.out.println("\n\nFirst Dose Vaccination Process for 3rd Category Recipient :");
                    System.out.print((String.format("\t________________________________________________________________________________________________________________________________________________________________")));
                    System.out.print((String.format("\n\t| %30s | %18s | %23s | %5s | %10s | %15s | %15s | %19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                    System.out.print(firstDoseCategory3.toString().replace("[","").replace("]","").replace(',',' '));
                    System.out.println((String.format("\t|______________________________________________________________________________________________________________________________________________________________|" + "\n\n")));
                    
                }
                }while(nextMenu != 2);
            }
        
            if (menu == 4){
                        
                //process 2nd vaccination process and insert data into completedList for citizen age 18-30
                while (! qCenter1.isEmpty()){
                    c = qCenter1.remove();
                    if(c.getSecondDoseStat().equalsIgnoreCase("null")){
                        c.setSecondDoseStat("COMPLETED");
                        c.setVaccineCompCert("COMPLETED");
                        completedList.add(c);
                    }
                }

                //process 2nd vaccination process and insert data into completedList for citizen age 31-49
                while (! qCenter2.isEmpty()){
                    c =  qCenter2.remove();
                    if(c.getSecondDoseStat().equalsIgnoreCase("null")){
                        c.setSecondDoseStat("COMPLETED");
                        c.setVaccineCompCert("COMPLETED");
                        completedList.add(c);
                    }
                }

                //process 2nd vaccination process and insert data into completedList for citizen age 50 and above
                while (! qCenter3.isEmpty()){
                    c = qCenter3.remove();
                    if(c.getSecondDoseStat().equalsIgnoreCase("null")){
                        c.setSecondDoseStat("COMPLETED");
                        c.setVaccineCompCert("COMPLETED");
                        completedList.add(c);
                    }
                }

            //declaration of submenu to display completed dose Recipient
            int nextMenu = 0;
            do{

                nextMenu = Integer.parseInt(JOptionPane.showInputDialog(" Menu 1 - Display Completed Dose Recipient" + 
                                                                        "\n Menu 2 - Exit"));
                                                                        
            if(nextMenu == 1){
                //display completed dose and completed vaccination certificate citizen data
                System.out.println("\n\nListing All Completed Dose Recipients : ");
                System.out.println((String.format("\t________________________________________________________________________________________________________________________________________________________________")));
                System.out.print((String.format("\t| %30s | %18s | %23s | %5s | %10s | %15s | %15s | %19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                System.out.print(completedList.toString().replace("[","").replace("]","").replace(',',' '));
                System.out.println((String.format("\t|______________________________________________________________________________________________________________________________________________________________|")));
                }

            }while(nextMenu != 2);
        }
        
            }while (menu != 5);   
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}