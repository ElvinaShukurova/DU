package org.example;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    @Argument(required = true, metaVar = "InputObjects")
    private List<String> listOfObjects = new ArrayList<>();

    @Option(name = "-h", metaVar = "HumanFormat")
    private boolean h;

    @Option(name = "-c", metaVar = "Summarize")
    private boolean c;

    @Option(name = "-si", metaVar = "Base")
    private boolean si;

    public int parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return 1;
        }

        Du du = new Du();
        try {
            du.output(listOfObjects, h, c, si);
        } catch (IllegalArgumentException e) {
            System.err.println("File not found");
            return 1;
        }
        return 0;
    }
}