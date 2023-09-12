import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            WordUtil.setup();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new Game();
    }
}