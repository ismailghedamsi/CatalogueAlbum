package com.example.tpfinal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest{
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    private String email;
    private String password;


    @Test
    public void authentificationTest(){

    }
}