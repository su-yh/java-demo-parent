class A {}
class B extends A {}

public class Main {
    public static void main(String[] args) {
        // 主要判断前者是后者的父类
        System.out.println(A.class.isAssignableFrom(B.class)); // true
        System.out.println(B.class.isAssignableFrom(A.class)); // false
    }
}
