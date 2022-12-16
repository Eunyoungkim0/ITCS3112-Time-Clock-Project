import java.util.*;
import java.text.SimpleDateFormat;

public class Timeclock{

   // Create an array list to store objects. The ArrayList will hold Employee objects.
   static ArrayList<Employee> employee = new ArrayList<Employee>();
   static ArrayList<Work> work = new ArrayList<Work>();
   static ArrayList<Payment> payment = new ArrayList<Payment>();
   static ArrayList<PaymentDetail> paymentDetail = new ArrayList<PaymentDetail>();
      
   public static void main(String[] args){
      Scanner scnr = new Scanner(System.in);

      // Declare variables
      String empName, startDate, accountNum, position, workLocation, charPosition, workYM, eachWorkYM, temp;
      String input = "",
             result = "";
      int numEmployee, workWeek, eachWorkWeek, eachWorkEmpId, numArray;
      int empId = 0,
          numTotalEmployee = 0,
          workId = 0,
          numWorkList = 0,
          valLocation = -1,
          valLocation2 = -1,
          paymentId = 0,
          newPaymentId = 0,
          paymentDetailID = 0;
      double workTime;
      double hourlyWage = 0.0,
             payAmount = 0.0,
             totalWage = 0.0,
             balance = 0.0,
             totalPayment = 0.0;
      boolean flag = true,
              subFlag = true,
              hasData = false;
              
      ArrayList<Work> empUnpaidList = new ArrayList<Work>();
            
      do{         
         if(flag == true){
            System.out.println("\n[Main menu] Please choose between 1-4: ");
            System.out.println("\t1. Manage Employees");
            System.out.println("\t2. Manage Work Time");
            System.out.println("\t3. Manage Payment");
            System.out.println("\t4. Exit");
            System.out.print("==> Your choice: ");
            
            input = scnr.nextLine();
         }
         
         if(input.equals("1")){
            // Manage Employees
            flag = true;
            subFlag = true;
            do{
               if(subFlag == true){
                  // initialize
                  empId = 0;
                  numTotalEmployee = 0;
                  
                  System.out.println("\n[1) Manage Employees] Please choose: ");
                  System.out.println("\tA. Add an employee");
                  System.out.println("\tB. Edit an employee information");
                  System.out.println("\tC. Remove an employee information");
                  System.out.println("\tD. Employee list");
                  System.out.println("\tM. Back to the main menu");
                  System.out.print("==> Your choice: ");
                  input = scnr.nextLine();
               }
               
               if(input.equalsIgnoreCase("A")){
                  // Add an employee
                  subFlag = true;
                  System.out.print("\n[1-A](1/4) A name of new employee?");                  
                  empName = scnr.nextLine();
                  System.out.print("[1-A](2/4) Start date? ");                  
                  startDate = scnr.nextLine();
                  System.out.print("[1-A](3/4) Account Number? ");                  
                  accountNum = scnr.nextLine();
                  System.out.print("[1-A](4/4) Position? 1-Manager, 2-General :");          
                  position = scnr.nextLine();
  
                  empId = getEmpId();
                  
                  if(position.equals("1")){
                     System.out.print("[1-A](Manager) The number of Employee? ");
                     temp = scnr.nextLine();
                     
                     if(isNumeric(temp)){
                        numEmployee = Integer.parseInt(temp);
                     }else{
                        numEmployee = 0;
                     }
                     
                     Manager mng = new Manager(empId, empName, startDate, accountNum, getPosition(position), numEmployee);
                     employee.add(mng);
                  }else if(position.equals("2")){
                     System.out.print("[1-A](General) Work Location? ");          
                     workLocation = scnr.nextLine();
                     
                     General gnr = new General(empId, empName, startDate, accountNum, getPosition(position), workLocation);
                     employee.add(gnr);
                  }else{
                     Employee emp = new Employee(empId, empName, startDate, accountNum, getPosition(position));
                     employee.add(emp);
                  }
                  
                  valLocation = getEmpLocation(empId);
                  System.out.println("\n(*) Success!");
                  System.out.println("\n<Added employee>\n" + employee.get(valLocation).getEmpInfo());
                  
               }else if(input.equalsIgnoreCase("B")){
                  // Edit an employee information
                  subFlag = true;
                  valLocation = -1;
                  
                  System.out.print("\n[1-B](1/5) An employee ID? ");
                  do{
                     temp = scnr.nextLine();
                     if(!isNumeric(temp)){
                        System.out.print("\t(!) Please enter only number. An employee ID? ");
                     }
                  }while(!isNumeric(temp));                                      
                  empId = Integer.parseInt(temp);
                  valLocation = getEmpLocation(empId);
                  
                  if(valLocation == -1){
                     // Fail to search an employee with empId
                     System.out.println("(!) I'm sorry we don't have an employee with ID " + empId);
                  }else{
                     // Success to search an employee with empId
                     System.out.println("\n<Original information>\n" + employee.get(valLocation).getEmpInfo());
                     
                     System.out.println("\n[1-B] If you don't want to change information, just press enter."); 
                     System.out.print("[1-B](2/5) A name of the employee?");
                     empName = scnr.nextLine();
                     if(!empName.equals("")){
                        employee.get(valLocation).setEmpName(empName);
                     }
                     System.out.print("[1-B](3/5) Start date? ");        
                     startDate = scnr.nextLine();
                     if(!startDate.equals("")){
                        employee.get(valLocation).setStartDate(startDate);
                     }
                     System.out.print("[1-B](4/5) Account Number? ");    
                     accountNum = scnr.nextLine();
                     if(!accountNum.equals("")){
                        employee.get(valLocation).setAccountNum(accountNum);
                     }
                                          
                     System.out.println("(*) Success!");
                     System.out.println("\n<Changed Information>\n" + employee.get(valLocation).getEmpInfo());
                  }

               }else if(input.equalsIgnoreCase("C")){
                  // Remove an employee information
                  subFlag = true;
                  valLocation = -1;
                  
                  System.out.print("\n[1-C](1/1) An employee ID? ");
                  do{
                     temp = scnr.nextLine();
                     if(!isNumeric(temp)){
                        System.out.print("\t(!) Please enter only number. An employee ID? ");
                     }
                  }while(!isNumeric(temp));                                      
                  empId = Integer.parseInt(temp);
                        
                  valLocation = getEmpLocation(empId);
                  
                  if(valLocation == -1){
                     // Fail to search an employee with empId
                     System.out.println("(!) I'm sorry we don't have an employee with ID " + empId);
                  }else{
                     // Success to search an employee with empId
                     hasData = false;
                     
                     for(int i=0; i<work.size(); i++){
                        if(work.get(i).getEmpId() == empId && work.get(i).getHasPayment() == false){
                           hasData = true;
                           break;
                        }
                     }
                     
                     for(int i=0; i<payment.size(); i++){
                        if(payment.get(i).getEmpId() == empId && payment.get(i).getBalance() > 0){
                           hasData = true;
                           break;
                        }
                     }
                     
                     if(hasData == true){
                        System.out.println("(!) This employee has balance to get paid. Please pay first.");
                     }else{
                        System.out.println("\n<Original information>\n" + employee.get(valLocation).getEmpInfo());
                        employee.remove(valLocation);
                        System.out.println("(*) Success removing!");
                     }
                  }

               }else if(input.equalsIgnoreCase("D")){
                  // List of employees
                  subFlag = true;
                  numTotalEmployee = employee.size();
                  
                  if(numTotalEmployee == 0){
                     System.out.println("(!) We have 0 employee!");
                  }else{
                     System.out.println("\n<Employee List>");
                     for(int i=0; i<employee.size(); i++){
                        System.out.println((i+1) + ". " + employee.get(i).getEmpInfo());
                     }
                  }
                  
               }else if(input.equalsIgnoreCase("M")){
                  // Back to the main menu
                  subFlag = false;
               }else{
                  // When user doesn't select A-D/M
                  subFlag = false;
                  System.out.print("(!) Wrong selection. Plase select between A-D or M for the main menu: ");
                  input = scnr.nextLine();
               }            
            }while(!input.equalsIgnoreCase("M"));
                           
         }else if(input.equals("2")){
            flag = true;
            subFlag = true;
            // Manage work time
            do{
               if(subFlag == true){
                  // initialize
                  empId = 0;
                  numTotalEmployee = 0;
                  
                  System.out.println("\n[2) Manage Work Time] Please choose: ");
                  System.out.println("\tA. Add work time each employee");
                  System.out.println("\tB. Add work time all employee");
                  System.out.println("\tC. List of Work");
                  System.out.println("\tM. Back to the main menu");
                  System.out.print("==> Your choice: ");
                  input = scnr.nextLine();
               }
               
               if(input.equalsIgnoreCase("A")){
                  // Add work time each employee
                  subFlag = true;
                  valLocation = -1;
                  
                  if(employee.size() == 0){
                     System.out.println("(!) We have 0 employee!");
                  }else{
                     System.out.print("\n[2-A](1/4) An employee ID? ");
                     do{
                        temp = scnr.nextLine();
                        if(!isNumeric(temp)){
                           System.out.print("\t(!) Please enter only number. An employee ID? ");
                        }
                     }while(!isNumeric(temp));                                      
                     empId = Integer.parseInt(temp);
                     valLocation = getEmpLocation(empId);
                                       
                     if(valLocation == -1){
                        // Fail to search an employee with empId
                        System.out.println("(!) I'm sorry we don't have an employee with ID " + empId);
                     }else{
                        // Success to search an employee with empId  // employee.get(valLocation)
                        
                        System.out.print("\n[2-A](2/4) Work year and month?(YYYYMM) ");                  
                        workYM = scnr.nextLine();
                        System.out.print("[2-A](3/4) What week of the month? ");      
                        do{
                           temp = scnr.nextLine();
                           if(!isNumeric(temp)){
                              System.out.print("\t(!) Please enter only number. What week of the month? ");
                           }
                        }while(!isNumeric(temp));
                        workWeek = Integer.parseInt(temp);
                        
                        System.out.print("[2-A](4/4) How many hours? ");  
                        do{
                           temp = scnr.nextLine();
                           if(!isNumeric(temp)){
                              System.out.print("\t(!) Please enter only number. How many hours? ");
                           }
                        }while(!isNumeric(temp));                                      
                        workTime = Integer.parseInt(temp);

                        numWorkList = work.size();
                        hasData = false;
                        
                        if(numWorkList == 0){
                           Work wk = new Work(getWorkId(), workYM, workWeek, workTime, employee.get(valLocation));
                           work.add(wk);
                           System.out.println("\n<Added Work Info>\n" + wk.getWorkInfo());
                        }else{
                           for(int i=0; i<numWorkList; i++){
                              
                              eachWorkYM = work.get(i).getWorkYM();
                              eachWorkWeek = work.get(i).getWorkWeek();
                              eachWorkEmpId = work.get(i).getEmpId();
                              
                              if(eachWorkYM.equals(workYM) && eachWorkWeek == workWeek && eachWorkEmpId == empId){
                                 hasData = true;
                                 work.get(i).addWorkTime(workTime);
                                 
                                 for(int j=0; j<payment.size(); j++){
                                    if(payment.get(j).getWorkId() == work.get(i).getWorkId()){
                                       payment.get(j).setWork(work.get(i));
                                       payment.get(j).updatePayment(workTime);
                                    }
                                 }
                                 System.out.println("\n<Added Work Info>\n" + work.get(i).getWorkInfo());
                                 break;
                              }
                           }
                           
                           if(hasData == false){
                              Work wk = new Work(getWorkId(), workYM, workWeek, workTime, employee.get(valLocation));
                              work.add(wk);
                              System.out.println("\n<Added Work Info>\n" + wk.getWorkInfo());
                           }
                        }
                     }
                  }
                                    
               }else if(input.equalsIgnoreCase("B")){
                  // Add work time all employee
                  subFlag = true;
                                    
                  if(employee.size() == 0){
                     System.out.println("(!) We have 0 employee!");
                  }else{
                     System.out.print("\n[2-B](1/3) Work year and month?(YYYYMM) ");
                     workYM = scnr.nextLine();
                     System.out.print("[2-B](2/3) What week of the month? ");
                     do{
                        temp = scnr.nextLine();
                        if(!isNumeric(temp)){
                           System.out.print("\t(!) Please enter only number. What week of the month? ");
                        }
                     }while(!isNumeric(temp));
                     workWeek = Integer.parseInt(temp);              
                     
                     System.out.print("[2-B](3/3) How many hours? ");     
                     do{
                        temp = scnr.nextLine();
                        if(!isNumeric(temp)){
                           System.out.print("\t(!) Please enter only number. How many hours? ");
                        }
                     }while(!isNumeric(temp));
                     workTime = Integer.parseInt(temp);              
                     
                     int changedCount = 0;
                     int newCount = 0;
                     hasData = false;
                     
                     numWorkList = work.size();
                     for(int i=0; i<employee.size(); i++){
                    
                        for(int j=0; j<numWorkList; j++){
                           
                           eachWorkYM = work.get(j).getWorkYM();
                           eachWorkWeek = work.get(j).getWorkWeek();
                           eachWorkEmpId = work.get(j).getEmpId();
                           
                           if(eachWorkYM.equals(workYM) && eachWorkWeek == workWeek && eachWorkEmpId == employee.get(i).getEmpId()){
                              hasData = true;
                              work.get(j).addWorkTime(workTime);
                              changedCount += 1;
                           }
                        }
                        
                        if(hasData == false){
                           // Create Work class
                           Work wk = new Work(getWorkId(), workYM, workWeek, workTime, employee.get(i));
                           work.add(wk);
                           newCount += 1;
                        }
                        
                        hasData = false;
                     }
                     
                     System.out.println("\n(*) Success! Changed data: " + changedCount + ", New data: " + newCount);
                     
                  }
                                   
               }else if(input.equalsIgnoreCase("C")){
                  // List of Work
                  subFlag = true;
                  numArray = work.size();
                  if(numArray == 0){
                     System.out.println("(!) There is no work list.");
                  }else{
                     System.out.println("\n-----------------------");
                     System.out.println("<List of Work>");
                     for(int i=0; i<numArray; i++){
                        System.out.println((i+1) + ". " + work.get(i).getWorkInfo());
                     }
                     System.out.println("-----------------------");
                  }
                  
               }else if(input.equalsIgnoreCase("M")){
                  // Back to the main menu
                  subFlag = false;
               }else{
                  // When user doesn't select A-C/M
                  subFlag = false;
                  System.out.print("(!) Wrong selection. Plase select between A-C or M for the main menu: ");
                  input = scnr.nextLine();
               }     
               
            }while(!input.equalsIgnoreCase("M"));
            
         }else if(input.equals("3")){
            flag = true;
            subFlag = true;
            // Manage Payment
            do{
               if(subFlag == true){
                  // initialize
                  empId = 0;
                  numTotalEmployee = 0;
                  
                  System.out.println("\n[3) Manage Payment] Please choose: ");
                  System.out.println("\tA. Pay each employee");
                  System.out.println("\tB. Pay all employee");
                  System.out.println("\tC. List of All Payment");
                  System.out.println("\tD. Unpaid List");
                  System.out.println("\tM. Back to the main menu");
                  System.out.print("==> Your choice: ");
                  input = scnr.nextLine();
               }
               
               if(input.equalsIgnoreCase("A")){
                  // Pay each employee
                  subFlag = true;
                  
                  System.out.print("\n[3-A] An employee ID? ");
                  do{
                     temp = scnr.nextLine();
                     if(!isNumeric(temp)){
                        System.out.print("\t(!) Please enter only number. An employee ID? ");
                     }
                  }while(!isNumeric(temp));                                      
                  empId = Integer.parseInt(temp);
                  valLocation = getEmpLocation(empId);
                  
                  if(valLocation == -1){
                     // Fail to search an employee with empId
                     System.out.println("(!) I'm sorry we don't have an employee with ID " + empId);
                  }else{
                     // Success to search an employee with empId
                     empUnpaidList = getWorkList(empId);
                     numArray = empUnpaidList.size();
                     
                     if(numArray == 0){
                        System.out.println("(!) There is nothing left to pay for employee ID " + empId);
                     }else{
                        System.out.println("-----------------------");
                        System.out.println("\n<Unpaid work list for employee ID " + empId + ">");
                        ArrayList<Integer> empUnpaidWorkId = new ArrayList<Integer>();
                        
                        for(int i=0; i<numArray; i++){
                           empUnpaidWorkId.add(empUnpaidList.get(i).getWorkId());
                           System.out.println((i+1) + ". " + empUnpaidList.get(i).getWorkInfo());
                        }
                        System.out.println("--------------------");
                        
                        System.out.print("[3-A] Select Work code that you are going to pay: ");
                        do{
                           temp = scnr.nextLine();
                           if(!isNumeric(temp)){
                              System.out.print("\t(!) Please enter only number. Work code? ");
                           }
                        }while(!isNumeric(temp));
                        workId = Integer.parseInt(temp);   
                        hasData = false;
                        
                        do{  
                           for(int i=0; i<empUnpaidWorkId.size(); i++){
                              if(empUnpaidWorkId.get(i) == workId){
                                 hasData = true;
                              }
                           }
                           
                           if(hasData == false){
                              System.out.print("[3-A] (!) Wrong selection. Select Work code that you are going to pay: ");
                              do{
                                 temp = scnr.nextLine();
                                 if(!isNumeric(temp)){
                                    System.out.print("\t(!) Please enter only number. Work code? ");
                                 }
                              }while(!isNumeric(temp));
                              workId = Integer.parseInt(temp);
                           }
                        }while(hasData == false);                      
                        
                        paymentId = getPaymentInfo(workId);
                        
                        if(paymentId == 0){
                           // Create
                           System.out.print("[3-A] Hourly Wage: ");
                           do{
                              temp = scnr.nextLine();
                              if(!isNumeric(temp)){
                                 System.out.print("\t(!) Please enter only number. Hourly Wage? ");
                              }
                           }while(!isNumeric(temp));
                           hourlyWage = Double.parseDouble(temp);
                        }else{
                           // Update information
                           valLocation = getPaymentLocation(paymentId); // payment location
                           hourlyWage = payment.get(valLocation).getHourlyWage();
                        }
                        
                        // payEach(int workId, double hourlyWage, int paymentId, char flag)
                        totalPayment = payEach(workId, hourlyWage, paymentId, 'E');
                        System.out.println("(*) Total Payment Amount: " + totalPayment);
                        
                     }
                  }
                                    
               }else if(input.equalsIgnoreCase("B")){
                  // Pay all employee
                  subFlag = true;
                  empUnpaidList = getAllWorkList();
                  
                  if(empUnpaidList.size() == 0){
                     System.out.println("(!) There is no unpaid list.");
                  }else{
                     System.out.println(msgUnpaidList(empUnpaidList));
                     System.out.print("[3-B] Batch process for all of the above?(Y/N) ");
                     input = scnr.nextLine();
                     if(input.equalsIgnoreCase("Y")){
                        System.out.print("[3-B] Hourly Wage of new payment?(If it's already set, it won't change) ");
                        
                        do{
                           temp = scnr.nextLine();
                           if(!isNumeric(temp)){
                              System.out.print("\t(!) Please enter only number. Hourly Wage? ");
                           }
                        }while(!isNumeric(temp));
                        hourlyWage = Double.parseDouble(temp);
                        
                        totalPayment = payAll(empUnpaidList, hourlyWage);
                        System.out.println("\n(*) Success! Total Payment Amount: " + totalPayment);
                     }                     
                  }
                  
               }else if(input.equalsIgnoreCase("C")){
                  // List of All Payment
                  subFlag = true;
                  numArray = paymentDetail.size();
                  
                  if(numArray == 0){
                     System.out.println("(!) There is no payment list.");
                  }else{
                     System.out.println("-----------------------");
                     System.out.println("<List of All Payment>");
                     for(int i=0; i<numArray; i++){
                        System.out.println(paymentDetail.get(i).getPaymentDetailInfo());
                     }
                     System.out.println("-----------------------");
                  }
               }else if(input.equalsIgnoreCase("D")){
                  // Unpaid List
                  subFlag = true;
                  empUnpaidList = getAllWorkList();
                  
                  if(empUnpaidList.size() == 0){
                     System.out.println("(!) There is no unpaid list.");
                  }else{
                     System.out.println(msgUnpaidList(empUnpaidList));
                  }
                                    
               }else if(input.equalsIgnoreCase("M")){
                  // Back to the main menu
                  subFlag = false;
               }else{
                  // When user doesn't select A-D/M
                  subFlag = false;
                  System.out.print("(!) Wrong selection. Plase select between A-D or M for the main menu: ");
                  input = scnr.nextLine();
               }            
            }while(!input.equalsIgnoreCase("M"));

            
         }else if(input.equals("4")){
            // Exit
            flag = false;
            System.out.println("\nBye! Have a good day!");
            System.exit(0);
         }else{
            System.out.println("You didn't select between 1-4");
            flag = false;
            System.out.print("(!) Wrong selection. Plase select between 1-4: ");
            input = scnr.nextLine();
         }
         
      }while(!input.equals("4"));
   }
   
   
   
   
   
