import java.io.*;
import java.util.*;

class ExtractData {
    static String fileName = "shuttle.trn";
    public static void main(String[] args) {
        
        String currentLine = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int entryCount = 0;
            int numberOfEntries = 43500;
            // Kind of cheating, since we know ahead of time that there are 43500 entries
            int[][] resultingArray = new int[10][numberOfEntries];
            while ((currentLine = br.readLine()) != null) {
                String[] valuesAsStrings = currentLine.split(" ");
                int valueCount = 0;
                for (String value : valuesAsStrings) {
                    resultingArray[valueCount][entryCount] = Integer.parseInt(value);
                    valueCount++;
                }
                entryCount++;
            }

            System.out.print("What column do we want information on: ");
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();

            // Now we have the integer values in an array, we can get the values we want
            ArrayList<Integer> columnAskedFor = new ArrayList<Integer>();
            int negNumNo = 0;
            int negNumYes = 0;
            int posNumNo = 0;
            int posNumYes = 0;

            for (int i = 0; i < numberOfEntries; i++) {
                int currentValue = resultingArray[number][i];
                int currentClass = resultingArray[9][i];
                if (!columnAskedFor.contains(currentValue)) {
                    columnAskedFor.add(currentValue);
                }
                if ((currentValue >= 0) && (currentClass == 1)) {
                    posNumYes++;
                } else if ((currentValue >= 0) && (currentClass != 1)) {
                    posNumNo++;
                } else if ((currentValue < 0) && (currentClass == 1)) {
                    negNumYes++;
                } else if ((currentValue < 0) && (currentClass != 1)) {
                    negNumNo++;
                }
            }

            System.out.println("negNumNo " + negNumNo);
            System.out.println("negNumYes " + negNumYes);
            System.out.println("posNumNo " + posNumNo);
            System.out.println("posNumYes " + posNumYes);

            System.out.println("Entropy of positive values: " + 
                    entropy(posNumYes, posNumNo));
            System.out.println("Entropy of negative values: " + 
                    entropy(negNumNo, negNumNo));

            Collections.sort(columnAskedFor);
            System.out.println(columnAskedFor.size());
            for (int i = 0; i < columnAskedFor.size(); i++) {
                System.out.print(columnAskedFor.get(i) + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double infoGain() {
        // TODO: Well, all of it
        return 0.0;
    }

    private static double entropy(int posVal, int negVal) {
        int total = posVal + negVal;
        double posRatio = (double)posVal/total;
        double negRatio = (double)negVal/total;
        return (-posRatio * base2Log(posRatio))
            - (negRatio * base2Log(negRatio));
    }
    private static double base2Log(double val) {
        return Math.log(val)/Math.log(2);
    }
}
