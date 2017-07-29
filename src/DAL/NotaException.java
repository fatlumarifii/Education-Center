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
public class NotaException extends Exception {

    /**
     * Creates a new instance of <code>NotaException</code> without detail
     * message.
     */
    public NotaException() {
    }

    /**
     * Constructs an instance of <code>NotaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NotaException(String msg) {
        super(msg);
    }
}
