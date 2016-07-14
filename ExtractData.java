import java.io.*;
import java.util.*;

class ExtractData {
    static String fileName = "shuttle.trn";
    public static void main(String[] args) {
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
            ArrayList<Integer> valuesInColumnZero = new ArrayList<Integer>();
            for (int i = 0; i < 43500; i++) {
                int currentValue = resultingArray[0][i];
                if (!valuesInColumnZero.contains(currentValue)) {
                    valuesInColumnZero.add(currentValue);
                }
            }

            System.out.println(valuesInColumnZero.size());
            for (int i = 0; i < valuesInColumnZero.size(); i++) {
                System.out.print(valuesInColumnZero.get(i) + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
