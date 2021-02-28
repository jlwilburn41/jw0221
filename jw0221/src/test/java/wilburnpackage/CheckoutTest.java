package wilburnpackage;

import org.junit.jupiter.api.Test;


/**
 * Unit test for Checkout.
 */
class CheckoutTest {
    @Test
    void test1() throws Exception{
        //Test Case 1
        Checkout test1 = new Checkout("JAKR", 5, 101, "09/03/15");
        System.out.println("");
        System.out.println("***************");
        System.out.println("");
    }

    @Test
    void test2() throws Exception{
        //Test Case 2
        Checkout test2 = new Checkout("LADW", 3, 10, "07/02/20");
        System.out.println("");
        System.out.println("***************");
        System.out.println("");
    }

    @Test
    void test3() throws Exception{
        //Test Case 3
        Checkout test3 = new Checkout("CHNS", 5, 25, "07/02/15");
        System.out.println("");
        System.out.println("***************");
        System.out.println("");
    }

    @Test
    void test4() throws Exception{
        //Test Case 4
        Checkout test4 = new Checkout("JAKD", 6, 0, "09/03/15");
        System.out.println("");
        System.out.println("***************");
        System.out.println("");
    }

    @Test
    void test5() throws Exception{
        //Test Case 5
        Checkout test5 = new Checkout("JAKR", 9, 0, "07/02/15");
        System.out.println("");
        System.out.println("***************");
        System.out.println("");
    }

    @Test
    void test6() throws Exception{
        //Test Case 6
        Checkout test6 = new Checkout("JAKR", 4, 50, "07/02/20");
    }
}
