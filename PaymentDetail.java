public class PaymentDetail {

   private int paymentDetailId;
   private String paymentDate;
   private double paymentAmount;
   private Payment payment;
   
   public PaymentDetail(){
      paymentDetailId = 0;
      paymentDate = "";
      paymentAmount = 0.0;
      payment = new Payment();
   }
   
   public PaymentDetail(int paymentDetailId, String paymentDate, double paymentAmount, Payment payment){
      this.paymentDetailId = paymentDetailId;
      this.paymentDate = paymentDate;
      this.paymentAmount = paymentAmount;
      this.payment = new Payment(payment);
   }
   
   public void setPaymentDetailId(int paymentDetailId){
      this.paymentDetailId = paymentDetailId;
   }
   
   public void setPaymentDate(String paymentDate){
      this.paymentDate = paymentDate;
   }
   
   public void setPaymentAmount(double paymentAmount){
      this.paymentAmount = paymentAmount;
   }
   
   public void setPayment(Payment payment){
      this.payment = new Payment(payment);
   }
   
   public int getPaymentDetailId(){
      return paymentDetailId;
   }
   
   public String getPaymentDate(){
      return paymentDate;
   }
   
   public double getPaymentAmount(){
      return paymentAmount;
   }
   
   public Payment getPayment(){
      return new Payment(payment);
   }
   
   public String getPaymentDetailInfo(){
      return payment.getPaymentInfo() + "\n\t - Payment Detail ID: " + paymentDetailId + " | Payment Date: " + paymentDate + " | Payment Amount: " + paymentAmount;
   }
} 