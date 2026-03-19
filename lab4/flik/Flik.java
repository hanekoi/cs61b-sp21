package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(int a, int b) {
        // int stores the value directly; Integer is the box of int
        return a == b; // 128 trap, == compares the address of the objects
    }
}
