public class Payment {

   private int paymentId;
   private double hourlyWage;
   private double totalWage;
   private double balance;
   private String status; //A: all paid, U: unpaid, P: partial paid 
   private Work work;
   
   public Payment(){
      paymentId = 0;
      hourlyWage = 0.0;
      totalWage = 0.0;
      balance = totalWage;
      status = "U";
      work = new Work();
   }
   
   public Payment(int paymentId, double hourlyWage, Work work) {
      this.paymentId = paymentId;
      this.hourlyWage = hourlyWage;
      this.status = "U";
      this.work = new Work(work);
      
      work.setHasPayment(true);
      calTotalWage();
      this.balance = totalWage;
   }
   
   public Payment(Payment payment){
      this.paymentId = payment.paymentId;
      this.hourlyWage = payment.hourlyWage;
      this.totalWage = payment.totalWage;
      this.balance = payment.balance;
      this.status = payment.status;
      this.work = new Work(payment.work);
      work.setHasPayment(true);
   }
   
   public void setPaymentId(int paymentId){
      this.paymentId = paymentId;
   }
   
   public void setHourlyWage(double hourlyWage){
      this.hourlyWage = hourlyWage;
   }
   
   public void setTotalWage(double totalWage){
      this.totalWage = totalWage;
   }
   
   public void setBalance(double balance){
      this.balance = balance;
   }
   
   public void setStatus(String status){
      this.status = status;
   }
   
   public void setWork(Work work){
      this.work = new Work(work);
      work.setHasPayment(true);
   }
   
   public int getPaymentId(){
      return paymentId;
   }
   
   public double getHourlyWage(){
      return hourlyWage;
   }
   
   public double getTotalWage(){
      return totalWage;
   }
   
   public double getBalance(){
      return balance;
   }
   
   public String getStatus(){
      return status;
   }
   
   public Work getWork(){
      return new Work(work);
   }
   
   public int getWorkId(){
      return work.getWorkId();
   }
   
   public double getWorkTime(){
      return work.getWorkTime();
   }
   
   public int getEmpId(){
      return work.getEmpId();
   }
   
   public void calTotalWage(){
      totalWage = hourlyWage * work.getWorkTime();
   }
   
   public void updatePayment(double workTime){
      this.totalWage += (workTime * hourlyWage);
      this.balance += (workTime * hourlyWage);
      updateStatus();
   }
   
   public void updateStatus(){
      if(balance == 0){ //A: all paid, U: unpaid, P: partial paid 
         status = "A";
      }else if(balance != totalWage){
         status = "P";
      }else{
         status = "U";
      }
   }
   
   public String getPaymentInfo(){
      return work.getWorkInfo() 
            + "\nPayment ID: " + paymentId + " | Hourly Wage: " + hourlyWage + "\nTotal Wage: " + totalWage + " | Balance: " + balance +  " | Status: " + status;
   }
} 