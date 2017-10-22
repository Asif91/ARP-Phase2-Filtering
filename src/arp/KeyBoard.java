package arp;


import java.util.*;
import java.io.*;
import java.time.*;


/**
 * Provides  utility methods for input output and validation of user interaction 
 * 
 * @author Asif 
 * @version v1.01
 */
 
public class KeyBoard
{
    // instance variables - replace the example below with your own
    private static Scanner in = new Scanner(System.in);
     /**
     * Receives String to prompt and read line 
     * 
     * @param       String prompt
     * @return      String read 
     */    
    public static String getString(String prompt)
    {
        System.out.print(prompt + " " );
        return in.nextLine();
    }
    
	/**
     * Receives String to prompt and read line 
     * 
     * @param       String prompt
     * @return      double read 
     */   
    public static Double getDouble(String prompt)
    {
        Double d = 0.00;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                d = Double.parseDouble(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Double");
            }
        }
        return d;
    }
    /**
     * Receives String to prompt and read line 
     * 
     * @param       String prompt
     * @return      integer read 
     */   
    public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }
     
	/**
     * Receives String to output 
     * 
     * @param       String toPrint
     */   	 
    public static void println(String toPrint)
    {
        System.out.println(toPrint);
    }
    
	/**
     *reads integer from commandline 
     * @return    Integer 
     */   
    public static Integer getInteger()
    {
        Integer i = 0;
        while (true)
        {
            try
            {
                System.out.print("Please enter an integer");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid integer");
            }
        }
        return i;
    }
    
    
}
