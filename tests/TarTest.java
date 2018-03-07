import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TarTest {

    @Test
    void out() throws IOException {
        String[] arg = {"input1.txt", "input2.txt", "input3.txt", "input4.txt", "input5.txt"};
        String res = "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "(by input1.txt 5)\n" +
                "\n" +
                "You're sick of feeling numb\n" +
                "You're not the only one\n" +
                "I'll take you by the hand\n" +
                "And I'll show you a world that you can understand\n" +
                "This life is filled with hurt\n" +
                "When happiness doesn't work\n" +
                "Trust me and take my hand\n" +
                "When the lights go out you will understand\n" +
                "(by input2.txt 15)\n" +
                "\n" +
                "Anger and agony\n" +
                "Are better than misery\n" +
                "Trust me I've got a plan\n" +
                "When the lights go off you will understand\n" +
                "(by input3.txt 21)\n" +
                "\n" +
                "I know (I know I know I know I know)\n" +
                "That you're wounded\n" +
                "You know (You know you know you know you know)\n" +
                "That I'm here to save you\n" +
                "You know (You know you know you know you know)\n" +
                "I'm always here for you\n" +
                "I know (I know I know I know I know)\n" +
                "That you'll thank me later\n" +
                "(by input4.txt 31)\n" +
                "\n" +
                "Pain, without love\n" +
                "Pain, can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Rather feel pain than nothing at all\n" +
                "Rather feel pain\n" +
                "(by input5.txt 47)";
        assertEquals(res, outTest(arg, arg.length));

        String[] arg1 = {"input6.txt"};
        String res1 = "(by input6.txt 1)";
        assertEquals(res1, outTest(arg1, arg1.length));

        String[] arg2 = {"input7.txt", "input8.txt"};
        String res2 = "(by input7.txt 1)\n" +
                "\n" +
                "iuadfhviuhfdailiuhv\n" +
                "dsafdca\n" +
                "sdcsd\n" +
                "(by input8.txt 6)";
        assertEquals(res2, outTest(arg2, arg2.length));

        String[] arg3 = {"input1.txt", "input2.txt", "input3.txt"};
        String res3 = "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "(by input1.txt)\n" +
                "\n" +
                "You're sick of feeling numb\n" +
                "You're not the only one\n" +
                "I'll take you by the hand\n" +
                "And I'll show you a world that you can understand\n" +
                "This life is filled with hurt\n" +
                "When happiness doesn't work\n" +
                "Trust me and take my hand\n" +
                "When the lights go out you will understand\n" +
                "(by input2.txt)\n" +
                "\n" +
                "Anger and agony\n" +
                "Are better than misery\n" +
                "Trust me I've got a plan\n" +
                "When the lights go off you will understand\n" +
                "(by input3.txt)";
        assertEquals(res3, outTest(arg3, arg3.length));
    }

    @Test
    void uTest() throws IOException {
        String str1 = "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "-----------------------chorus.txt-----------------------\n" +
                "You're sick of feeling numb\n" +
                "You're not the only one\n" +
                "I'll take you by the hand\n" +
                "And I'll show you a world that you can understand\n" +
                "This life is filled with hurt\n" +
                "When happiness doesn't work\n" +
                "Trust me and take my hand\n" +
                "When the lights go out you will understand\n" +
                "-----------------------verse1.txt-----------------------\n" +
                "Anger and agony\n" +
                "Are better than misery\n" +
                "Trust me I've got a plan\n" +
                "When the lights go off you will understand\n" +
                "-----------------------verse2.txt-----------------------\n" +
                "I know (I know I know I know I know)\n" +
                "That you're wounded\n" +
                "You know (You know you know you know you know)\n" +
                "That I'm here to save you\n" +
                "You know (You know you know you know you know)\n" +
                "I'm always here for you\n" +
                "I know (I know I know I know I know)\n" +
                "That you'll thank me later\n" +
                "-----------------------verse3.txt-----------------------\n" +
                "Pain, without love\n" +
                "Pain, can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Pain, without love\n" +
                "Pain, I can't get enough\n" +
                "Pain, I like it rough\n" +
                "'Cause I'd rather feel pain than nothing at all\n" +
                "Rather feel pain than nothing at all\n" +
                "Rather feel pain\n" +
                "-----------------------end.txt-----------------------\n";
        assertEquals(str1, uTest("test1.txt"));

        String str2 = "\n" +
                "-----------------------openOut.txt-----------------------\n";
        assertEquals(str2, uTest("test2.txt"));

        String str3 = "\n" +
                "-----------------------lOut1.txt-----------------------\n" +
                "iuadfhviuhfdailiuhv\n" +
                "dsafdca\n" +
                "sdcsd\n" +
                "-----------------------lOut2.txt-----------------------\n";
        assertEquals(str3, uTest("test3.txt"));
    }

    private static String outTest(String[] args, int size) throws IOException {
        StringBuilder result = new StringBuilder("");
        int i = 0;
        int sizeOut = 0;
        for (String file: args) {
            if (!args[args.length - 1].matches("\\w+\\.txt"))
                throw new IllegalArgumentException("Введен неверный формат аргументов");
            if (sizeOut == size) break;
            Scanner input = new Scanner(new FileReader(file));
            while (input.hasNext()) {
                result.append(input.nextLine());
                result.append("\n");
                i++;
            }
            result.append("(by ").append(file).append(" ").append(i + 1).append(")");
            sizeOut++;
            if (sizeOut != size){
                result.append("\n\n");
                i += 2;
            }

        }
        return result.toString();
    }


    static String uTest(String file) throws IOException {
        Scanner input = new Scanner(new FileReader(file));
        StringBuilder result = new StringBuilder("");
        String line;
        int lineNum = 0;
        String[] lineArr;
        while (input.hasNext()) {
            lineNum++;
            line = input.nextLine();
            if (!line.matches("\\(by [\\w]+\\.txt \\d+\\)")) {
                result.append(line);
                result.append("\n");
            } else {
                line = line.substring(4, line.length() - 1);
                lineArr = line.split(" ");
                int extent = result.length();
                if (Objects.equals(lineArr[1], Integer.toString(lineNum))) {
                    if (lineNum != 1)
                        result.delete(extent - 1, extent);
                    result.append("\n-----------------------");
                    result.append(lineArr[0]).append("-----------------------\n");
                    if (input.hasNext())
                        input.nextLine();
                    lineNum++;
                }
            }
        }
        return result.toString();
    }
}