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

import java.io.IOException;

public class JavassistCompiler {
    public static void main(String[] args) throws Exception {
        // 获取CtClass 实例的工具类 Class Type Class, 字节码类型的class 类
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = genericClass(pool);
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

        // 向ctClass 中初始化无参构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        String body = "{\nname=\"zhangsan\";\nage=23;\n}";
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);

        CtMethod ctMethod = new CtMethod(CtClass.voidType, "personInfo", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\nSystem.out.println(\"name=\"+name);\n");
        buffer.append("System.out.println(\"age=\"+age);\n}");
        ctMethod.setBody(buffer.toString());
        ctClass.addMethod(ctMethod);

        return ctClass;
    }

    private static void invokeInstance(CtClass ctClass) throws Exception {
        // 通过ctClass 获取 到其对应的Class
        Class clazz = ctClass.toClass();
        Object instance = clazz.newInstance();
        instance.getClass().getMethod("personInfo", new Class[]{})
                .invoke(instance, new Object[]{});
    }
}
