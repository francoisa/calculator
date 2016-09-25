package net.jmf.calc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import net.jmf.antlr4.LabeledExprLexer;
import net.jmf.antlr4.LabeledExprParser;

public class Calc {
	private static Map<String, Integer> sMap;
	
	private static Map<String, Integer> getMap() {
		sMap = sMap == null ? new HashMap<>() : sMap;
		return sMap;
	}
	
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        if (inputFile == null) {
        	Console console = System.console();
        	String line = console.readLine("Type 'quit' when done> ");
        	while (!"quit".equals(line)) {
        		parse(line);
        		line = console.readLine("> ");
        	}
        }
        else {
        	 BufferedReader br = new BufferedReader(new FileReader(inputFile));
        	 try {
	        	 String line = null;
	        	 while ((line = br.readLine()) != null) {
	        		 parse(line);
	        	 }
        	 }
        	 finally {
        		 br.close();
        	 }
        }
    }
    
    public static String parse(String str) throws Exception {
    	if (!str.endsWith("\n")) str += "\n";
    	ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(bis);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog(); // parse

        EvalVisitor eval = new EvalVisitor(getMap());
        eval.visit(tree);
        return eval.getValue().toString();
    }
}
