public class Employee {

   private int empId;
   private String empName;
   private String startDate;
   private String accountNum;
   private String position;
   
   public Employee(){
      empId = 0;
      empName = "";
      startDate = "";
      accountNum = "";
      position = "";
   }
   
   public Employee(int empId, String empName, String startDate, String accountNum, String position) {
      this.empId = empId;
      this.empName = empName;
      this.startDate = startDate;
      this.accountNum = accountNum;
      this.position = position;
   }
   
   public Employee(Employee employee){
      this.empId = employee.empId;
      this.empName = employee.empName;
      this.startDate = employee.startDate;
      this.accountNum = employee.accountNum;
      this.position = employee.position;
   }
   
   public void setEmpId(int empId){
      this.empId = empId;
   }
   
   public void setEmpName(String empName){
      this.empName = empName;
   }
   
   public void setStartDate(String startDate){
      this.startDate = startDate;
   }
   
   public void setAccountNum(String accountNum){
      this.accountNum = accountNum;
   }
   
   public void setPosition(String position){
      this.position = position;
   }
   
   public int getEmpId(){
      return empId;
   }
   
   public String getEmpName(){
      return empName;
   }
   
   public String getStartDate(){
      return startDate;
   }
   
   public String getAccountNum(){
      return accountNum;
   }
   
   public String getPosition(){
      return position;
   }
      
   public String getEmpInfo(){
      return "ID: " + empId + " | Name: " + empName + " | Start Date: " + startDate + " | Account Number: " + accountNum + " | Position: " + position;
   }
} 