/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSITags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

/**
 * Class that marks required text fields with setters for variables 
 * @author TJurc
 */
public class IfEmptyMarkTag extends TagSupport{
    
    private String field;
    private String color = "red";
    
    public void setField(String field){
        this.field = field;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    /**
     * Writes out the required field text in chosen or default color
     * 
     * @return body skip
     * @throws JspException 
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (field == null || field.length() == 0) {
                out.print("<font color=" + color + "> *</font>");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return SKIP_BODY;
    }
}
