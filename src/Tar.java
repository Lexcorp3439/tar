import java.io.*;
import java.util.Scanner;

public class Tar {

    static void out(FileWriter output, String[] args) throws IOException {
        int argsSise = args.length;
        int whichArg = 0;
        for (String file : args) {
            whichArg++;
            Scanner input = new Scanner(new FileReader(file));
            Scanner input1 = new Scanner(new FileReader(file));
            output.write("(by " + file.replaceAll("(.+/)*", ""));
            output.write(" " + length(input) + ")" + "\n");
            while (input1.hasNext()) {
                output.write(input1.nextLine());
                if (argsSise >= whichArg)
                    output.write("\n");
            }
            input.close();
            input1.close();
        }
        output.close();
    }

    static void u(Scanner input) throws IOException {
        String fileName;
        int lineNum = 0;
        int size;
        String[] lineArr;
        while (input.hasNext()) {
            fileName = input.nextLine();
            fileName = fileName.substring(4, fileName.length() - 1);
            lineArr = fileName.split(" ");
            size = Integer.parseInt(lineArr[1]);
            File output = new File(lineArr[0]);
            FileWriter writer = new FileWriter(output);
            while (lineNum < size) {
                lineNum++;
                if (input.hasNext())
                    writer.write(input.nextLine());
                if (input.hasNext())
                    writer.write("\n");
            }
            writer.close();
            lineNum = 0;
        }
    }

    private static int length(Scanner file){
        int length = 0;
        while (file.hasNext()){
            file.nextLine();
            length++;
        }
        return length;
    }
}

