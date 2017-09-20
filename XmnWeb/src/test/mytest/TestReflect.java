package mytest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestReflect {  
  
    /** 
     * @param args 
     *  
     * Java反射是Java语言的一个很重要的特征，它使得Java具有了"动态性"。 
     * 在Java运行环境中，对于任意一个类，能否知道这个类有哪些属性和方法？ 
     * 对于任意一个对象，能否调用它的任意一个方法？答案是肯定的。这种动态获取 
     * 类的信息以及动态调用对象的方法的功能来自于Java语言的反射（Reflection）机制。 
     * Reflection是Java被视为动态（或准动态）语言的一个关键性质，这个机制允许程序在运行时透过 
     * Reflection APIs取得任何一个已知名称的class的内部信息，包括modifiers（诸如public，static等等）、superclass（例如object）、 
     * 实现之interfaces（如Serializable），也包括fields和methods的所有信息，并可于运行时改变fields内容或者调用methods。 
     * 程序运行时，允许改变程序结构或者变量类型，这种语言称为动态语言。从这个观点看，Perl，Python，Ruby是动态语言，C++，Java，C#不是动态语言。 
     * 在JDK中，主要由以下类来实现Java反射机制，这些类都位于java.lang.reflect包中： 
     * Class类：代表一个类。 
     * Field类：代表类的成员变量。 
     * Method类：代表类的方法。 
     * Constructor类：代表类的构造方法。 
     * Array类：提供了动态创建数组，以及访问数组的元素的静态方法。 
     */  
    public static void main(String[] args) {  
          /* 
          * 通过一个对象获取完整的包名和类名 
          */  
//      Animal dog=new Dog();  
//      System.out.print(dog.getClass().getName());//打印：com.reflect.sym.Dog  
         /* 
          * 所有类的对象其实都是Class的实例 
          */  
//      Class<?> dog1=null;  
//      Class<?> dog2=null;  
//      Class<?> dog3=null;  
//      try {  
//          dog1=Class.forName("mytest.Dog");  
//      } catch (ClassNotFoundException e) {  
//          e.printStackTrace();  
//      }  
//         dog2=new Dog().getClass();  
//         dog3=Dog.class;  
//         System.out.println("类名称:"+dog1.getName());//类名称:com.reflect.sym.Dog  
//         System.out.println("类名称:"+dog2.getName());//类名称:com.reflect.sym.Dog  
//         System.out.println("类名称:"+dog3.getName());//类名称:com.reflect.sym.Dog  
           
           
        /* 
         * 通过Class实例化其他类的对象 
         * 在使用Class实例化其他类的对象的时候，一定要自己定义无参数的构造函数 
         */  
//      Class<?> dog = null;  
//      try {  
//          dog = Class.forName("mytest.Dog");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Dog dogd = null;  
//      try {  
//          dogd = (Dog) dog.newInstance();  
//      } catch (InstantiationException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      } catch (IllegalAccessException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      dogd.setAge(4);  
//      System.out.print(dogd);  
//      /*打印结果：age为：4*/  
        /* 
         * 通过Class调用其他类中的构造函数（也可以通过这种方式通过Class创建其他类的对象） 
         * 此时构造方法可以有参数 
         */  
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Cat cat1=null;  
//      Cat cat2=null;  
//      Cat cat3=null;  
//      Cat cat4=null;  
//      Constructor<?>cons[]=cat.getConstructors();  
//      for(Constructor<?> con:cons){  
//          System.out.println(con);  
//      }  
//      /*  
//       * 打印结果：public com.reflect.sym.Cat(int)  
//       *        public com.reflect.sym.Cat(java.lang.String)  
//       *        public com.reflect.sym.Cat(int,java.lang.String)  
//       *        public com.reflect.sym.Cat()  
//       */  
//      try {  
//          /*  
//           * 特别要注意这里的构造函数与类中的构造函数顺序不一定相同  
//           */  
//          cat4=(Cat) cons[3].newInstance();  
//          cat3=(Cat) cons[2].newInstance(20);  
//          cat2=(Cat) cons[1].newInstance("kate");  
//          cat1=(Cat) cons[0].newInstance(20,"Kate");  
//      } catch (IllegalArgumentException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      } catch (InstantiationException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      } catch (IllegalAccessException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      } catch (InvocationTargetException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      System.out.println(cat1);//age：20name：null  
//      System.out.println(cat2);//age：0name：kate  
//      System.out.println(cat3);//age：20name：Kate  
//      System.out.println(cat4);//age：0name：null  
        /* 
         * 获取其他类的继承的接口 
         */  
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Class<?>intes[]=cat.getInterfaces();  
//      for(Class<?> inte:intes){  
//          System.out.print("实现的接口："+inte.getName());//实现的接口：com.reflect.sym.Animal  
//      }  
        /* 
         * 获取其他类的继承的父类 
         */  
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Class<?>father= cat.getSuperclass();  
//      System.out.print("实现的父类："+father.getName());//实现的父类：java.lang.Object  
        
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Constructor<?>[]constrouts=cat.getConstructors();  
//      for(Constructor<?> constrout:constrouts){  
//          System.out.println("拥有的构造函数："+constrout);  
//      }  
//      /*打印结果：拥有的构造函数：public com.reflect.sym.Cat(int)  
//                                拥有的构造函数：public com.reflect.sym.Cat(java.lang.String)  
//                                拥有的构造函数：public com.reflect.sym.Cat(int,java.lang.String)  
//                                拥有的构造函数：public com.reflect.sym.Cat()*/  
//        
          
        /* 
         * 获取构造函数的修饰符 
         */  
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//      Constructor<?>[]constrouts=cat.getConstructors();  
//      for(int i=0;i<constrouts.length;i++){  
//          Class<?>[] p=constrouts[i].getParameterTypes();
//          int mo=constrouts[i].getModifiers();  
//           System.out.print(Modifier.toString(mo)+" ");//这个打印出“public”  
//           System.out.print(constrouts[i].getName());//com.reflect.sym.Cat  
//           System.out.print("(");  
//           for(int j=0;j<p.length;++j){  
//                  System.out.print(p[j].getName()+" arg"+i);  
//                  if(j<p.length-1){  
//                      System.out.print(",");  
//                  }  
//              }  
//           System.out.println("){}");  
//      }  
//      /*打印结果：public com.reflect.sym.Cat(int arg0){}  
//                public com.reflect.sym.Cat(java.lang.String arg1){}  
//                public com.reflect.sym.Cat(int arg2,java.lang.String arg2){}  
//                public com.reflect.sym.Cat(){}*/  
        /* 
         * 打印出方法，有时候出现异常的方法 
         */  
//      Class<?>cat=null;  
//      try {  
//          cat=Class.forName("mytest.Cat");  
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//       Method method[]=cat.getMethods();
//       for(int i=0;i<method.length;++i){  
//              Class<?> returnType=method[i].getReturnType();  
//              Class<?> para[]=method[i].getParameterTypes();  
//              int temp=method[i].getModifiers();  
//              System.out.print(Modifier.toString(temp)+" ");  
//              System.out.print(returnType.getName()+"  ");  
//              System.out.print(method[i].getName()+" ");  
//              System.out.print("(");  
//              for(int j=0;j<para.length;++j){  
//                  System.out.print(para[j].getName()+" "+"arg"+j);  
//                  if(j<para.length-1){  
//                      System.out.print(",");  
//                  }  
//              }  
//              Class<?> exce[]=method[i].getExceptionTypes();  
//              if(exce.length>0){  
//                  System.out.print(") throws ");  
//                  for(int k=0;k<exce.length;++k){  
//                      System.out.print(exce[k].getName()+" ");  
//                      if(k<exce.length-1){  
//                          System.out.print(",");  
//                      }  
//                  }  
//              }else{  
//                  System.out.print(")");  
//              }  
//              System.out.println();  
//          }  
//      /* 打印结果：public java.lang.String  toString ()  
//       public java.lang.String  getName ()  
//       public void  setName (java.lang.String arg0)  
//       public void  call (java.lang.String arg0)  
//       public java.lang.String  eat ()  
//       public int  getAge ()  
//       public void  setAge (int arg0)  
//       public final void  wait () throws java.lang.InterruptedException   
//       public final void  wait (long arg0,int arg1) throws java.lang.InterruptedException   
//       public final native void  wait (long arg0) throws java.lang.InterruptedException   
//       public native int  hashCode ()  
//       public final native java.lang.Class  getClass ()  
//       public boolean  equals (java.lang.Object arg0)  
//       public final native void  notify ()  
//       public final native void  notifyAll ()*/  
//        
        /* 
         * 获取其他类的全部属性 
         */  
       Class<?> cat = null;  
          try {  
              cat = Class.forName("mytest.Cat");  
          } catch (Exception e) {  
              e.printStackTrace();  
          }  
          System.out.println("===============本类属性========================");  
          // 取得本类的全部属性  
          Field[] field = cat.getDeclaredFields();  
          for (int i = 0; i < field.length; i++) {  
              // 权限修饰符  
              int mo = field[i].getModifiers();  
              String priv = Modifier.toString(mo);  
              // 属性类型  
              Class<?> type = field[i].getType();  
              System.out.println(priv + " " + type.getName() + " "  
                      + field[i].getName() + ";");  
          }  
          System.out.println("===============实现的接口或者父类的属性========================");  
          // 取得实现的接口或者父类的属性  
          Field[] filed1 = cat.getFields();  
          for (int j = 0; j < filed1.length; j++) {  
              // 权限修饰符  
              int mo = filed1[j].getModifiers();  
              String priv = Modifier.toString(mo);  
              // 属性类型  
              Class<?> type = filed1[j].getType();  
              System.out.println(priv + " " + type.getName() + " "  
                      + filed1[j].getName() + ";");  
          }  
//         /*打印结果： ===============本类属性========================  
//               int age;  
//               java.lang.String name;  
//              ===============实现的接口或者父类的属性========================  
//              public static final int data;*/  
            /* 
             * 通过反射调用其他类中的方法 
             */  
//          Class<?> cat = null;  
//          try {  
//              cat = Class.forName("com.reflect.sym.Cat");  
//          } catch (Exception e) {  
//              e.printStackTrace();  
//          }   
//          try{  
//          //调用Cat类中的toString方法,这里只是显示控制台上的，只显示出System.out.print("")中的内容  
//               Method method=null;  
//              method=cat.getMethod("toString");  
//              method.invoke(cat.newInstance());  
//              //调用Cat的call方法  
//              method=cat.getMethod("call", String.class);  
//              method.invoke(cat.newInstance(),"Rollen");  
//          }catch (Exception e) {  
//              e.printStackTrace();  
//          }  
//            
//    /*        打印结果：Rollen*/  
             /* 
              * 获取get和set方法 
              */  
//      Class<?> cat = null;  
//      Object obj = null;  
//      try {  
//          cat = Class.forName("com.reflect.sym.Cat");  
//      } catch (Exception e) {  
//          e.printStackTrace();  
//      }  
//      try {  
//          obj = cat.newInstance();  
//      } catch (Exception e) {  
//          e.printStackTrace();  
//      }  
//      Method method;  
//      //此处注意要先set后get  
//      try {  
//          method = obj.getClass().getMethod("set" + "Name", String.class);  
//          try {  
//              method.invoke(obj, "Kate");  
//              method = obj.getClass().getMethod("get" + "Name");  
//              System.out.println(method.invoke(obj));  
//          } catch (IllegalArgumentException e) {  
//              // TODO Auto-generated catch block  
//              e.printStackTrace();  
//          } catch (IllegalAccessException e) {  
//              // TODO Auto-generated catch block  
//              e.printStackTrace();  
//          } catch (InvocationTargetException e) {  
//              // TODO Auto-generated catch block  
//              e.printStackTrace();  
//          }  
//                
//      } catch (SecurityException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      } catch (NoSuchMethodException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
        //打印结果：Kate  
            /* 
             * 通过反射操作属性  
             */  
//        
//      Class<?> cat = null;  
//       Object obj = null;  
//         
//       try {  
//          cat = Class.forName("com.reflect.sym.Cat");  
//           try {  
//              obj = cat.newInstance();  
//              Field field;  
//              try {  
//                  field = cat.getDeclaredField("name");  
//                   field.setAccessible(true);  
//                   field.set(obj, "Kate");  
//                   System.out.println(field.get(obj));  
//              } catch (SecurityException e) {  
//                  // TODO Auto-generated catch block  
//                  e.printStackTrace();  
//              } catch (NoSuchFieldException e) {  
//                  // TODO Auto-generated catch block  
//                  e.printStackTrace();  
//              }  
//           
//          } catch (InstantiationException e) {  
//              // TODO Auto-generated catch block  
//              e.printStackTrace();  
//          } catch (IllegalAccessException e) {  
//              // TODO Auto-generated catch block  
//              e.printStackTrace();  
//          }  
//     
//            
//      } catch (ClassNotFoundException e) {  
//          // TODO Auto-generated catch block  
//          e.printStackTrace();  
//      }  
//         /*  
//          * 打印结果：Kate  
//          */  
        /* 
         * 通过反射取得并修改数组的信息 
         */  
//      int[] temp={1,2,3,4,5};  
//        Class<?>cat=temp.getClass().getComponentType();  
//        System.out.println("数组类型： "+cat.getName());  
//        System.out.println("数组长度  "+Array.getLength(temp));  
//        System.out.println("数组的第一个元素: "+Array.get(temp, 0));  
//        Array.set(temp, 0, 100);  
//        System.out.println("修改之后数组第一个元素为： "+Array.get(temp, 0));   
//      /*  打印结果：数组类型： int  
//                             数组长度  5  
//                             数组的第一个元素: 1  
//                              修改之后数组第一个元素为： 100*/  
        /* 
         * 反射修改数组的大小 
         *  
         */  
//       int[] temp={1,2,3,4,5,6,7,8,9};  
//          Class<?>arr=temp.getClass().getComponentType();  
//          Object newArr=Array.newInstance(arr, 15);  
//          int co=Array.getLength(temp);  
//          System.arraycopy(temp, 0, newArr, 0, co);  
//          int[] newTemp=(int[])newArr;  
//          Class<?>c=temp.getClass();  
//          if(!c.isArray()){  
//              return;  
//          }  
//          System.out.println("数组长度为： "+Array.getLength(temp));  
//          for (int i = 0; i < Array.getLength(temp); i++) {  
//              System.out.print(Array.get(temp, i)+" ");  
//          }  
//          System.out.println("=====================");  
//          String[] atr={"a","b","c"};  
//          Class<?>arr1=atr.getClass().getComponentType();  
//          Object newArr1=Array.newInstance(arr1, 8);  
//          int co1=Array.getLength(atr);  
//          System.arraycopy(atr, 0, newArr1, 0, co1);  
//          String[] str1=(String[])newArr1;  
//          Class<?>c1=atr.getClass();  
//          if(!c1.isArray()){  
//              return;  
//          }  
//          System.out.println("数组长度为： "+Array.getLength(atr));  
//          for (int i = 0; i < Array.getLength(atr); i++) {  
//              System.out.print(Array.get(atr, i)+" ");  
//          }  
//         /*打印结果： 数组长度为： 9  
//          1 2 3 4 5 6 7 8 9 =====================  
//                          数组长度为： 3  
//          a b c*/   
        /* 
         *     如何获取类加载器 
         */  
//      Cat cat=new Cat();  
//       System.out.println("类加载器  "+cat.getClass().getClassLoader().getClass().getName());  
//      /* 打印结果：类加载器  sun.misc.Launcher$AppClassLoader*/  
//      /* 其实在java中有三种类类加载器。  
//  
//       1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。  
//  
//       2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类  
//  
//       3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。*/  
          
        /* 
         * 动态代理 
         */  
//          MyInvocationHandler demo = new MyInvocationHandler();  
//            Animal sub = (Animal) demo.bind(new Cat());  
//             sub.call("Kate");  
          /*   打印结果：Kate*/  
    }  
  
}  