   /* The getEmpLocation method gets index of ArrayList employee with employee ID */
   public static int getEmpLocation(int empId){
      int valLocation = -1;
      for(int i=0; i<employee.size(); i++){
         if(employee.get(i).getEmpId() == empId){
            valLocation = i;
            break;
         }
      }
      
      return valLocation;
   }
   
   /* The getWorkLocation method gets index of ArrayList work paper with work ID */
   public static int getWorkLocation(int workId){
      int valLocation = -1;
      for(int i=0; i<work.size(); i++){
         if(work.get(i).getWorkId() == workId){
            valLocation = i;
            break;
         }
      }
      
      return valLocation;
   }
   
   /* The getPaymentLocation method gets index of ArrayList payment list with payment ID */
   public static int getPaymentLocation(int paymentId){
      int valLocation = -1;
      for(int i=0; i<payment.size(); i++){
         if(payment.get(i).getPaymentId() == paymentId){
            valLocation = i;
            break;
         }
      }
      
      return valLocation;
   }
   
   /* The getPaymentDetailLocation method gets index of ArrayList payment detail list with paymentDetail ID */
   public static int getPaymentDetailLocation(int paymentDetailId){
      int valLocation = -1;
      for(int i=0; i<paymentDetail.size(); i++){
         if(paymentDetail.get(i).getPaymentDetailId() == paymentDetailId){
            valLocation = i;
            break;
         }
      }
      
      return valLocation;
   }
   
