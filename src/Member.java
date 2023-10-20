import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Member {

    protected String input;

    public Member(){}

    public void SearchCustomerArray(String userInput) {     //Tar input från userInput som skickats från Reception.
        List<Person> customerList = Person.ReadMemberList();   //Ny lista customerList skapas upp som hämtar värdet från Person.ReadMemberList och sparar i customerList
        this.input = userInput; //värdet på userInput läggs i lokal variabel.
        boolean matchFound = false;

        for (Person person : customerList) {
            if (input.contains(person.getSocSecNumber()) || input.contains(person.getName())) {
                matchFound = true;

                LocalDate ld1 = LocalDate.now(); //dagens datum
                LocalDate ld2 = LocalDate.parse(person.getDate()); //Datum som hämtats från filen customer.txt och som matchats mot träffen i sökningen.


                    long daysBetween = ld1.toEpochDay() - ld2.toEpochDay(); //Tar de båda datumena och tar ut dem mellan varandra i dagsform från Epoch-datumet 1 Jan 1970.
                    int daysInYear = 365; // räknar ej skottår, så lite utrymme för feltolkning

                    if(daysBetween < daysInYear){ //kontrollerar om daysBetween är mer eller mindre mot DaysInYear för att avgöra om medlemskapet är giltigt.
                        System.out.println("Välkommen "+ person.getName()+ ". Ditt medlemskap är: " + daysBetween + " dagar gammalt.");
                        WriteToFile( "Namn: " + person.getName() +", Personnummer: "+ person.getSocSecNumber() +", Datum för träning: " + ld1);

                    }
                    else{
                        System.out.println("Du är ej behörig då det är mer än 1 år sedan du förnyade ditt medlemskap. Medlemskapet är " + daysBetween + " dagar gammalt.");

                    }
                }
            }
            if(!matchFound){
            System.out.println("Du kunde ej hittas i systemet och är därför ej behörig.");

            }







    }

    public String Reception(){ //metod för att ta en userInput via Scanner(System.in)

        System.out.println("Skriv in ditt för- och efternamn eller ditt 10-siffriga personnummer:");
        Scanner scan = new Scanner(System.in);
        this.input = scan.nextLine().trim();
        return input;
    }


    public void WriteToFile(String tempLine){   //Metod för att skriva till fil. Körs inne i metoden SearchCustomerArray



        Path path = Paths.get("src/Resources/PTFile.txt"); //Slår upp sökvägen ditt filen som man ska skriva till ska sparas.

        try{ //Kollar om filen existerar och skapar upp om den INTE gör det.
            if(!Files.exists(path)){
                Files.createFile(path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){  //skapar upp filen och med StandardOpenOption.APPEND, berättar man att man vill lägga till och inte skriva över.

            bw.write(tempLine);//tar värdet av tempLine som skickats från SearchCustomerArray och skriver till filen
            bw.newLine();

        }

        catch (IOException e) { //Felhantering
           e.printStackTrace();
        }


    }



}
