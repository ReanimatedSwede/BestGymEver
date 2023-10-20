import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Person {

    protected String socSecNumber;
    protected String name;

    protected String date;


    public Person(String socSecNumber, String namn, String date) {
        this.socSecNumber = socSecNumber;
        this.name = namn;
        this.date = date;
    }

    public Person() {

    }

    public String getSocSecNumber() {
        return socSecNumber;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public static List<Person> ReadMemberList() {
            //Läser in customer.txt och sparar i en Arraylist med formatet personnummer, namn, datum för återläsning.

            String filePath = "src/Resources/Customer.txt";


            List<Person> customerList = new ArrayList<>();

            String tempLine;
            String[] customerDataPartsFirstLine;
            String customerDataPartsSecondLine = null;


            try (BufferedReader buffIn = new BufferedReader(new FileReader(filePath))) { //Skapar upp nu filereader som omfamnas av en bufferedReader för inläsning av hela raden.
                while (( tempLine = buffIn.readLine()) != null) {   // Läser så länge !=null i textfilen påträffas.


                    customerDataPartsFirstLine = tempLine.split(", ");  //Delar upp första raden som läses in i 2 delar och sparar i tillfällig array.
                    if((tempLine = buffIn.readLine()) != null){
                        customerDataPartsSecondLine = tempLine.trim(); //fortsätter sedan på nästa rad och delar istället där nästa rad förekommer.


                    }
                    Person k = new Person(customerDataPartsFirstLine[0].trim(),
                            customerDataPartsFirstLine[1].trim(),
                            customerDataPartsSecondLine);
                    customerList.add(k);
                    // objekt k av Person skapas upp och här läggs första 2 raderna i arrayen in som socSecNumber och name
                    // k sparas in i listan customerList.

                }
            } catch (Exception e) {     //felhantering
                e.printStackTrace();
            }
        return customerList; //returnerar listan
    }


}







