/**
 * 
 */
package arp;

/**
 * @author Asif
 * This is the main class of this script which filters out keywords from paragraphs matching with them
 * with the synonyms 
 * 
 * The class collaborates with connection.java for the query methods 
 *
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import arp.connection;
public class Filter {
	
public static Scanner scan = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Main Menu
		
		try {
			connection.openConnection();
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //end main menu
	}
	

	/**
     * this method runs the menu
     * 
     */

    public static void run() throws IOException
    {
        while(true)
            switch (menu() ) 
            {
                    case 1:
                        deleteAll();
                        break;
                    case 2:
                        runScript();
                        break;
                    case 3:
						connection.closeConnection();
                        return;   
                     default:
                        System.out.println ( "Invalid option" );
                        break;
            }
    }
    

	
	
	public static int menu()
	{
		
		// Main Menu
	
		
		 KeyBoard.println ("|-------------------Menu--------------------------|");
         KeyBoard.println ("| 1 - Delete All Data                       |");
         KeyBoard.println ("| 2 - Start from last position                    |");
         KeyBoard.println ("| 3 - Exit                                        |" );
          KeyBoard.println("|-------------------------------------------------|");
        return  KeyBoard.getInteger("Select Option: ");
		
	//	return i ;
	}
	

	
	public static void runScript()
	{
		connection.openConnection();
		ResultSet synonyms = fetchSynonyms();
		 int total = -1;

		int k = KeyBoard.getInteger("Enter \n 1 to perform on  all the remaining paragraphs \n 2 to specify number of paragraphs");

		int last = fetchLastPara();
		ResultSet desc = fetchParagraphs(last);

		if(k == 1)
		{
			System.out.println("working on it ....");
			String success = filterwords(synonyms, desc , total);
			System.out.println(success);
//			menu();
		}
		else if (k ==2)
		{
			boolean loop = false ; 
			do{
				int jobs =	KeyBoard.getInteger("Enter number of paragraphs (greater than 0) you want to filter : (-1 to return to main menu )");
			 
		
			if(jobs > 0)
			{
				System.out.println("working on it .. ");
				String success = filterwords(synonyms, desc, jobs);
				System.out.println(success);
			}
			else if(jobs == -1)
			{
				loop = true ;
			}
			else
			{
				System.out.println("Enter number greater than 0");
			}
			
			}
			while(!loop);
		}
	
	//	connection.closeConnection();
	}
	
	public static void deleteAll()
	{
	
		String l = KeyBoard.getString("Are you sure ? y/n");
		if(l.equalsIgnoreCase("y"))
		{
			deleteKeywordData();
			System.out.println("Data Deleted");
	
		}
		else
		{
			System.out.println("You saved your data by not entering 'y' ");
	
		}
	

	}
	//fetch last paragraph id to start from table paragraphs
	
	public static int fetchLastPara()
	{
		ResultSet last = null; 
		String sql = "SELECT * FROM keywords ORDER BY key_id DESC LIMIT 1";
		last = connection.selectSqlQuery(sql);
		int p = -1;
		try {
			if(last.next())
			{
				p = last.getInt("paragraph_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p ;
	}

	
	
	// this function fetches paragraph details along with their description
	public static ResultSet fetchParagraphs(int number)
	{
		ResultSet rs = null ; 
		
		String sql = "Select paragraph_id, descriptions from paragraphs where paragraph_id > '"+number+"' " ;
		
		rs = connection.selectSqlQuery(sql) ;
		
		return rs ; 
	}
	
	//fetch keywords available in list_keyword table in database with cat id
	public static ResultSet fetchSynonyms()
	{
			ResultSet keyset = null ; 
			
			String sql = "Select id,name, cat_id from synonyms" ;
			
			keyset =  connection.selectSqlQuery2(sql) ;
						
		
			
			return keyset ; 
	}
	
	
	// This is the main function to implement filter word algorithm
	public static String filterwords(ResultSet synonyms, ResultSet desc, int total)
	{
		
		//loop for keywords nested in para loop
		int paraId = 0 ;
		String description = "";
		String synonym = "";
		int count = 0 ; 
		try
		{

			
			while(desc.next())
			{
				
				paraId = desc.getInt("paragraph_id") ;
				description = desc.getString("descriptions") ;
				
				
				while(synonyms.next())
				{
					
					synonym = synonyms.getString("name");
					int synonym_id = synonyms.getInt("id");
					int category_id = synonyms.getInt("cat_id");
					
					// use description.contains(synonym) for other technique
					if(isContain(description,synonym))
					{
						int count1 = 0 ; 
	
						count1 ++ ;
						
						
						// the code lines below will be used in case you want to consider only single words in paragraphs instead of phrases
	/*					String[] arr = description.split(" ");
						for(int i =0 ; i<arr.length ;i++)
						{
							//synonym contains and break for loop
						
							if(arr[i].contains(synonym))
							{
								count1 ++ ;
							}
						}
						*/
						
						
						

						String checkKeyword = "Select * from keywords where keyword = '"+synonym+"' and paragraph_id = '"+paraId+"'  and category_id ='"+category_id+"' " ;
						
						ResultSet keywords = connection.selectSqlQuery(checkKeyword) ;
						
						
						if(keywords.next())
						{
     						int key_count = keywords.getInt("key_count");
     						int key_id= keywords.getInt("key_id");
							key_count += count1 ;

							String updatesql = "UPDATE keywords " + "SET key_count = '" + key_count + "' where key_id = "+key_id+" " ;
							
							boolean updateresult = connection.updateSqlQuery(updatesql);
 
						}
						else
						{

							String sql = "INSERT INTO `keywords`( `paragraph_id`, `key_count`, `keyword`, `synonym_id`, `category_id`) VALUES ( '" + paraId+ "', '"+count1+ "','" + synonym + "','" + synonym_id + "','"+category_id+"')" ;
							ResultSet result = connection.insertSqlQuery(sql);
						}
						
						
										
					}
				
				}
				synonyms.beforeFirst();
				
				
				if(total > 0)
				{
					count++;
				}
				if(count == total){
					
					break ;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		return "success"; 
	}
	
// matching function
	 private static boolean isContain(String description, String synonym){
         String pattern = "\\b"+synonym+"\\b";
         Pattern p=Pattern.compile(pattern);
         Matcher match=p.matcher(description);
         return match.find();
    }
	
	//Delete previous data in case of starting from beginning 
	public  static int deleteKeywordData()
	{
		String sql = "truncate table keywords";
		System.out.println("Deleting previous Data");
		int j = connection.deleteSqlQuery(sql);
		//System.out.println(j);
		if(j > 0)
		{
			System.out.println(j + " records deleted");
		}
		return j ; 
		
	}

}
