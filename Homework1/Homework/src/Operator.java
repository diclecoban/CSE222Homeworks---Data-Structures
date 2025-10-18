
public class Operator extends Person {
    private final int wage;
    private Customer[] customers;

    Operator(){
        super();
        wage = 0;
        customers = new Customer[100];
    }
    Operator(String _name, String _surname, String _address, String _phone, int _ID, int _wage){
        super(_name,_surname,_address,_phone,_ID);
        wage = _wage;
        customers = new Customer[100];
    }
    public int getWage(){return wage;}
    public void print_operator(){
       System.out.println("----------------**----------------");
        System.out.println("Operator Name: " +getName());
        System.out.println("Operator Surname: " +getSurname());
        System.out.println("Operator address: " +getAddress());
        System.out.println("Operator phone: " +getPhone());
        System.out.println("Operator ID: " +getID());
        System.out.println("Operator wage: " +getWage());
       System.out.println("----------------**----------------");
    }

    public void addCustomer(Customer customer) {
        if (customers == null || customers.length == 0) {
            customers = new Customer[1];
            customers[0] = customer;
        } else {
            Customer[] newCustomers = new Customer[customers.length + 1];
            System.arraycopy(customers, 0, newCustomers, 0, customers.length);
            newCustomers[newCustomers.length - 1] = customer;
            customers = newCustomers;
        }
    }
    public void print_customers() {
        int customerNumber = 1;
       for (Customer customer : customers) {
               if (customer != null) {
               System.out.print("Customer #" +customerNumber++);
               System.out.println(" (a "+customer.getCustomerType()+")");
               customer.print_customer();
               System.out.println(" ");
           }
       }
    }
    public void define_customers(Customer [] customers) {
        for(Customer customer : customers){
            if(customer != null && customer.getOperator_ID() == this.getID()){
                addCustomer(customer);
            }
        }
    }
}
