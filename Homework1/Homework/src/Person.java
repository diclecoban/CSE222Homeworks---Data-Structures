
public abstract class Person {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final int ID;
    public Person(){
        name = "";
        surname = "";
        address = "";
        phone = "";
        ID = 0;

    }
    public Person(String _name, String _surname, String _address, String _phone, int _ID ){
        name = _name;
        surname = _surname;
        address = _address;
        phone = _phone;
        ID = _ID;
    }
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getAddress(){return address;}
    public String getPhone(){return phone;}
    public int getID(){return ID;}
}
