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
public class TelStudentiException extends Exception {

    /**
     * Creates a new instance of <code>TelStudentiException</code> without
     * detail message.
     */
    public TelStudentiException() {
    }

    /**
     * Constructs an instance of <code>TelStudentiException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TelStudentiException(String msg) {
        super(msg);
    }
}
