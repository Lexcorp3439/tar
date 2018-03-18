import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Tar {

    public static void main(String[] args) throws IOException {
        int aLen = args.length;

        if (aLen < 2 ||
                (aLen > 2 && !args[aLen - 2].equals("-out")) ||
                (aLen == 2 && !args[0].equals("-u")))
            throw new IllegalArgumentException("Введен неверный формат аргументов");

        if (aLen > 2 && args[aLen - 2].equals("-out")) {
            if (!args[aLen - 1].matches("(\\w+.txt\\\\)*\\w+\\.txt"))
                throw new IllegalArgumentException("Введен неверный формат аргументов");
            File output = new File(args[aLen - 1]);
            FileWriter writer = new FileWriter(output);
            writer.write(out(args, aLen - 2));
            writer.flush();
            writer.close();
        }

        if (aLen == 2 && args[0].equals("-u"))
            u(args[1]);
    }

    static String out(String[] args, int size) throws IOException {
        StringBuilder result = new StringBuilder("");
        int i = 0;
        int sizeOut = 0;
        for (String file : args) {
            if (!args[args.length - 1].matches("\\w+\\.txt"))
                throw new IllegalArgumentException("Введен неверный формат аргументов");
            if (sizeOut == size)
                break;
            Scanner input = new Scanner(new FileReader(file));
            while (input.hasNext()) {
                result.append(input.nextLine());
                result.append("\n");
                i++;
            }
            result.append("(by ").append(file).append(" ").append(i + 1).append(")");
            sizeOut++;
            if (sizeOut != size) {
                result.append("\n\n");
                i += 2;
            }
        }
        return result.toString();

    }

    static void u(String file) throws IOException {
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
                    File output = new File(lineArr[0]);
                    FileWriter writer = new FileWriter(output);
                    writer.write(result.toString());
                    writer.flush();
                    writer.close();
                    result = new StringBuilder("");
                    if (input.hasNext())
                        input.nextLine();
                    lineNum++;
                }
            }
        }
    }
}

