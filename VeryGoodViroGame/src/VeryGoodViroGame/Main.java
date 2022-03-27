package VeryGoodViroGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{


    static void print(String str) {
        System.out.println(str);
    }

    static void AskRunTest()
    {
        System.out.print("Select test: ");
        int ID;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                ID = Integer.parseInt(reader.readLine().toLowerCase());
                print("\n");
                Tester.RunTest(ID-1);
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
            catch (NumberFormatException ex){
                print("Invalid input.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("--== Goldshit Teszteloprogramja©®™ ==--"); //todo ne hagyjuk itt plsspls

        while(true)
        {
            Tester.ListTests();
            AskRunTest();
        }

    }

}