   /* The getPosition method gets text of position value */
   public static String getPosition(String p){
      if(p.equals("1")){
         return "Manager";
      }else if(p.equals("2")){
         return "General";
      }else{
         return "N/A";
      }
   }
   
   /* The getEmpId method returns new employee ID */
   public static int getEmpId(){
      // Employee id = max(employee id) + 1
      int numTotalEmployee = employee.size();
      if(numTotalEmployee == 0){
         return 1;
      }else{
         return employee.get(numTotalEmployee-1).getEmpId() + 1;
      }
   }
     
   /* The getWorkId method returns new work ID */
   public static int getWorkId(){
      // Work id = max(work id) + 1
      int numWorkList = work.size();
      if(numWorkList == 0){
         return 1;
      }else{
         return work.get(numWorkList-1).getWorkId() + 1;
      }
   }
   
   /* The getPaymentId method returns new payment ID */
   public static int getPaymentId(){
      // payment id = max(payment id) + 1
      int numTotalPayment = payment.size();
      if(numTotalPayment == 0){
         return 1;
      }else{
         return payment.get(numTotalPayment-1).getPaymentId() + 1;
      }
   }
   
   /* The getPaymentDetailId method returns new payment Detail ID */
   public static int getPaymentDetailId(){
      // payment detail id = max(payment detail id) + 1
      int numTotalPaymentDetail = paymentDetail.size();
      if(numTotalPaymentDetail == 0){
         return 1;
      }else{
         return paymentDetail.get(numTotalPaymentDetail-1).getPaymentDetailId() + 1;
      }
   }
   
