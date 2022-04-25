package VeryGoodViroGame;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    
    
    static void print(String str)
    {
        System.out.println(str);
    }
    
    static boolean AskRunTest()
    {
        int ID;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        
        while(true)
        {
            try
            {
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
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
                return true;
            }
            catch(NumberFormatException ex)
            {
                if(str.trim().toLowerCase().equals("exit"))
                    return true;
                print("Invalid input: " + str);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        System.out.println("--== Biden Teszteloprogramja©®™ ==--");

        /*if (args.length>0)
        {
            ConsoleIO.RunCMD("load", args);
        }
        else
        {
            while (true) {
                Tester.ListTests();
                if (AskRunTest())
                    break;
            }
        }*/
        if(args.length == 0)
        {
            Scanner sc = new Scanner(System.in);
            while(true)
            {
                String[] s = sc.nextLine().split(" ");
                if(s.length == 0)
                    continue;
                if(s[0].equalsIgnoreCase("exit"))
                    break;
                if(s[0].equals("loadall"))
                {
                    String inputs =
                            "Bear_Strikes_Back.tin\n" + "Blaha_Lujza_Ter.tin\n" + "Check_Gloves_effect.tin\n" +
                            "Devmap_generates.tin\n" + "Get_Bear_virus.tin\n" + "Gloves_death.tin\n" + "Kill_Viro" +
                            ".tin\n" + "New_Hope.tin\n" + "ParalyzedMoveStrategy_not_moves_viro.tin\n" +
                            "Return_of_the_Axe.tin\n" + "Revenge_of_the_Viro.tin\n" + "SimpleMoveStrategy_moves_viro" + ".tin\n" + "Spread_Bear_virus.tin\n" + "testconsole.tin\n" + "teszt.tin\n" + "Timer_steps_tick-based_elements.tin\n" + "User_Acceptance_Test.tin\n" + "User_Acceptance_Test_variant.tin\n" + "User_Acceptance_Test_variant2.tin\n" + "VeryBigTest.tin\n" + "Viros_move_around_craft_and_do_stuff_that_is_not_politically_correct.tin\n" + "Viro_checks_if_they_are_paralyzed.tin\n" + "Viro_interacts_with_FieldBunker.tin\n" + "Viro_interacts_with_FieldLab.tin\n" + "Viro_interacts_with_FieldWarehouse.tin\n" + "Viro_tries_to_craft_virus_and_has_enough_resource.tin\n" + "Viro_tries_to_craft_virus_but_has_not_enough_resources.tin\n" + "Viro_tries_to_put_a_virus_on_a_Viro_and_succeeds.tin\n" + "Viro_tries_to_put_a_virus_on_a_Viro_but_paralyzed.tin\n" + "Viro_tries_to_put_a_virus_on_a_Viro_but_the_other_has_a_coat.tin\n" + "Viro_tries_to_steals_equipment_and_the_other_viro_is_paralyzed.tin\n" + "Viro_tries_to_steals_resource_and_the_other_Viro_is_paralyzed.tin\n" + "Viro_tries_to_steal_equipment_but_the_other_Viro_is_not_paralyzed.tin\n" + "Viro_tries_to_steal_resource_but_the_other_Viro_is_not_paralyzed.tin\n" + "Viro_vaccinates_a_Viro.tin\n" + "Viro’s_Menace.tin\n" + "VitusDanceMoveStrategy_randomly_moves_viro.tin";
                    String[] files = inputs.split("\n");
                    for(int i = 0; i < files.length; i++)
                    {
                        System.out.println(files[i]);
                        ConsoleIO.RunCMD("load", new String[]{files[i]});
                        ConsoleIO.RunCMD("clear", "".split(""));
                        System.out.println("\n");
                    }
                    continue;
                }
                ConsoleIO.RunCMD(s[0], Arrays.stream(s).skip(1).toArray(String[]::new));
            }
        }
        else
        {
            ConsoleIO.RunCMD("load", args);
        }
    }
}
