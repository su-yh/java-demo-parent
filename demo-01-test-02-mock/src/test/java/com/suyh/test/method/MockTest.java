package com.suyh.test.method;

import com.suyh.MockApplication;
import com.suyh.compoent.MethodTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockApplication.class)
//@WebAppConfiguration
@Slf4j
public class MockTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //gg(boolean)方法：控制台打印: coming.........
    //g(boolean)方法：控制台打印: coming.........g
    //h()方法：控制台打印： coming.........h
    //gg(boolean)方法在g(boolean)和h()方法执行完之后：控制台打印： result data is *


    /**
     * 使用代理：如下方式
     * 或Mockito.mock(MethodTest.class)获得代理对象
     */
    @MockBean
    private MethodTest mockTest;


    /**
     * 使用mock方式将会把对象给mock掉，则对象中的方法将不会被执行，但是使用到某方法时可以mock值，供调用方法使用
     */

    /**
     * 访问主方法体gg(boolean)
     * 返回null
     */
    @Test
    public void test1() {
        log.info(mockTest.gg(false));
    }

    /**
     * mock方法gg(boolean),使其在使用到gg(boolean)方法时，返回:"test"
     */
    @Test
    public void test2() {
        Mockito.when(mockTest.gg(false)).thenReturn("test");

        log.info(mockTest.gg(false));
    }

    /**
     * mock方法gg(boolean),使其在使用到gg(boolean)方法时，返回:"test"
     */
    @Test
    public void test3() {
        Mockito.doReturn("test").when(mockTest).gg(false);

        log.info(mockTest.gg(false));
    }

    /**
     * mock方法gg(boolean),使其在使用到gg(boolean)方法时，返回:"test"
     */
    @Test
    public void test4() {
        Mockito.when(mockTest.gg(false)).thenAnswer(new Answer<String>() {
            //Answer<T>   T为方法的返回值类型
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "test";
            }
        });

        log.info(mockTest.gg(false));
    }

    /**
     * mock方法gg(boolean),使其在执行到此方法时抛出自定义异常
     */
    @Test
    public void test5() {
        Mockito.when(mockTest.gg(false)).thenThrow(new IllegalArgumentException("单元测试异常"));

        log.info(mockTest.gg(true));    // mock 不匹配

        // 异常断言捕获
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("单元测试异常");
        log.info(mockTest.gg(false));   // mock 匹配
    }

    //对与返回值类型为void的操作有
    //1.直接mock对象，即此对象中所有的方法都不会被执行
    //2.使用Mockito.doNothing().when(mockObject).method();标记执行该方法时不做任何操作
}
