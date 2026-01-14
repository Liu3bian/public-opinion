package com.fdbatt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        // INSERT INTO sys_user VALUES (1,'admin','$2a$10$EW4OYazRrsssmwv3BI0La.smVA469icJM8RXO/ptSRxBsB8xUI5lG',1,NOW());
        System.out.println(encoder.matches("123456",
                "$2a$10$EW4OYazRrsssmwv3BI0La.smVA469icJM8RXO/ptSRxBsB8xUI5lG"));
    }
}
