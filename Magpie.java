/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    String response = "";
    String phrase = statement.trim();
    if (statement.indexOf("no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (statement.indexOf("mother") >= 0
               || statement.indexOf("father") >= 0
               || statement.indexOf("sister") >= 0
               || statement.indexOf("brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (statement.indexOf("dog") >= 0
               || statement.indexOf("cat") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if (statement.indexOf("Kiang") >= 0)
    {
      response = "Mr. Kiang is the best teacher ever!";
    }
    else if (statement.indexOf("Landgraf") >= 0)
    {
      response = "Mr. Landgraf is the best teacher ever!";
    }
    else if (phrase.length() == 0)
    {
      response = "Please say something."; 
    }
    else if (statement.indexOf("apple") >= 0
               || statement.indexOf("orange") >= 0
               || statement.indexOf("banana") >= 0
               || statement.indexOf("rice") >= 0
               || statement.indexOf("spaghetti") >= 0
               || statement.indexOf("fruit") >= 0
               || statement.indexOf("vegetable") >= 0
               || statement.indexOf("food") >= 0
               || statement.indexOf("candy") >= 0
               || statement.indexOf("pastr") >= 0
               || statement.indexOf("muffin") >= 0
               || statement.indexOf("bread") >= 0
               || statement.indexOf("cheese") >= 0
               || statement.indexOf("soup") >= 0)
    {
      response = "Talking about food is making me hungry."; 
    }
    else if (statement.indexOf("Magpie") >= 0)
    {
      response = "Oh, I know about Magpie. It is a chat bot."; 
    }
    else if (statement.indexOf("Isaiah") >= 0)
    {
      response = "Isaiah programmed this chat bot."; 
    }
    else if (statement.indexOf("America") >= 0)
    {
      response = "God bless America."; 
    }
     else if (statement.indexOf("tired") >= 0)
    {
      response = "Not sleeping is unhealthy."; 
    }
    // when there is more than one keyword in a string, the response draws from the first if statement that is called on
    //when keywords are contained within other words, they are detected anyway, so the 'no' response happens when 'know' 'cannon', or 'pinot' are entered
     
    else
    {
      response = getRandomResponse();
    }
    return response;
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if (whichResponse == 4)
    {
      response = "Mmkay.";
    }
    else if (whichResponse == 5)
    {
      response = "Oh, really?.";
    }
    return response;
  }
}
