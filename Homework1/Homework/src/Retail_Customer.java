public class Retail_Customer extends Customer {
    Retail_Customer(){
        super();
    }
    Retail_Customer(String name, String surname, String address, String phone, int ID,int _operator_ID){
        super(name,surname,address,phone,ID,_operator_ID);
    }
    @Override
    String getCustomerType(){
        return "Retail Customer";
    }
}
