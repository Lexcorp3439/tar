import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TarTest {
    private static String content(String file) throws IOException {
        Scanner fileTemp = new Scanner(new FileReader(file));
        StringBuilder result = new StringBuilder("");
        while (fileTemp.hasNext()){
            result.append(fileTemp.nextLine());
            if (fileTemp.hasNext())
                result.append("\n");
        }
        fileTemp.close();
        return result.toString();
    }

    @Test
    void out() throws IOException {
        String[] arg = {"input1.txt", "input2.txt", "input3.txt", "input4.txt", "input5.txt"};
        String res = content("test1.txt");
        assertEquals(res, Tar.out(arg , arg.length));

        String[] arg1 = {"input6.txt"};
        String res1 = content("test2.txt");
        assertEquals(res1, Tar.out(arg1, arg1.length));

        String[] arg2 = {"input7.txt", "input8.txt"};
        String res2 = content("test3.txt");
        assertEquals(res2, Tar.out(arg2, arg2.length));

        String[] arg3 = {"input1.txt", "input2.txt", "input3.txt"};
        String res3 = content("test4.txt");
        assertEquals(res3, Tar.out(arg3, arg3.length));
    }

    @Test
    void u() throws IOException {
        Tar.u("test5-8\\test5.txt");
        Tar.u("test5-8\\test6.txt");
        Tar.u("test5-8\\test7.txt");
        Tar.u("test5-8\\test8.txt");
        String[] arg = {"chorus.txt",  "verse1.txt", "verse2.txt", "verse3.txt", "end.txt", "empty.txt",
                "pul.txt", "abc.txt", "verse11", "verse22", "verse33"};

        assertEquals(content("chorus.txt"), content("input1.txt"));
        assertEquals(content("verse1.txt"), content("input2.txt"));
        assertEquals(content("verse2.txt"), content("input3.txt"));
        assertEquals(content("verse3.txt"), content("input4.txt"));
        assertEquals(content("end.txt"), content("input5.txt"));
        assertEquals(content("empty.txt"), content("input6.txt"));
        assertEquals(content("pull.txt"), content("input7.txt"));
        assertEquals(content("abc.txt"), content("input8.txt"));
        assertEquals(content("verse11.txt"), content("input1.txt"));
        assertEquals(content("verse22.txt"), content("input2.txt"));
        assertEquals(content("verse33.txt"), content("input3.txt"));

        for (String file: arg){
            File fileTemp = new File(file);
            fileTemp.delete();
        }
    }

}




