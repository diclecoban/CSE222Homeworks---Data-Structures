
public class Corporate_Customer extends Customer {
    private final String company_name;
    Corporate_Customer(){
        super();
        company_name = "";
    }
    Corporate_Customer(String name, String surname, String address, String phone, int ID,int operator_ID, String _company_name){
        super(name, surname, address, phone, ID, operator_ID);
        company_name = _company_name;
    }
    @Override
    String getCustomerType(){
        return "Corporate Customer";
    }
    String getCompany_name(){
        return company_name;
    }
    @Override
    public void print_customer(){
        super.print_customer();
        System.out.println("Customer Company name: " +getCompany_name());
    }
}
