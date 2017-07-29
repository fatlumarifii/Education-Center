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
public class ArkivaException extends Exception {

    /**
     * Creates a new instance of <code>ArkivaException</code> without detail
     * message.
     */
    public ArkivaException() {
    }

    /**
     * Constructs an instance of <code>ArkivaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ArkivaException(String msg) {
        super(msg);
    }
}
