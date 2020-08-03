package com.suyh.test.method;

import com.suyh.MockApplication;
import com.suyh.compoent.MethodTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockApplication.class)
//@WebAppConfiguration
@Slf4j
public class SpyTest {
    //gg(boolean)方法：控制台打印: coming.........
    //g(boolean)方法：控制台打印: coming.........g
    //h()方法：控制台打印： coming.........h
    //gg(boolean)方法在g(boolean)和h()方法执行完之后：控制台打印： result data is *

    /**
     * 使用代理：如下方式
     * 或Mockito.spy(MethodTest.class)获得代理对象
     */
    @SpyBean
    private MethodTest spyTest;

    /**
     * 不代理，直接运行程序
     * 控制台打印：
     *  coming.........
     *  coming.........g
     *  coming.........h
     *  result data is d
     *  d
     */
    @Test
    public void test(){
        log.info(spyTest.gg(false));
    }

    /**
     * 不使用代理：让g方法抛异常
     * 控制台打印
     * coming.........
     * coming.........g
     * 由于抛异常，程序中断
     */
    @Test
    public void test2(){
        log.info(spyTest.gg(true));
    }

    /**
     * 使用代理对象，代理g方法，并让其返回“test”
     * 第一种方式：使用thenReturn("test");
     * 控制台打印:
     * coming.........g
     * coming.........
     * coming.........h
     * result data is test
     * test
     *
     */
    @Test
    public void test3(){
        // 直接主动调用g 方法，并指定其返回值为"test"
        Mockito.when(spyTest.g(false)).thenReturn("test");

        log.info(spyTest.gg(false));
    }
    /**
     * 使用代理对象，代理g方法，并让其返回“test”
     * 第二种方式：使用doReturn("test");
     * 其中doReturn("test")后执行.when(spyTest).g(false)方法
     * 控制台打印:
     * coming.........
     * coming.........h
     * result data is test
     * test
     *
     */
    @Test
    public void test4(){
        // g 方法被代理了，其原来的g 方法将不会被执行
        // 同时指定了参数还需要匹配上
        Mockito.doReturn("test").when(spyTest).g(false);
        log.info(spyTest.gg(false));
        log.info(spyTest.gg(true));
    }
    /**
     * test3-test4比较
     * 正常执行顺序
     *  coming.........
     *  coming.........g
     *  coming.........h
     *  result data is d
     *  d
     * 使用Mockito.when(spyTest.g(false)).thenReturn("test");方式
     * g(boolean)方法会在调用主方法gg(boolean)前执行，不管执行的结果是啥，都会按照mock的数据“test”返回
     *
     * 使用Mockito.doReturn("test").when(spyTest).g(false);方式
     * g(boolean)方法不会被执行，当时会将它的返回值赋值为：“test”。
     * 在主方法gg(boolean)执行到g(boolean)时，不执行g(boolean)方法体，直接将值“test'”返回。后续方法照常执行
     */
//#######################################################################################
    /**
     * 使用代理对象，代理gg方法，并让其返回“test”
     * 第一种方式：使用thenReturn("test");
     * 控制台打印:
     * coming.........g
     *
     */
    @Test(expected = IllegalAccessError.class)
    public void test5(){
        Mockito.when(spyTest.g(true)).thenReturn("test");
        log.info(spyTest.gg(true));
    }

    /**
     * 使用代理对象，代理gg方法，并让其返回“test”
     * 第二种方式：使用doReturn("test");
     * 其中doReturn("test")后执行.when(spyTest).g(true)方法--抛异常
     * 控制台打印:
     * coming.........
     * coming.........h
     * result data is test
     * test
     *
     */
    @Test
    public void test6(){
        Mockito.doReturn("test").when(spyTest).g(true);
        log.info(spyTest.gg(true));
    }

    /**
     * test5-test6比较
     * 故意赋值让其方法抛异常。
     * 正常执行顺序
     *  coming.........
     *  coming.........g
     *  coming.........h
     *  result data is d
     *  d
     *
     *  因为：Mockito.when(spyTest.g(true)).thenReturn("test");会执行方法体的内容，则g(boolean)方法体会被执行，会抛出异常中断程序运行
     *  而Mockito.doReturn("test").when(spyTest).g(true);方法体不会被执行，则不会抛出异常，不会造成程序中断
     */

    //##################################无返回值######################
    /**
     * 无返回值方法的代理
     * 方法为h()
     * 因此方法对代码不造成任何影响，所以可以不执行它
     * 可以使用：doNothing
     * 代码：Mockito.doNothing().when(spyTest).h();
     */
    @Test
    public void test7(){
        Mockito.doNothing().when(spyTest).h();

        log.info(spyTest.gg(false));
    }

    /**
     * 测试
     * coming.........
     * coming.........g
     * result data is d
     * d
     */
    @Test
    public void test10(){
        Mockito.doReturn("23").when(spyTest).g(true);
        Mockito.doNothing().when(spyTest).h();

        log.info(spyTest.gg(false));
    }
}
