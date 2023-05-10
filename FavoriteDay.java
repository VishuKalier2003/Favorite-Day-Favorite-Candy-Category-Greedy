/* You are given a (0-indexed) array of positive integers candiesCount where candiesCount[i] represents the number 
of candies of the ith type you have. You are also given a 2D array queries where queries[i] = [favoriteTypei, 
favoriteDayi, dailyCapi]. You play a game with the following rules:
1. You start eating candies on day 0.
2. You cannot eat any candy of type i unless you have eaten all candies of type i - 1.
3. You must eat at least one candy per day until you have eaten all the candies.
Construct a boolean array answer such that answer.length == queries.length and answer[i] is true if you can eat a 
candy of type favoriteTypei on day favoriteDayi without eating more than dailyCapi candies on any day, and false 
otherwise. Note that you can eat different types of candy on the same day, provided that you follow rule 2.
Return the constructed array answer. 
* Eg 1 : candiesCount = [7,4,5,3,8]     queries = [[0,2,2], [4,2,4], [2,13,100000]]    Output = [true, false, true]
* Eg 2 : candiesCount = [5,2,6,4,1]     queries = [[3,1,2], [4,10,3], [3,10,100]       Output = [false, true, true
*                                                  [4,100,30], [1,3,1]]                          false, false]  
*/ 
import java.util.*;
public class FavoriteDay
{
      public boolean[] FavoriteDayFavoriteCandy(int candiesCount[], int queries[][])
      {
            boolean[] answer = new boolean[queries.length];    // Array declaration... //*  Variable -> O(1)
            int greedy[] = new int[candiesCount.length];       // Greedy approach      //*  Array -> O(N)
            greedy[0] = candiesCount[0];     // base declaration...
            for(int i = 1; i < candiesCount.length; i++)     //! Greedy Array Creation -> O(N)
            {
                  // This array gives the number of candies to eat, to reach the i candy (inclusive)...
                  greedy[i] = greedy[i - 1] + candiesCount[i];    // Tabulation approach...
            }
            for(int i = 0; i < queries.length; i++)     //! Query Checking -> O(N)
            {
                  int max = queries[i][1] * queries[i][2];     // The maximum candies we can eat...
                  int min = queries[i][1] * 1;                 // The minimum candies we can eat...
                  if((greedy[queries[i][0]] >= min) && (greedy[queries[i][0]] < max))
                        answer[i] = true;      // If the query candy is in range...
                  else if(greedy[queries[i][0]] >= max)
                  {     // If the query candy is not in range...
                        int Eat = greedy[queries[i][0]] - candiesCount[i];
                        if(Eat - max > 1)    // If we have to eat another type of candy before query candy...
                              answer[i] = false;
                        else      // If we have already eaten one or more query candy and a query candy is left...
                              answer[i] = true;
                  }
            }
            return answer;    // Return the array of possibilites as answer...
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int x;
            System.out.print("Enter the length of Candies count array : ");
            x = sc.nextInt();
            int count[] =new int[x];
            for(int i = 0; i < count.length; i++)
            {
                  System.out.print("Enter the number of candies on the "+(i+1)+" th day: ");
                  count[i] = sc.nextInt();
            }
            System.out.print("Enter the number of queries : ");
            x = sc.nextInt();
            int query[][] = new int[x][3];
            for(int i = 0; i < query.length; i++)
            {
                  System.out.print("Favorite day for "+(i+1)+" th query : ");
                  query[i][0] = sc.nextInt();
                  System.out.print("Favorite candy for "+(i+1)+" th query : ");
                  query[i][1] = sc.nextInt();
                  System.out.print("Daily Capacity for "+(i+1)+" th query : ");
                  query[i][2] = sc.nextInt();
            }
            boolean values[] = new boolean[query.length];
            FavoriteDay favoriteday = new FavoriteDay();      // Object creation...
            values = favoriteday.FavoriteDayFavoriteCandy(count, query);     // Function calling...
            for(int i = 0; i < values.length; i++)
                  System.out.print(values[i]+", ");
            System.out.println();
            sc.close();
      }
}



//! Time Complexity -> O(N)
//* Space Complexity -> O(N)

/** //? DEDUCTIONS :-
 * ? We take the array and find the greedy array, to get the candies to eat before the ith candy...
 * ? We then find the range of candies we can eat, we can eat the maximum candies and the minimum candies...
 * ? If the candy falls in the range, then we can eat it on the favorite day, otherwise, we check if we have to eat
 * ? any other candy or not...
 */