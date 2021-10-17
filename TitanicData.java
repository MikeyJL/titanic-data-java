/**
 * A helper to load, transform, and explore the titanic dataset from https://kaggle.com
 * @author Mikey Lau (https://mikeylau.uk)
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Loads the titanic.csv data and prompts the user for an input between 1 and 5.
 * 1: Get all names,
 * 2: Get number of survivors,
 * 3: Get male and females,
 * 4: Get age groups,
 * 5: Get age groups that survived.
 */
public class TitanicData {
    private ArrayList<String[]> dataset = new ArrayList<String[]>();

    /**
     * Inits and loads the data into class property.
     */
    public TitanicData () {
        try {
            File file = new File("titanic.csv");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                dataset.add(data.split(","));
            }
            dataset.remove(0);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @return vector<string> - all the names in the dataset. 
     */
    public ArrayList<String> names () {
        ArrayList<String> listOfNames = new ArrayList<String>();
        for (String[] row : dataset) listOfNames.add((row[3] + row[4]).replace("\"", ""));
        return listOfNames;
    }

    /**
     * @return string - the number of survivors.
     */
    public int survived () {
        int survived = 0;
        for (String[] row : dataset) survived += (row[1].equals("1")) ? 1 : 0;
        return survived;
   }

   /**
     * @return FemaleMale - the number of names and females.
     */
   public MaleFemale maleFemaleCount () {
       MaleFemale instance = new MaleFemale();
       for (String[] row : dataset) instance.males += row[5].equals("male") ? 1 : 0;
       instance.females = dataset.size() - instance.males;
       return instance;
   }

   /**
     * @return AgeGroup - age groups of children (<18), adults (18-64), and elders (>65).
     */
   public AgeGroup ageGroupCount () {
       AgeGroup instance = new AgeGroup();
       ArrayList<String[]> cleanData = new ArrayList<String[]>();
       for (String[] row : dataset) if (row[6] != "") cleanData.add(row);
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) < 18) instance.children += 1;
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) >= 18 && Float.parseFloat(row[6]) < 65) instance.adults += 1;
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) >= 65) instance.elders += 1;
       return instance;
   }

   /**
     * @return AgeGroup - age groups of children (<18), adults (18-64), and elders (>65) that survived.
     */
   public AgeGroup ageGroupSurvivedCount () {
       AgeGroup instance = new AgeGroup();
       ArrayList<String[]> cleanData = new ArrayList<String[]>();
       for (String[] row : dataset) if (row[6] != "") cleanData.add(row);
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) < 18 && row[1].equals("1")) instance.children += 1;
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) >= 18 && Float.parseFloat(row[6]) < 65 && row[1].equals("1")) instance.adults += 1;
       for (String[] row : cleanData) if (Float.parseFloat(row[6]) >= 65 && row[1].equals("1")) instance.elders += 1;
       return instance;
   }
}
