/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Arbër Suka
 */
public class NumriTelValidator {
    private Pattern pattern;
    private Matcher matcher;
    
    private static final String EMAIL_PATTERN = 
        "^((\\+|00)(38649|37744|37745|38643 )[0-9]{6})";
    
    public NumriTelValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
