public class Manager extends Employee{

   private int numEmployee;
   
   public Manager(){
      super();
      numEmployee = 0;
   }
   
   public Manager(int empId, String empName, String startDate, String accountNum, String position, int numEmployee) {
      super(empId, empName, startDate, accountNum, position);
      this.numEmployee = numEmployee;
   }
   
   public void setNumEmployee(int numEmployee){
      this.numEmployee = numEmployee;
   }
   
   public int getNumEmployee(){
      return numEmployee;
   }
   
   @Override
   public String getEmpInfo(){
      return super.getEmpInfo() + " (The Number of Employee: " + numEmployee + ")";
   }
} 