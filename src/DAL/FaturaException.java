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
public class FaturaException extends Exception {

    /**
     * Creates a new instance of <code>FaturaException</code> without detail
     * message.
     */
    public FaturaException() {
    }

    /**
     * Constructs an instance of <code>FaturaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FaturaException(String msg) {
        super(msg);
    }
}
