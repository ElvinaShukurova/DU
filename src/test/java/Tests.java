
import org.example.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    public String returnActual(String command) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream pStream = new PrintStream(stream);
        System.setOut(pStream);
        Main.main(command.split(" "));
        System.out.flush();
        System.setOut(System.out);
        return stream.toString();
    }

    @Test
    void test1() throws Exception {
        assertEquals("src/test/resources - 55KB" + System.lineSeparator(),
                returnActual("-h src/test/resources"));
    }
    @Test
    void test2() throws Exception {
        assertEquals("Total sum - 36" + System.lineSeparator(),
                returnActual("-c src/test/resources/file1.txt src/test/resources/file2.txt src/test/resources/file3.txt"));
    }
    @Test
    void test3() throws Exception {
        assertEquals("src/test/resources/file1.txt - 34KB" + System.lineSeparator() +
                "src/test/resources/file2.txt - 3KB" + System.lineSeparator() +
                "src/test/resources/file3.txt - 45B" + System.lineSeparator(), returnActual("-h -si src/test/resources/file1.txt src/test/resources/file2.txt src/test/resources/file3.txt"));
    }
    @Test
    void test4() throws Exception {
        assertEquals("Total sum - 171KB" + System.lineSeparator(),returnActual("-h -si -c src/test/resources src/test/resources src/test/resources"));
    }
}
