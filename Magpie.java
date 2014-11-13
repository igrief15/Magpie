import java.util.Random;
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
    if (findKeyword(statement, "no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0
               || statement.indexOf("father") >= 0
               || statement.indexOf("sister") >= 0
               || statement.indexOf("brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "dog") >= 0
               || statement.indexOf("cat") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if (findKeyword(statement, "Kiang") >= 0)
    {
      response = "Mr. Kiang is the best teacher ever!";
    }
    else if (findKeyword(statement, "Hello") >= 0)
    {
      response = "Hello. How are you?";
    }
    else if (findKeyword(statement, "Landgraf") >= 0)
    {
      response = "Mr. Landgraf is the best teacher ever!";
    }
    else if (phrase.length() == 0)
    {
      response = "Please say something."; 
    }
    else if (findKeyword(statement, "apple") >= 0
               || findKeyword(statement, "orange") >= 0
               || findKeyword(statement, "banana") >= 0
               || findKeyword(statement, "rice") >= 0
               || findKeyword(statement, "spaghetti") >= 0
               || findKeyword(statement, "fruit") >= 0
               || findKeyword(statement, "vegetable") >= 0
               || findKeyword(statement, "food") >= 0
               || findKeyword(statement, "candy") >= 0
               || findKeyword(statement, "muffin") >= 0
               || findKeyword(statement, "bread") >= 0
               || findKeyword(statement, "cheese") >= 0
               || findKeyword(statement, "soup") >= 0)      
    {
      response = "Talking about food is making me hungry."; 
    }
    else if (findKeyword(statement, "Magpie") >= 0)
    {
      response = "Oh, I know about Magpie. It is a chat bot."; 
    }
    else if (findKeyword(statement, "Isaiah") >= 0)
    {
      response = "Isaiah programmed this chat bot."; 
    }
    else if (findKeyword(statement, "America") >= 0)
    {
      response = "God bless America."; 
    }
    else if (findKeyword(statement, "tired") >= 0)
    {
      response = "Not sleeping is unhealthy."; 
    }
    // when there is more than one keyword in a string, the response draws from the first if statement that is called on
    //when keywords are contained within other words, they are detected anyway, so the 'no' response happens when 'know' 'cannon', or 'pinot' are entered
    
    
    // Responses which require transformations
    else if (findKeyword(statement, "I want to", 0) >= 0)
    {
      response = transformIWantToStatement(statement);
    }
    else if (findKeyword(statement, "is", 0) >= 0)
    {
      response = transformIsStatement(statement);
    }
    else if ((findKeyword(statement, "are", 0) >= 0) && !(findKeyword(statement, "you", 0) >=0))
    {
      response = transformAreStatement(statement);
    }
    
    else if (findKeyword(statement, "I want", 0) >= 0)
    {
      response = transformIWantStatement(statement);
    }
    else if (findKeyword(statement, "You are", 0) >=0)
    {
      response = transformYouAreStatement(statement);
    }
    else if (findKeyword(statement, "You were", 0) >=0)
    {
      response = transformYouWereStatement(statement);
    }
      else if (findKeyword(statement, "I was", 0) >=0)
    {
      response = transformIWasStatement(statement);
    }

    else
    {
      // Look for a two word (you <something> me)
      // pattern
      int psn = findKeyword(statement, "you", 0);
      int psn1 = findKeyword(statement, "I", 0);
      if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else if (psn1 >= 0
                 && findKeyword(statement, "you", psn1) >= 0)
      {
        response = transformYouIStatement(statement);
      }
      
      
      else
      {
        response = getRandomResponse();
      }
    }
    return response;
  }
  
  /*  
   I threw the ball to you.
   why did you throw the ball to me?
   -irregular past tense verbs, 
   
   I have been waiting, Obi-Wan.
   Why have you been waiting?
   -in a normal sentence, maybe 'i have been waiting for....'
   -so if 'for' does not appear after waiting, then cut out the rest
   
    We had not read the books.
   Why had we not read the books?
   -had works
   -just have to change the you to i and vice versa
   -i dont know how to differentiate between 'I had a puppy, but it died' and 'I had to go to the bathroom' or 'I had changed the password a month before I was hacked'. 
   -its entirely contextual and doesnt really work as a rule
   
  
  

   I worked hard.
   why did you work hard?
   -a pronoun, and then a verb, with -ed suffix
   
   I tallied the results.
   why did you tally the results?
   "
   
   */
  
  /**
   * Take a statement with "I want to <something>." and transform it into 
   * "What would it mean to <something>?"
   * @param statement the user statement, assumed to contain "I want to"
   * @return the transformed statement
   */
  
  
  /*
  I was hoping we would meet.
   Why were you hoping we would meet?
   -I was dying of cancer
   Why were you...
   -I was (verb)
   -I was cold
   Why were you cold
   */
private String transformYouWereStatement(String statement)
  {
    statement = statement.trim();
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "You were", 0);
    String restOfStatement = statement.substring(psn + 8).trim();
    return "Why was I " + restOfStatement + "?";
  }
private String transformIWasStatement(String statement)
  {
    statement = statement.trim();
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I was", 0);
    String restOfStatement = statement.substring(psn + 5).trim();
    return "Why were you " + restOfStatement + "?";
  }
    
  private String transformIsStatement(String statement)
  {
    statement = statement.trim();
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "is", 0);
    String beforeStatement = statement.substring(0,psn).trim();
    String restOfStatement = statement.substring(psn + 2).trim();
    return "Why is "+ beforeStatement+ " " + restOfStatement + "?";
  }
  
  private String transformYouAreStatement(String statement) //You are intelligent / You are a nice person, just take after 'are'
  {
    statement = statement.trim();
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "are", 0);
    String restOfStatement = statement.substring(psn + 3).trim();
    return "Why am I " + restOfStatement + "?";
  }
  
  private String transformAreStatement(String statement)
  {
    statement = statement.trim();
    statement = statement.toLowerCase();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "are", 0);
    String beforeStatement = statement.substring(0,psn).trim();
    String restOfStatement = statement.substring(psn + 3).trim();
    return "Why are "+ beforeStatement+ " " + restOfStatement + "?";
  }
  //when inputting a 'have' statement, it messes up, not sure how to solve for have statements, past tense
  
  private String transformIWantStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I want", 0);
    String restOfStatement = statement.substring(psn + 6).trim();
    return "Would you really be happy if you had " + restOfStatement + "?";
  }
  
  private String transformIWantToStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 9).trim();
    return "What would it mean to " + restOfStatement + "?";
  }
  
  
  
  /**
   * Take a statement with "you <something> me" and transform it into 
   * "What makes you think that I <something> you?"
   * @param statement the user statement, assumed to contain "you" followed by "me"
   * @return the transformed statement
   */
  
  
  private String transformYouMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  
  private String transformYouIStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 3);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement.length() - 3);
    }
    int psnOfYou = findKeyword (statement, "I", 0);
    int psnOfMe = findKeyword (statement, "you", psnOfYou + 1);
    String restOfStatement = statement.substring(psnOfYou + 1, psnOfMe).trim();
    return "Why do you " + restOfStatement + " me?";
  }
  
  
  
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no").
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @param startPos
   *            the character of the string to begin the
   *            search at
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal, int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
      }
      
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
      
    }
    
    return -1;
  }
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no"). The search
   * begins at the beginning of the string.
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * 
   * @return a non-committal string
   */
  
  
  
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse ()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  private String [] randomResponses = {"Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "Mmkay.",
    "Oh, really?",
    "Okay.",
    "Huh.",
    "Mm.",
    "Tell me more about this."
  };
}
