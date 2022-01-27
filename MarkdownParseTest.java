import static org.junit.Assert.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.nio.file.Path;

import org.junit.*;

public class MarkdownParseTest {

    HashMap<String, ArrayList<String>> fileMap;
    
    @Before
    public void setupMap() {
        fileMap = new HashMap<>();
        fileMap.put("test-file1.md", new ArrayList<String>(Arrays.asList("https://something.com", "some-page.html")));
        fileMap.put("test-file2.md", new ArrayList<String>(Arrays.asList()));
        fileMap.put("test-file3.md", new ArrayList<String>(Arrays.asList()));
    }

    @Test
    public void testFile1() throws Exception {
        assertEquals(fileMap.get("test-file1.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file1.md"))));
    }

    @Test
    public void testFile2() throws Exception {
        assertEquals(fileMap.get("test-file2.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file2.md"))));
    }

    @Test
    public void testFile3() throws Exception {
        assertEquals(fileMap.get("test-file3.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file3.md"))));
    }
}