// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("](", nextOpenBracket);
            int closeParen = markdown.indexOf(")", nextCloseBracket + 1);
            if(nextOpenBracket == - 1 || nextCloseBracket == -1 || closeParen == -1){
                break;
            }
            toReturn.add(markdown.substring(nextCloseBracket + 2, closeParen));
            currentIndex = closeParen + 1;
        }
        ArrayList<String> filteredToReturn = new ArrayList<>();
        for(String s: toReturn){
            if (s.contains(".png") == false && s.contains(".jpg") == false && s.contains(".jpeg") == false && 
                s.contains("data:")== false  || s.contains("www.") == true){
                    filteredToReturn.add(s);
            }
        }
        ArrayList<String> filteredToReturn2 = new ArrayList<>();
        for(String s: filteredToReturn){
            if (s.contains(" ") == false){
                filteredToReturn2.add(s);
            }
        }
        ArrayList<String> filteredToReturn3 = new ArrayList<>();
        for(String s: filteredToReturn2)
            if((s.indexOf(".") + 1 == s.length()) == false && s.contains(".") == true){
                filteredToReturn3.add(s);
            }

        return filteredToReturn3;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}