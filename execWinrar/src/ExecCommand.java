import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 
 */

/**
 * @author WangXiaoping
 * 
 */
public class ExecCommand {
	public static final String BACKSLASH="\\";
	public static Map<String, List<ClassPathEntry>> parserXmlClassPath(String fileName) {
		Map<String, List<ClassPathEntry>> dataMap = new HashMap<String, List<ClassPathEntry>>();
		SAXBuilder builder = new SAXBuilder(false);
		Element employees = null;
		try {
			Document document = builder.build(fileName);
			employees = document.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Element> elList = employees.getChildren();
		List<ClassPathEntry> srcItems = new ArrayList<ClassPathEntry>();
		List<ClassPathEntry> outputItems = new ArrayList<ClassPathEntry>();
		for (Element element : elList) {
			if ("src".equals(element.getAttribute("kind").getValue())) {
				String output=element.getAttribute("output")==null ? null:element.getAttribute("output").getValue();
				String path=element.getAttribute("path")==null ? null:element.getAttribute("path").getValue();
				srcItems.add(new ClassPathEntry("src", path, output));
			}
			if ("output".equals(element.getAttribute("kind").getValue())) {
				String output=element.getAttribute("output")==null ? null:element.getAttribute("output").getValue();
				String path=element.getAttribute("path")==null ? null:element.getAttribute("path").getValue();
				outputItems.add(new ClassPathEntry("output", path, output));
			}
		}
		dataMap.put("src", srcItems);
		dataMap.put("output", outputItems);
		return dataMap;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	public static String getPerfixName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		Properties cmdProperties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("cmd.properties");
			cmdProperties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (args.length < 4) {
			System.out.println("参数错误！");
			System.exit(0);
		}

		String workspacePath = args[0], projectName = args[1], resoucePath = args[2], resouceName = args[3];
		// String workspacePath= "F:\AllWorkSpace\indigo";
		// String projectName = "execWinrar";
		// String resoucePath="\execWinrar\src\ExecCommand.java";
		// String resouceName = "ExecCommand.java";
		//System.out.println(workspacePath+"\n"+projectName+"\n"+resoucePath+"\n"+resouceName);
		//项目路径
		String projectPath = workspacePath + BACKSLASH + projectName;
		// 相对路径  \execWinrar\src\
		String relativePath = resoucePath.substring(0,resoucePath.length()-resouceName.length());
		File file = new File(workspacePath + resoucePath);
		/** 获取command命令和项目配置信息 -begin **/
		String cmd = cmdProperties.getProperty("command");
		Map<String, List<ClassPathEntry>> pathMap = parserXmlClassPath(projectPath + BACKSLASH + ".classpath");
		List<ClassPathEntry> srcEntry = pathMap.get("src");
		List<ClassPathEntry> classEntry = pathMap.get("output");
		String perfix = getPerfixName(file.getName());
		String suffix = getExtensionName(file.getName());
		/** 获取command命令和项目配置信息 -end **/
		boolean isDirectory = file.isDirectory();
		boolean isClassPath = false;
		boolean isJava = resouceName.toLowerCase().endsWith(".java");
		String srcPath="",classPath=BACKSLASH+classEntry.get(0).getPath();
		System.out.println(srcPath);
		String curentFilePath = " " + file.getPath();
		String compressFilePaths="", compressedFile = " " + projectPath + BACKSLASH + projectName;
		for (int i = 0; i < srcEntry.size(); i++) {
			//判断文件是否在src目录
			String _tSrc = BACKSLASH+srcEntry.get(i).getPath();
			_tSrc=_tSrc.replace('/', '\\');
			int indexSrc = resoucePath.indexOf(_tSrc);
			if(indexSrc!=-1){
				isClassPath = true;
				srcPath = _tSrc;
				break;
			}
		}
		if(isDirectory){
			cmd += " -R";
			if(isClassPath){
				relativePath = resoucePath.replace(srcPath, classPath);
				relativePath = relativePath.substring(0,relativePath.lastIndexOf(BACKSLASH));
				compressFilePaths = curentFilePath.replace(srcPath, classPath);
			}
			else{
				//relativePath = resoucePath;
				relativePath = resoucePath.substring(0,resoucePath.lastIndexOf(BACKSLASH));
				compressFilePaths = curentFilePath;
			}
		}
		//single file
		else{
			if(isClassPath){
				relativePath = relativePath.replace(srcPath, classPath);
				curentFilePath = curentFilePath.replace(srcPath, classPath);
				compressFilePaths = isJava ? curentFilePath.replace(".java", ".class") : curentFilePath;
				if (!isFileExist(compressFilePaths)) {
					System.err.println("严重: File not Fond !!!"+curentFilePath.trim());
				}
				if(isJava){
					//处理内部类
					File _directory = new File(curentFilePath.substring(0,curentFilePath.lastIndexOf(BACKSLASH)).trim());
					File[] _files=_directory.listFiles();
					for (File f : _files) {
						String _fname = f.getName();
						if(_fname.endsWith(".class")&&_fname.startsWith(file.getName().replace(".java", "$"))){
							compressFilePaths += " "+f.getPath();
						}
					}
				}
			}
			else{
				compressFilePaths = curentFilePath;
			}
		}
		
		cmd += isDirectory ? " -ep1" : " -ep";
		cmd += " -ap" + (relativePath.substring(1).replaceAll("WebRoot/", "").replaceAll("WebRoot\\\\", ""));
		cmd += compressedFile + compressFilePaths;
		System.out.println(cmd);

		Runtime run = Runtime.getRuntime();
		try {
			Process p = run.exec(cmd);
			printOutStream(p.getInputStream());
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1) {// p.exitValue()==0表示正常结束，1：非正常结束
					System.out.println("命令执行失败!");
					p.destroy();
				}
			}
		} catch (Exception e) {
			System.out.println("命令执行异常!");
			e.printStackTrace();
		}
	}
	private static void printOutStream(InputStream is) {
		BufferedInputStream in = new BufferedInputStream(is);
		BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
		String lineStr;
		try {
			while ((lineStr = inBr.readLine()) != null)
				// 获得命令执行后在控制台的输出信息
				/** 经测试无法获取信息 **/
				System.out.println("message: "+new String(lineStr.getBytes("ISO-8859-1"),"GBK"));// 打印输出信息
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean isFileExist(String filePath){
		File file = new File(filePath.trim());
		return file.exists();
	}
	public static class ClassPathEntry{
		protected String kind;
		protected String path;
		protected String output;
		public ClassPathEntry(){
			
		}
		public ClassPathEntry(String kind, String path){
			this.kind = kind;
			this.path = path;
		}
		public ClassPathEntry(String kind, String path,String output){
			this.kind = kind;
			this.path = path;
			this.output = output;
		}
		public String getKind() {
			return kind;
		}
		public String getPath() {
			return path;
		}
		public String getOutput() {
			return output;
		}
	}
}
