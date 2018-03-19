import org.kohsuke.args4j.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarLauncher {
    @Option(name = "-u", required = true, metaVar = "unarchiveFile", usage = "Input file to unarchive")
    private String reTar;

    @Option(name = "-out", metaVar = "archiveFile", usage = "Output archive")
    private String toTar;

    @Argument
    private List<String> args = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new TarLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar tar.jar -u input.txt");
            System.err.println("java -jar tar.jar input1.txt ... inputN.txt -out output.txt.txt");
            parser.printUsage(System.err);
            return;
        }

        if (reTar != null && toTar != null){
            System.err.println("Please Select only one option");
            parser.printUsage(System.err);
        }

        if (reTar != null){
            if (reTar.matches("(.+/)*\\w+\\.txt")) {
                Scanner input = new Scanner(new FileReader(reTar));
                Tar.u(input);
                input.close();
            } else {
                System.err.println("Wrong argument format entered");
                parser.printUsage(System.err);
            }
        }

        if (toTar != null ){
            if (toTar.matches("(.+/)*\\w+\\.txt")) {
                FileWriter output = new FileWriter(toTar);
                Tar.out(output, args);
            } else {
                System.err.println("Wrong argument format entered");
                parser.printUsage(System.err);
            }
        }
    }
}