   /* The getPaymentInfo method returns payment ID if exists */
   public static int getPaymentInfo(int workId){
      int paymentId = 0;
      
      for(int i=0; i<payment.size(); i++){
         if(payment.get(i).getWorkId() == workId){
            paymentId = payment.get(i).getPaymentId();
         }
      }
      return paymentId;
   }
   
   /* The getWorkList method returns unpaid work list for each employee */
   public static ArrayList<Work> getWorkList(int empId){
      ArrayList<Work> empUnpaidList = new ArrayList<Work>();
      
      int numWorkList = work.size();
      int workId = 0;      
      String status = "";
      
      for(int i=0; i<numWorkList; i++){
         workId = work.get(i).getWorkId();
         
         if(work.get(i).getEmpId() == empId){
            if(work.get(i).getHasPayment() == false){
               empUnpaidList.add(work.get(i));
            }else{
               for(int j=0; j<payment.size(); j++){
                  status = payment.get(j).getStatus();
                  if(payment.get(j).getWorkId() == workId && (status.equals("P") || status.equals("U"))){
                     empUnpaidList.add(work.get(i));
                  }
               }
            }
         }
      }
      return empUnpaidList;
   }
   
   /* The getAllWorkList method returns unpaid work list for all employee */
   public static ArrayList<Work> getAllWorkList(){
      ArrayList<Work> empUnpaidList = new ArrayList<Work>();
      
      int numWorkList = work.size();  
      int workId = 0;
      String status = "";
          
      for(int i=0; i<numWorkList; i++){
         workId = work.get(i).getWorkId();
         
         if(work.get(i).getHasPayment() == false){
            empUnpaidList.add(work.get(i));
         }else{
            for(int j=0; j<payment.size(); j++){
               status = payment.get(j).getStatus();
               if(payment.get(j).getWorkId() == workId && (status.equals("P") || status.equals("U"))){
                  empUnpaidList.add(work.get(i));
               }
            }
         }
      }
      return empUnpaidList;      
   }
   
