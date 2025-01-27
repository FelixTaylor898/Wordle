import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public interface WordUtil {
    ArrayList<String> words = new ArrayList<>();
    static Random rand = new Random();

    public static void setup() throws FileNotFoundException {
        File file = new File("src/words.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            words.add(scan.nextLine());
        }
        scan.close();
    }

    public static boolean checkString(String toCheck) {
        return words.contains(toCheck.toLowerCase());
    }

    public static String random() {
        return words.get(rand.nextInt(words.size())).toUpperCase();
    }

}