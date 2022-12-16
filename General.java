public class General extends Employee{

   private String workLocation;
   
   public General(){
      super();
      workLocation = "";
   }
   
   public General(int empId, String empName, String startDate, String accountNum, String position, String workLocation) {
      super(empId, empName, startDate, accountNum, position);
      this.workLocation = workLocation;
   }
   
   public void setWorkLocation(String workLocation){
      this.workLocation = workLocation;
   }
   
   public String getWorkLocation(){
      return workLocation;
   }
   
   @Override
   public String getEmpInfo(){
      return super.getEmpInfo() + " (Working Location: " + workLocation + ")";
   }
} 