   /* The msgUnpaidList method returns msg for unpaid work list for all employee */
   public static String msgUnpaidList(ArrayList<Work> wk){
      int workId = 0;
      String msg = "\n-----------------\n<Unpaid List>";
      String status = "";
            
      for(int i=0; i<wk.size(); i++){
         workId = wk.get(i).getWorkId();
         if(wk.get(i).getHasPayment() == true){
            // When the work is partial paid
            for(int j=0; j<payment.size(); j++){
               status = payment.get(j).getStatus();
               if(payment.get(j).getWorkId() == workId && (status.equals("P") || status.equals("U"))){
                  msg += "\n" + (i+1) + ". empId: " + wk.get(i).getEmpId() + " | empName: " + wk.get(i).getEmpName();
                  msg += " | workId: " + wk.get(i).getWorkId() + " | workDate: " + wk.get(i).getWorkYM() + "(" + wk.get(i).getWorkWeek() + ") | workHour: " + wk.get(i).getWorkTime();
                  msg += " | hourlyWage: " + payment.get(j).getHourlyWage() + " | totalWage: " + payment.get(j).getTotalWage() + " | balance: " + payment.get(j).getBalance();
               }
            }
         }else{
            // When the work is unpaid
            msg += "\n" + (i+1) + ". empId: " + wk.get(i).getEmpId() + " | empName: " + wk.get(i).getEmpName();
            msg += " | workId: " + wk.get(i).getWorkId() + " | workDate: " + wk.get(i).getWorkYM() + "(" + wk.get(i).getWorkWeek() + ") | workHour: " + wk.get(i).getWorkTime();
         }
      }
      msg += "\n-----------------";
      return msg;
   }
   
