
public class Order {
 private final String product_name;
 private final int count;
 private final int total_price;
 private final int status;
 private final int customer_id;

 Order(String _product_name, int _count, int _total_price, int _status, int _customer_id) {
  product_name = _product_name;
  count = _count;
  total_price = _total_price;
  status = _status;
  customer_id = _customer_id;
 }

 Order(){
  product_name = "";
  count = 0;
  total_price = 0;
  status = 0;
  customer_id = 0;
 }

 public int getCustomer_ID(){return customer_id;}

 public int getCount(){return count;}

 public int getStatus(){return status;}

 public String getName(){return product_name;}

 public int getPrice(){return total_price;}
 public void print_order() {
  System.out.print(" => Product Name: " +getName());
  System.out.print(" - Count: " +getCount());
  System.out.print(" - Total price: " +getPrice());
  switch (this.getStatus()){
   case 0:
    System.out.print(" - Status: Initialized");
    break;
   case 1:
    System.out.print(" - Status: Processing");
    break;
   case 2:
    System.out.print(" - Status: Completed");
    break;
   case 3:
    System.out.print(" - Status: Canceled");
    break;
  }
 }
}
