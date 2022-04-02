package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    static void print(String str) {
        System.out.println(str);
    }

    static boolean AskRunTest() {
        int ID;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while (true) {
            try {
                System.out.print("Select test: ");
                str = reader.readLine();
                ID = Integer.parseInt(str.toLowerCase());
                print("\n");
                Tester.RunTest(ID - 1);
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".\n");
            } catch (IOException ex) {
                ex.printStackTrace();
                return true;
            } catch (NumberFormatException ex) {
                if (str.trim().toLowerCase().equals("exit"))
                    return true;
                print("Invalid input: " + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--== Biden Teszteloprogramja©®™ ==--");

        while (true) {
            Tester.ListTests();
            if (AskRunTest())
                break;
        }
    }

}
