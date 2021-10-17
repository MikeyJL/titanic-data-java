import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nPlease select one of the following options:\n\n[1] Display the names of all passengers\n[2] Display the number of passengers that survived\n[3] Display the number of passengers per gender\n[4] Display the number of passengers per age group\n[5] Display the number of survivors per age group\n\nSelect an option\n>>> ");
        int option = in.nextInt();
        in.close();
        System.out.println("\nYou've selected option: " + option + "\n");

        TitanicData instance = new TitanicData();
        switch (option) {
            case 1:
                for (String name : instance.names()) System.out.println(name);
                break;
            case 2:
                System.out.println("survived: " + instance.survived());
                break;
            case 3:
                MaleFemale mf = instance.maleFemaleCount();
                System.out.println("males: " + mf.males + " females: " + mf.females);
                break;
            case 4:
                AgeGroup age1 = instance.ageGroupCount();
                System.out.println("children: " + age1.children + " adults: " + age1.adults + " elders: " + age1.elders);
                break;
            case 5:
                AgeGroup age2 = instance.ageGroupCount();
                AgeGroup sAge = instance.ageGroupSurvivedCount(); 
                System.out.println("children: " + sAge.children + "/" + age2.children + " adults: " + sAge.adults + "/" + age2.adults + " elders: " + sAge.elders + "/" + age2.elders);
                break;
        }
    }
}