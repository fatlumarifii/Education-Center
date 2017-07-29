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
public class SallaException extends Exception {

    /**
     * Creates a new instance of <code>SallaException</code> without detail
     * message.
     */
    public SallaException() {
    }

    /**
     * Constructs an instance of <code>SallaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SallaException(String msg) {
        super(msg);
    }
}
