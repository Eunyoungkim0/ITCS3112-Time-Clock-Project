public class Work {

   private int workId;
   private String workYM;
   private int workWeek;
   private double workTime;
   private Employee employee;
   private boolean hasPayment;
   
   public Work(){
      workId = 0;
      workYM = "";
      workWeek = 0;
      workTime = 0.0;
      employee = new Employee();
      hasPayment = false;
   }
   
   public Work(int workId, String workYM, int workWeek, double workTime, Employee employee){
      this.workId = workId;
      this.workYM = workYM;
      this.workWeek = workWeek;
      this.workTime = workTime;
      this.employee = new Employee(employee);
      this.hasPayment = false;
   }
   
   public Work(Work work){
      this.workId = work.workId;
      this.workYM = work.workYM;
      this.workWeek = work.workWeek;
      this.workTime = work.workTime;
      this.employee = new Employee(work.employee);
      this.hasPayment = work.hasPayment;
   }
   
   public void setWorkId(int workId){
      this.workId = workId;
   }
   
   public void setWorkYM(String workYM){
      this.workYM = workYM;
   }
   
   public void setWorkWeek(int workWeek){
      this.workWeek = workWeek;
   }
   
   public void setWorkTime(double workTime){
      this.workTime = workTime;
   }
   
   public void setEmployee(Employee employee){
      this.employee = new Employee(employee);
   }
   
   public void setHasPayment(boolean hasPayment){
      this.hasPayment = hasPayment;
   }
   
   public int getWorkId(){
      return workId;
   }
   
   public String getWorkYM(){
      return workYM;
   }
   
   public int getWorkWeek(){
      return workWeek;
   }
   
   public double getWorkTime(){
      return workTime;
   }
   
   public Employee getEmployee(){
      return new Employee(employee);
   }
   
   public boolean getHasPayment(){
      return hasPayment;
   }
   
   public String getWorkInfo(){
      return "[Employee ID: " + employee.getEmpId() + ", Name: " + employee.getEmpName() + "]\nWork code: " + workId + " | Year/Month(week): " + workYM + "(" + workWeek + ") | Work Hour: " + workTime;
   }
   
   public int getEmpId(){
      return employee.getEmpId();
   }
   
  public String getEmpName(){
     return employee.getEmpName();
  }
   
   public void addWorkTime(double time){
      this.workTime += time;
   }
} 