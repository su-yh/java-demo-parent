package com.suyh.demo04;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 参考：
 * <ul>
 *     <li>@see <a href="https://www.jianshu.com/p/43424242846b">javassist Tutorial 1</a></li>
 *     <li>@see <a href="https://www.jianshu.com/p/b9b3ff0e1bf8">javassist Tutorial 2</a></li>
 *     <li>@see <a href="https://www.jianshu.com/p/7803ffcc81c8">javassist Tutorial 3</a></li>
 * </ul>
 *
 * <table border="1" id="tbl" width="500">
 *     <thead>
 *       <tr>
 *         <th>符号</th>
 *         <th>含义</th>
 *       </tr>
 *     </thead>
 *     <tbody>
 *       <tr>
 *         <td>$0, $1, $2, ...</td>
 *         <td>this and 方法的参数</td>
 *       </tr>
 *       <tr>
 *         <td>$args</td>
 *         <td>方法参数数组.它的类型为 Object[]</td>
 *       </tr>
 *       <tr>
 *         <td>$$</td>
 *         <td>所有实参。例如, m($$) 等价于 m($1,$2,...)</td>
 *       </tr>
 *       <tr>
 *         <td>$cflow(...)</td>
 *         <td>cflow 变量</td>
 *       </tr>
 *       <tr>
 *         <td>$r</td>
 *         <td>返回结果的类型，用于强制类型转换</td>
 *       </tr>
 *       <tr>
 *         <td>$w</td>
 *         <td>包装器类型，用于强制类型转换</td>
 *       </tr>
 *       <tr>
 *         <td>$_</td>
 *         <td>返回值</td>
 *       </tr>
 *       <tr>
 *         <td>$sig</td>
 *         <td>类型为 java.lang.Class 的参数类型数组</td>
 *       </tr>
 *       <tr>
 *         <td>$type</td>
 *         <td>一个 java.lang.Class 对象，表示返回值类型</td>
 *       </tr>
 *       <tr>
 *         <td>$class</td>
 *         <td>一个 java.lang.Class 对象，表示当前正在修改的类</td>
 *       </tr>
 *     <tbody>
 *   </table>
 */
public class JavassistCompiler {
    public static void main(String[] args) throws Exception {
        // 获取CtClass 实例的工具类 Class Type Class, 字节码类型的class 类
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = genericClass(pool);
        writeToFile1(ctClass);
        writeToFile2(ctClass);
        invokeInstance(ctClass);
    }

    private static CtClass genericClass(ClassPool pool) throws NotFoundException, CannotCompileException, IOException {
        CtClass ctClass = pool.makeClass("com.abc.Person");

        CtField nameField = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        nameField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(nameField);

        CtField ageField = new CtField(pool.getCtClass("int"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        ctClass.addMethod(CtNewMethod.getter("getName", nameField));
        ctClass.addMethod(CtNewMethod.setter("setName", nameField));
        ctClass.addMethod(CtNewMethod.getter("getAge", ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge", ageField));

        /*
         * 向ctClass 中添加无参构造器:
         * public Person() {
         *      name = "zhangsan";
         *      age = 23;
         * }
         */
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        // $0=this / $1,$2,$3... 代表方法参数
        String body = "{\nname=\"zhangsan\";\nage=23;\n}";
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);

        /*
         * 向ctClass 中添加带参构造器:
         * public Person(String name, String age) {
         *      this.name = name;
         *      this.age = age;
         * }
         */
        CtConstructor ctConstructorByParams = new CtConstructor(new CtClass[]{
                pool.get("java.lang.String"), CtClass.intType}, ctClass);
        // $0=this / $1,$2,$3... 代表方法参数
        String bodyByParams = "{\n$0.name=$1;\n$0.age=$2;\n}";
        ctConstructorByParams.setBody(bodyByParams);
        ctClass.addConstructor(ctConstructorByParams);

        /*
         * 添加一个方法
         * public void personInfo() {
         *      System.out.println("name="+name);
         *      System.out.println("age="+age);
         * }
         */
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "personInfo", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        StringBuffer buffer = new StringBuffer();
        // $0=this / $1,$2,$3... 代表方法参数
        buffer.append("{");
        buffer.append("System.out.println(\"name=\"+$0.name);");
        buffer.append("System.out.println(\"age=\"+$0.age);");
        buffer.append("}");
        ctMethod.setBody(buffer.toString());
        ctClass.addMethod(ctMethod);

        return ctClass;
    }

    private static void writeToFile1(CtClass ctClass) throws IOException, CannotCompileException, NotFoundException {
        ctClass.writeFile("e:/java-demo");
        // ctClass.writeFile();
    }

    private static void writeToFile2(CtClass ctClass) throws IOException, CannotCompileException {
        byte[] bytes = ctClass.toBytecode();
        File file = new File("e:/java-demo/Person.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    private static void invokeInstance(CtClass ctClass) throws Exception {
        // 通过ctClass 获取 到其对应的Class
        Class clazz = ctClass.toClass();
        Object instance = clazz.newInstance();
        instance.getClass().getMethod("personInfo", new Class[]{})
                .invoke(instance, new Object[]{});
    }
}
