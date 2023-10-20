public class Huvudprogram {


    public static void main(String[] args) {

        Member newMember = new Member(); //newMember skapas upp
        String input = newMember.Reception(); //newMember kör metoden Reception för att fånga input.
        newMember.SearchCustomerArray(input); //newMember kör metoden SearchCustomerArray, med input från Reception för att söka i listan över medlemmar.

    }
}
