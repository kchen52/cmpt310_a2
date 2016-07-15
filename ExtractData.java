import java.io.*;
import java.util.*;

class ExtractData {
    static String fileName = "shuttle.trn";
    public static void main(String[] args) {
        System.out.println(entropy(2,3));
        System.out.println("---");
        
        String currentLine = null;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int entryCount = 0;
            // Kind of cheating, since we know ahead of time that there are 43500 entries
            int[][] resultingArray = new int[10][43500];
            while ((currentLine = br.readLine()) != null) {
                String[] valuesAsStrings = currentLine.split(" ");
                int valueCount = 0;
                for (String value : valuesAsStrings) {
                    resultingArray[valueCount][entryCount] = Integer.parseInt(value);
                    valueCount++;
                }
                entryCount++;
            }

            // Now we have the integer values in an array, we can get the values we want
            ArrayList<Integer> valuesInColumnOne = new ArrayList<Integer>();
            int negNumNo = 0;
            int negNumYes = 0;
            int posNumNo = 0;
            int posNumYes = 0;

            for (int i = 0; i < 43500; i++) {
                int currentValue = resultingArray[1][i];
                int currentClass = resultingArray[9][i];
                /*if (!valuesInColumnOne.contains(currentValue)) {
                    valuesInColumnOne.add(currentValue);
                }*/
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

            /*Collections.sort(valuesInColumnOne);
            //System.out.println(valuesInColumnZero.size());
            for (int i = 0; i < valuesInColumnOne.size(); i++) {
                System.out.print(valuesInColumnOne.get(i) + " ");
            }
            System.out.println("");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
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
