package in.ac.iiitd.pag.structureextractor;

import in.ac.iiitd.pag.util.Canonicalizer;
import in.ac.iiitd.pag.util.FileUtil;
import in.ac.iiitd.pag.util.StringUtil;
import in.ac.iiitd.pag.util.StructureUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StructureExtractor {
	
		
	public static String extract(String method, List<String> operators) {
		String structure = "";
		
		
		List<String> algoElements = StructureUtil.getAlgo(method,false);
		List<String> flattenedAlgo = StructureUtil.flattenAlgo(algoElements);
		List<String> flattendedAlgoWithoutMethods = new ArrayList<String>();		
		for(String item: flattenedAlgo) {
			if (!item.equalsIgnoreCase("method")) {
				if (item.contains("method")) {
					item = item.replace("method", "");
				}
				flattendedAlgoWithoutMethods.add(item);
			}
		}
		
		structure = StringUtil.getAsCSV(flattendedAlgoWithoutMethods);
		structure = structure.replaceAll(",", " ");
		structure = Canonicalizer.canonicalize(structure, operators);
				
		return structure;
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Structure Extractor Version 1.0");
			System.out.println("Usage: StructureExtractor java_file");
			System.out.println("java_file is the path to Java file containing *one* method.");
			return;
		}
		
		String method = FileUtil.readFromFile(args[0]);		
		System.out.println("Coverting method in file " + args[0]);
		System.out.println(method);
		String operatorsFile = "canonicalizedOperators.txt"; 		
		List<String> operators = FileUtil.readFromFileAsList(operatorsFile);
		System.out.println(StructureExtractor.extract(method, operators));
	}

	
}
