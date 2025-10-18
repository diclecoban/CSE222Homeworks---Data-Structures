import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Operator[] operators = new Operator[100];
        Order[] orders = new Order[100];
        Customer[] corporateCustomers = new Corporate_Customer[100];
        Customer[] retailCustomers = new Retail_Customer[100];
        int objectNumbers = 0;
        try {
            File file = new File("/Users/diclesaracoban/Desktop/Homework1/content.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] features = line.split(";", -1);
                int hasError = 0;
                int isDuplicate = 0;
                    switch (features[0]){
                        case "order":
                            try {
                                String name = features[1];
                                int count = Integer.parseInt(features[2]);
                                int total_price = Integer.parseInt(features[3]);
                                int status = Integer.parseInt(features[4]);
                                int or_ID = Integer.parseInt(features[5]);
                                if (count <= 0 || total_price <= 0 || status < 0 || or_ID < 0 ||
                                        count >= Integer.MAX_VALUE || total_price >= Integer.MAX_VALUE ||
                                        status >= Integer.MAX_VALUE || or_ID >= Integer.MAX_VALUE ||
                                        line.indexOf(';') == -1 || line.isEmpty() ||
                                        features.length != 6) {
                                    hasError = 1;
                                } else {
                                    orders[objectNumbers++] = new Order(name, count, total_price, status, or_ID);
                                }
                            } catch(Exception e){
                                if(hasError == 1){break;}
                            }
                            break;
                        case "operator":
                            try {
                                String op_name = features[1];
                                String surname = features[2];
                                String address = features[3];
                                String phone = features[4];
                                int op_ID = Integer.parseInt(features[5]);
                                int wage = Integer.parseInt(features[6]);
                                for (Operator operator : operators) {
                                    if (operator != null && operator.getID() == op_ID) {
                                        isDuplicate = 1;
                                    }
                                }
                                if (wage <= 0 || op_ID <= 0 ||
                                        op_ID >= Integer.MAX_VALUE || wage >= Integer.MAX_VALUE ||
                                         line.indexOf(';') == -1 || line.isEmpty() || isDuplicate == 1 ||
                                        features.length != 7) {
                                    hasError = 1;
                                } else {
                                    operators[objectNumbers++] = new Operator(op_name, surname, address, phone, op_ID, wage);
                                }
                            } catch(Exception e){
                                if(hasError == 1){break;}
                            }
                            break;
                        case "corporate_customer":
                            try {
                                String cus_name = features[1];
                                String cus_surname = features[2];
                                String cus_address = features[3];
                                String cus_phone = features[4];
                                int cor_ID = Integer.parseInt(features[5]);
                                int cor_op_ID = Integer.parseInt(features[6]);
                                String company_name = features[7];
                                for (Customer customer : corporateCustomers) {
                                    if (customer != null && customer.getID() == cor_ID) {
                                        isDuplicate = 1;
                                    }
                                }
                                if (cor_ID <= 0 || cor_op_ID <= 0 ||
                                        cor_ID >= Integer.MAX_VALUE || cor_op_ID >= Integer.MAX_VALUE ||
                                        line.indexOf(';') == -1 || line.isEmpty() || features.length != 8 ||
                                        isDuplicate == 1) {
                                    hasError = 1;
                                } else {
                                    corporateCustomers[objectNumbers++] = new Corporate_Customer(cus_name, cus_surname, cus_address, cus_phone, cor_ID, cor_op_ID, company_name);
                                }
                            } catch (Exception e){
                                if(hasError == 1){break;}
                            }
                            break;
                        case "retail_customer":
                            try {
                                String retail_name = features[1];
                                String retail_surname = features[2];
                                String retail_address = features[3];
                                String retail_phone = features[4];
                                int retail_ID = Integer.parseInt(features[5]);
                                int retail_op_ID = Integer.parseInt(features[6]);
                                for (Customer customer : retailCustomers) {
                                    if (customer != null && customer.getID() == retail_ID) {
                                        isDuplicate = 1;
                                    }
                                }
                                if (retail_ID <= 0 || retail_op_ID <= 0 ||
                                        retail_ID >= Integer.MAX_VALUE || retail_op_ID >= Integer.MAX_VALUE ||
                                        line.indexOf(';') == -1 || line.isEmpty() || isDuplicate == 1 ||
                                        features.length != 7) {
                                    hasError = 1;
                                } else {
                                    retailCustomers[objectNumbers++] = new Retail_Customer(retail_name, retail_surname, retail_address, retail_phone, retail_ID, retail_op_ID);
                                }
                            } catch (Exception e){
                                if(hasError == 1){break;}
                            }
                            break;
                        default: //for unknown identifier
                            break;
                    }
            }
            scanner.close();
        }   catch (Exception e) {
            System.out.println("an Exception captured");
            }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an ID...");
        int ID = scanner.nextInt();
        int matchFound = 0;

        //Checking if the ID exists as operators' ID
        for (Operator operator : operators) {
            if (operator != null && operator.getID() == ID) {
                System.out.println("*** Operator Screen ***");
                operator.print_operator();
                operator.define_customers(retailCustomers);
                operator.define_customers(corporateCustomers);
                operator.print_customers();
                    for (Customer customer : retailCustomers) {
                        if (customer != null && customer.getOperator_ID() == ID) {
                            customer.define_orders(orders);
                            customer.print_orders();
                        }
                    }
                for (Customer customer : corporateCustomers) {
                    if (customer != null && customer.getOperator_ID() == ID) {
                        customer.define_orders(orders);
                        customer.print_orders();
                    }
                }
                matchFound = 1;
                break;
            }
        }

        //If ID doesnt match with operators it goes here
        for(Customer r_customer : retailCustomers) {
            if(r_customer != null && r_customer.getID() == ID){
                System.out.println("** CUSTOMER SCREEN **");
                r_customer.print_customer();
                r_customer.define_orders(orders);
                r_customer.print_orders();
                matchFound = 1;
            }
        }
        for(Customer c_customer : corporateCustomers){
            if(c_customer != null && c_customer.getID() == ID){
                System.out.println("** CUSTOMER SCREEN **");
                c_customer.print_customer();
                c_customer.define_orders(orders);
                c_customer.print_orders();
                matchFound = 1;
            }
        }
        if(matchFound != 1){System.out.println("No operator/customer found with ID "+ID+". Please try again.");}
    }
}

