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
        String[] arg = {"input1-8/input1.txt", "input1-8/input2.txt", "input1-8/input3.txt", "input1-8/input4.txt", "input1-8/input5.txt"};
        String res = content("test1-8/test1.txt");
        FileWriter output = new FileWriter("out1.txt");
        File out = new File("out1.txt");
        Tar.out(output, arg);
        assertEquals(res, content("out1.txt"));
        out.delete();

        String[] arg1 = {"input1-8/input6.txt"};
        String res1 = content("test1-8/test2.txt");
        FileWriter output1 = new FileWriter("out1.txt");
        File out1 = new File("out1.txt");
        Tar.out(output1, arg);
        assertEquals(res, content("out1.txt"));
        out1.delete();

        String[] arg2 = {"input1-8/input7.txt", "input1-8/input8.txt"};
        String res2 = content("test1-8/test3.txt");
        FileWriter output2 = new FileWriter("out1.txt");
        File out2 = new File("out1.txt");
        Tar.out(output2, arg);
        assertEquals(res, content("out1.txt"));
        out2.delete();

        String[] arg3 = {"input1-8/input1.txt", "input1-8/input2.txt", "input1-8/input3.txt"};
        String res3 = content("test1-8/test4.txt");
        FileWriter output3 = new FileWriter("out1.txt");
        File out3 = new File("out1.txt");
        Tar.out(output3, arg);
        assertEquals(res, content("out1.txt"));
        out3.delete();
    }

    @Test
    void u() throws IOException {
        Tar.u(new Scanner(new FileReader("test1-8/test5.txt")));
        Tar.u(new Scanner(new FileReader("test1-8/test6.txt")));
        Tar.u(new Scanner(new FileReader("test1-8/test7.txt")));
        Tar.u(new Scanner(new FileReader("test1-8/test8.txt")));
        String[] arg = {"chorus.txt",  "verse1.txt", "verse2.txt", "verse3.txt", "end.txt", "empty.txt",
                "pull.txt", "abc.txt", "verse11.txt", "verse22.txt", "verse33.txt"};

        assertEquals(content("chorus.txt"), content("input1-8/input1.txt"));
        assertEquals(content("verse1.txt"), content("input1-8/input2.txt"));
        assertEquals(content("verse2.txt"), content("input1-8/input3.txt"));
        assertEquals(content("verse3.txt"), content("input1-8/input4.txt"));
        assertEquals(content("end.txt"), content("input1-8/input5.txt"));
        assertEquals(content("empty.txt"), content("input1-8/input6.txt"));
        assertEquals(content("pull.txt"), content("input1-8/input7.txt"));
        assertEquals(content("abc.txt"), content("input1-8/input8.txt"));
        assertEquals(content("verse11.txt"), content("input1-8/input1.txt"));
        assertEquals(content("verse22.txt"), content("input1-8/input2.txt"));
        assertEquals(content("verse33.txt"), content("input1-8/input3.txt"));

        for (String file: arg){
            File fileTemp = new File(file);
            fileTemp.delete();
        }
    }

}