   /* The payEach method pays each unpaid work */
   public static double payEach(int workId, double hourlyWage, int paymentId, char flag){
      Scanner scnr = new Scanner(System.in);
      String temp;
      int valLocation = -1,
          valLocation2 = -1,
          newPaymentId = 0,
          paymentDetailID = 0;
      double balance = 0.0,
             payAmount = 0.0,
             totalPayment = 0.0;
      boolean errorFlag = false;
          
      Date date = new Date();  
      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
      String strDate = formatter.format(date);  
                  
      if(paymentId == 0){
         // When the work is unpaid
         valLocation = getWorkLocation(workId); // work location
         // Create Payment class (int paymentId, double hourlyWage, Work work)
         paymentId = getPaymentId();
         Payment pm = new Payment(paymentId, hourlyWage, work.get(valLocation));
         payment.add(pm);
      }
      
      valLocation = getPaymentLocation(paymentId);
      balance = payment.get(valLocation).getBalance();
      
      if(flag == 'E'){
         // Paying each employee
         System.out.println("* Work Hour: " + payment.get(valLocation).getWorkTime() + ", Hourly Wage: " + payment.get(valLocation).getHourlyWage() + ", Balance: " + balance);
         System.out.print("* Pay Amount: ");
         do{
            temp = scnr.nextLine();
            if(!isNumeric(temp)){
               System.out.print("\t(!) Please enter only number. Pay Amount? ");
            }
         }while(!isNumeric(temp));
         payAmount = Double.parseDouble(temp);
         
         while(payAmount > balance){
            System.out.print("(!) Pay Amount cannot exceed balance. Pay Amount: ");
            do{
               temp = scnr.nextLine();
               if(!isNumeric(temp)){
                  System.out.print("\t(!) Please enter only number. Pay Amount? ");
               }
            }while(!isNumeric(temp));
            payAmount = Double.parseDouble(temp);
         }
         errorFlag = false;
      }else if(flag == 'B'){
         // Paying batch
         payAmount = payment.get(valLocation).getBalance();
         errorFlag = false;
      }else{
         errorFlag = true;
      }
      
      if(errorFlag == false){
         // Create PaymentDetail class (int paymentDetailId, String paymentDate, double paymentAmount, Payment payment)
         paymentDetailID = getPaymentDetailId();
         PaymentDetail pmd = new PaymentDetail(paymentDetailID, strDate, payAmount, payment.get(valLocation));
         paymentDetail.add(pmd);
         
         payment.get(valLocation).setBalance(balance-payAmount); // Set changed balance in payment object
         payment.get(valLocation).updateStatus();  // Set changed status in payment object  
         
         valLocation2 = getPaymentDetailLocation(paymentDetailID); // paymentDetail location             
         paymentDetail.get(valLocation2).setPayment(payment.get(valLocation)); // Set updated payment object in paymentDetail object
         
         if(flag == 'E'){
            System.out.println("(*) Success!\n-----------------------\n" + paymentDetail.get(valLocation2).getPaymentDetailInfo() + "\n-----------------------");
         }         
         totalPayment = paymentDetail.get(valLocation2).getPaymentAmount();
      }
      
      return totalPayment;
   }
   
   /* The payAll method pays all unpaid work */
   public static double payAll(ArrayList<Work> unpaidWork, double hourlyWage){
      int workId = 0;
      int paymentId = 0;
      double totalPayment = 0.0;
      String status = "";
            
      for(int i=0; i<unpaidWork.size(); i++){
         workId = unpaidWork.get(i).getWorkId();
         if(unpaidWork.get(i).getHasPayment() == true){
            // When the work is partial paid
            for(int j=0; j<payment.size(); j++){
               status = payment.get(j).getStatus();
               if(payment.get(j).getWorkId() == workId && (status.equals("P") || status.equals("U"))){
                  paymentId = payment.get(j).getPaymentId();
               }
            }
         }else{
            // When the work is unpaid
            paymentId = 0;
         }
         
         // payEach(int workId, double hourlyWage, int paymentId, char flag)
         totalPayment += payEach(workId, hourlyWage, paymentId, 'B');
      }
      return totalPayment;
   }
   
   /* The isNumeric method return if the value is numeric or not */
   public static boolean isNumeric(String str) { 
     try {  
       Double.parseDouble(str);  
       return true;
     } catch(NumberFormatException e){  
       return false;  
     }  
   }
}