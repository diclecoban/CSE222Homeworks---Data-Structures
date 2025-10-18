
public class Customer extends Person {
    protected Order[] orders;
    private final int operator_ID;
    Customer(){
        super();
        operator_ID = 0;
        orders = new Order[100];
    }
    Customer(String name, String surname, String address, String phone, int customer_ID,int _operator_ID){
        super(name,surname,address,phone,customer_ID);
        operator_ID = _operator_ID;
        orders = new Order[100];
    }
    int getOperator_ID(){
        return operator_ID;
    }
    String getCustomerType(){
        return "";
    }
    public void print_customer(){
        System.out.println("----------------**----------------");
        System.out.println("Customer Name: " +getName());
        System.out.println("Customer Surname: " +getSurname());
        System.out.println("Customer address: " +getAddress());
        System.out.println("Operator phone: " +getPhone());
        System.out.println("Customer ID: " +getID());
        System.out.println("Customer Operators' ID: " +getOperator_ID());

    }
    public void addOrder(Order order) {
        if (orders == null || orders.length == 0) {
            orders = new Order[1];
            orders[0] = order;
        } else {
            Order[] newOrders = new Order[orders.length + 1];
            System.arraycopy(orders, 0, newOrders, 0, orders.length);
            newOrders[newOrders.length - 1] = order;
            orders = newOrders;
        }
    }
    public void define_orders(Order[] orders){
        for(Order order : orders){
            if(order != null && order.getCustomer_ID() == this.getID()){
                addOrder(order);
            }
        }
    }
    public void print_orders(){
        int orderNumber = 1;
        System.out.println("----------------**----------------");
        for (Order order : orders) {
            if (order != null) {
                System.out.println(this.getName()+"'s Orders:");
                System.out.print("Order #"+orderNumber++);
                order.print_order();
                System.out.println(" ");
            }
        }
    }
}
