package tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tokenizer {
        
    private ArrayList<String> tokenList = new ArrayList<String>();
    
    public Tokenizer() {
        String codeString = parseCode(); 
        tokenList = tokenizeCode(codeString);
        
        //displays parsed tokens
        //for(int i = 0; i < tokenList.size(); i++)
        //System.out.println(tokenList.get(i));
       
        
       setTokens(tokenList);
    }
    
    public String parseCode() {
        String fileName = "C:\\Users\\Kurt\\Documents\\NetBeansProjects\\Tokenizer\\src\\tokenizer\\sampleCode.txt";

        String line = null;
        String hyperString = null;
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            if((line = bufferedReader.readLine()) != null) {
                hyperString = (line + "\n");
                //System.out.println(line);
            }
            
            while((line = bufferedReader.readLine()) != null) {
                hyperString += (line + "\n");
                //System.out.println(line);
            }   
            
            //System.out.println(hyperString);
            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // ex.printStackTrace();
        }   
        return hyperString;
    }
    
    public ArrayList<String> tokenizeCode(String hyperString){
        ArrayList<String> tokenList = new ArrayList<String>();
        
        //code currently uses sybmols as token splits (inefficient, should be optimized)
        StringTokenizer multiTokenizer = new StringTokenizer(hyperString, " =.,(){}\t\n\"");
        
        while (multiTokenizer.hasMoreTokens()){
            //System.out.println(multiTokenizer.nextToken());
            tokenList.add(multiTokenizer.nextToken());
        }
        
        return tokenList;    
    }
    
    public void setTokens(ArrayList<String> tokenList){
        int tokenIndex = 0;
        
        while(tokenIndex < tokenList.size()){
            if(isFunction(tokenList.get(tokenIndex))){     
                int increment = performFunction(tokenIndex);
                tokenIndex += increment;
            } else tokenIndex++;
        }
    }
    
    public boolean isFunction(String token){
        boolean function;
        
        switch(token){
            case "CREATE": function = true;
                            break;
            case "SET": function = true;
                            break;
            case "SPAWN": function = true;
                            break;
            case "LOOP": function = true;
                            break;
            case "IF": function = true;
                            break;
            case "ELSE": function = true;
                            break;
            case "COLLIDES": function = true;
                            break;
            case "FIGHTS": function = true;
                            break;
            case "USES": function = true;
                            break;
            case "Receives": function = true;
                            break;
            case "Iterate": function = true;
                            break;
            case "END": function = true;
                            break;
            default: function = false;
        }
        
        return function;
    }
    
    public int performFunction(int tokenIndex){
        int increment;

        switch(tokenList.get(tokenIndex)){
            case "CREATE": increment = performCreate(tokenIndex);
                            break;
            case "SET": increment = performSet(tokenIndex);
                            break;
            case "SPAWN": increment = performSpawn(tokenIndex);
                            break;
            case "LOOP": increment = performLoop(tokenIndex);
                            break;      
            case "IF": increment = performIf(tokenIndex);
                            break;
            case "ELSE": increment = performElse(tokenIndex);
                            break;
            case "COLLIDES": increment = performCollides(tokenIndex);
                            break;
            case "FIGHTS": increment = performFights(tokenIndex);
                            break;
            case "USES": increment = performUses(tokenIndex);
                            break;
            case "Receives": increment = performReceives(tokenIndex);
                            break;
            case "Iterate": increment = performIterate(tokenIndex);
                            break;
            case "END": increment = performEnd(tokenIndex);
                            break;
            default: increment = 1;
        }
        increment++;
        return increment;
    }
        
    public int performCreate(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment+=2;
        switch (tokenList.get(tempIndex)) {
            case "Map":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                System.out.print(output.get(0) + " has been created as a new");
                System.out.print(output.get(1) + " with the dimensions: X = ");
                System.out.println(output.get(2) + " and Y = " + output.get(3) + "!");
                break;
            case "Int":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                System.out.print(output.get(0) + " has been created as a new ");
                System.out.print(output.get(1) + " with the Value: ");
                System.out.println(output.get(2) + "!");
                break;
            case "GameOverScreen":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                System.out.print(output.get(0) + " has been created as a new");
                System.out.print(output.get(1) + " with the Message: ");
                System.out.print(output.get(2) + " and the Options: ");
                System.out.println(output.get(3) + " or " + output.get(4) + "!");
                break;
            default:
                output.add(tokenList.get(tempIndex));
                System.out.print(output.get(0) + " has been created as a new ");
                System.out.println(output.get(1) + "!");
                break;
        }
        
        return increment;
    }
    
    public int performSet(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment+=2;
        switch (tokenList.get(tempIndex)) {
            case "coord":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                System.out.print(output.get(1) + ": X = " + output.get(2) + " and Y = ");
                System.out.print(output.get(3) + "has been set as the" + output.get(4));
                System.out.println("of the Map: " + output.get(0) +"!");
                break;
            default:
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                System.out.println(output.get(0) + " has been set as " + output.get(1) + "!");
        }   
        return increment;
    }
    
    public int performSpawn(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment+=2;
        output.add(tokenList.get(tempIndex));
        
        System.out.print(output.get(0) + " has been spawned at ");
        System.out.println(output.get(1) + "!");
        
        return increment;
    }
    
    public int performLoop(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment+=2;
        output.add(tokenList.get(tempIndex));
        
        System.out.println("<<START LOOP BLOCK>>");
        System.out.println("Looped " + output.get(0) + " up to " + output.get(1) + " times!");
        
        return increment;
    }
    
    public int performIf(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        output.add(tokenList.get(tempIndex));
        increment++;
        
        System.out.println("<<START "+ output.get(0) + " BLOCK>>");
        
        return increment;
    }
    
    public int performElse(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        output.add(tokenList.get(tempIndex));
        increment++;
        
        System.out.println("<<"+ output.get(0) + " STATEMENTS>>");
        
        return increment;
    }
    
    public int performCollides(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex--;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment++;
        output.add(tokenList.get(tempIndex));
        
        System.out.print(output.get(1) + " sees ");
        System.out.println(output.get(0) + "!");
        
        return increment;
    }
    
    public int performFights(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex--;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment++;
        output.add(tokenList.get(tempIndex));
        
        System.out.print(output.get(0) + " fights with ");
        System.out.println(output.get(1) + "!");
        
        return increment;
    }
    
    public int performUses(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex--;
        output.add(tokenList.get(tempIndex));
            
        tempIndex+=2;
        increment++;
        output.add(tokenList.get(tempIndex));
        
        System.out.print(output.get(0) + " used ");
        System.out.println(output.get(1) + "!");
        
        return increment;
    }
    
    public int performReceives(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex--;
        output.add(tokenList.get(tempIndex));
        
        tempIndex+=2;
        increment++;
        switch (tokenList.get(tempIndex)) {
            case "Exp":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));
                System.out.println(output.get(0) + " gained " + output.get(2) + " " + output.get(1) + "!");
                break;
            case "Gold":
                output.add(tokenList.get(tempIndex));
                tempIndex++;
                increment++;
                output.add(tokenList.get(tempIndex));;
                System.out.println(output.get(0) + " received " + output.get(2) + " " + output.get(1) + "!");
                break;
            default:
                output.add(tokenList.get(tempIndex));
                System.out.println(output.get(0) + " received " + output.get(1) + "!");
        }       
        return increment;
    }
    
    public int performIterate(int currIndex){
        ArrayList<String> output = new ArrayList<String>();
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
            
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
        
        System.out.println("Iterated " + output.get(0) + " by " + output.get(1) + "!");
        
        return increment;
    }
    
    public int performEnd(int currIndex){
        ArrayList<String> output = new ArrayList<String>();   
        int tempIndex = currIndex;    
        int increment = 0;
        
        tempIndex++;
        increment++;
        output.add(tokenList.get(tempIndex));
        System.out.println("<<END " + output.get(0) + " BLOCK>>");     
        
        return increment;
    }
}
