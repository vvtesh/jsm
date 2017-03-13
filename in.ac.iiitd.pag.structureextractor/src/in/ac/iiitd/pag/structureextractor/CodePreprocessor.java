package in.ac.iiitd.pag.structureextractor;

import in.ac.iiitd.pag.util.Canonicalizer;
import in.ac.iiitd.pag.util.FileUtil;
import in.ac.iiitd.pag.util.StringUtil;
import in.ac.iiitd.pag.util.StructureUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CodePreprocessor {
	
		
	public static String extract(String method) {
		String structure = "";
		String newMethod = method;
		
		newMethod = StructureUtil.stripComments(method);
		
		
		List<String> algoElements = StructureUtil.getAlgo(newMethod,true);
		Set<String> identifiers = new HashSet<String>();
		for(int i=0; i<algoElements.size(); i++) {
			identifiers.add(algoElements.get(i));
		}
		int varIndex = 1;
		String anonVar = "id" + varIndex;
		Iterator<String> iter = identifiers.iterator();
		while(iter.hasNext()) {
			newMethod = newMethod.replaceAll("\\b"+iter.next()+"\\b", anonVar);			
			varIndex++;
			anonVar = "id" + varIndex;
		}
		newMethod = newMethod.substring(newMethod.indexOf('\n')+1);		
		newMethod = newMethod.substring(0,newMethod.lastIndexOf('\n'));
		newMethod = newMethod.substring(0,newMethod.lastIndexOf('\n'));
		return newMethod;
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Code Preprocessor Version 1.0.");
			System.out.println("Usage: CodePreprocessor java_file");
			System.out.println("Removes comments. Anonymizes identifiers.");
			System.out.println("java_file is the path to Java file containing *one* method.");
			return;
		}
		
		String method = FileUtil.readFromFile(args[0]);				
		
		System.out.println(CodePreprocessor.extract(method));
	}

	
}
