package com.jjt.schedule.test;

import com.jjt.schedule.util.MD5Util;
import org.testng.annotations.Test;

/**
 * ClassName: TestMD5Util
 * Package: com.jjt.schedule.test
 * Description:
 *
 * @Author jjt
 * @Create 2023/12/15 12:24
 * @Version 1.0
 */
public class TestMD5Util {

    @Test
    public void testEncrypt() {
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
