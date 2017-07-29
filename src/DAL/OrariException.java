/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Fatlum
 */
public class OrariException extends Exception {

    /**
     * Creates a new instance of <code>OrariException</code> without detail
     * message.
     */
    public OrariException() {
    }

    /**
     * Constructs an instance of <code>OrariException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public OrariException(String msg) {
        super(msg);
    }
}
