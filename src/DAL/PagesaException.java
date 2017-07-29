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
public class PagesaException extends Exception {

    /**
     * Creates a new instance of <code>PagesaException</code> without detail
     * message.
     */
    public PagesaException() {
    }

    /**
     * Constructs an instance of <code>PagesaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PagesaException(String msg) {
        super(msg);
    }
}
