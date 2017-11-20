package com.vinodh.hibernate.validation.demo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vinodh.hibernate.validation.demo.Utils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class})
public class UtilsTest {

    @Test
    public void testSay() throws Exception {
        PowerMockito.mockStatic(Utils.class);
        Mockito.when(Utils.say(Mockito.anyString())).thenReturn("hello:mandy");
        Assert.assertEquals("hello:mandy", Utils.say("sid"));
    }

}