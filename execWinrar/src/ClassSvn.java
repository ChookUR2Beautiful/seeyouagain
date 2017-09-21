import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ClassSvn 
{
    static int count =0;
    private static String javaPath = "D:\\ajava";
    private static String classPath = "D:\\aclasses";


    public static void main(String[] args) 
    {
        File f1 = new File(javaPath);
        File f2 = new File(classPath);
        if(f1.exists() && f2.exists())
        {
            createPath(f1);
        }
        System.out.println("��������"+count+"��class�ļ�");
    }

    /*
     *  ����java�ļ��ҵ���Ӧ��class�ļ�������lclass�ļ����Ƶ���ӦĿ¼�£�ͬʱɾ��java�ļ�
     */
    public  static void createPath(File file)
    {
        if(file.exists())
        {
            File[] files = file.listFiles();
            for(File f : files)
            {
                if(f.isDirectory())
                {//�ж��Ƿ���Ŀ¼
                    createPath(f);
                }
                else
                {
                    if(f.getName().endsWith(".java"))
                    {
                        //ȥ����չ��֮����ļ���
                        final String temp = f.getName().substring(0, f.getName().indexOf(".java"));

                        //class �����ļ���
                        final String path = classPath + f.getPath().substring(javaPath.length(), f.getPath().lastIndexOf("\\")+1);
                        File[] filels = new File(path).listFiles();//��ȡ�ļ����µ������ļ�
                        for(int i=0;i<filels.length;i++)
                        {
                            if(filels[i].isFile())
                            {
                                if(filels[i].getName().indexOf(temp) != -1)//����$��classҲcopy����
                                {
                                    count++;
                                    File classfile = new File(path+filels[i].getName());
                                    File javafile = new File(f.getPath().substring(0,f.getPath().lastIndexOf("\\")+1)+filels[i].getName());
                                    try 
                                    {
                                        copyFile(classfile,javafile);
                                        f.delete();
                                    }
                                    catch (Exception e) 
                                    {
                                        e.printStackTrace();
                                    }
                                    System.out.println(filels[i].getName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /** 
     * ��F1���Ƶ�F2 
     * 
     */  
    public static void copyFile(File f1, File f2) throws Exception 
    {  
        int length = 2097152;  
        FileInputStream in = new FileInputStream(f1);  
        FileOutputStream out = new FileOutputStream(f2);  
        byte[] buffer = new byte[length];  

        int len = 0;  
        while ((len = in.read(buffer)) != -1) 
        {  
           out.write(buffer, 0, len);
        }  
        in.close();  
        out.flush();  
        out.close();  
    }  
}