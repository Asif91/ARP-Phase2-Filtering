/**
 * 
 */
package arp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class fetch {
	public static void main(String[] args) {

		URL url;
		URL urlResult ;
		try {
		
			
			urlResult = new URL("https://www.seek.com.au/tester-jobs/in-All-Melbourne-VIC");

			URLConnection conn2 = urlResult.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br2 = new BufferedReader(
                               new InputStreamReader(conn2.getInputStream()));
			
			String inputLine2 ;
			//save to this filename
			while ((inputLine2 = br2.readLine()) != null) {
				
			//	System.out.println("----------All data------");
//for all data uncomment the line below
				// we get 19 results or results of single page 
				System.out.println(inputLine2);
				if(inputLine2.contains("at SEEK with"))
			
				{
					System.out.println("----------TOTAL RESULTS------");
					
					String result = inputLine2.substring(inputLine2.indexOf("with"), inputLine2.indexOf("found"));
					System.out.println(result);
					
				}

			}

			br2.close();

			

			
			
			
			// get URL content
			url = new URL("https://www.seek.com.au/job/34211831");
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

			String inputLine ;
			//save to this filename
			while ((inputLine = br.readLine()) != null) {
				
				if(inputLine.contains("status"))
				{
					System.out.println("----------JOB JD------");
					
					String desc = inputLine.substring(inputLine.indexOf("status"), inputLine.indexOf("user"));
				//	System.out.println(desc);
			//System.out.println(inputLine);
				}
			}

			br.close();

			
			
			System.out.println("Done");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}