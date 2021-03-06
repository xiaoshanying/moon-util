package com.moon.lang;

import com.moon.lang.support.StringSupport;
import com.moon.util.Console;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author benshaoye
 * @date 2018/9/12
 */
class SupportUtilTestTest {

    @Test
    void testSetString() {
        String str = "benshaoye";
        char[] chars = StringSupport.setString(null, 0, str);
        String now = new String(chars, 0, str.length());

        assertFalse(Arrays.equals(chars, str.toCharArray()));
        assertTrue(now.equals(str));

        chars = StringSupport.setString(chars, chars.length, str);
        assertFalse(Arrays.equals(chars, (str + str).toCharArray()));
    }
}