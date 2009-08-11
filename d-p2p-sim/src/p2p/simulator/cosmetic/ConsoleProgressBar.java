/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2p.simulator.cosmetic;

import java.io.IOException;
import jline.ConsoleReader;
import jline.Terminal;
/**
 *
 * @author gp
 */
public class ConsoleProgressBar {
    
    private Terminal terminal;
    private ConsoleReader reader;

    public ConsoleProgressBar() {
        try {
            terminal = Terminal.setupTerminal();
            reader = new ConsoleReader();
            terminal.beforeReadLine(reader, "", (char)0);
        } catch (Exception e) {
            e.printStackTrace();
            terminal = null;
        }
    }
    
    public void setProgress (int completed, int total) {
        
        if (terminal == null)
                return;
        
        int w = reader.getTermwidth();
        int progress = (completed * 20) / total;
        String totalStr = String.valueOf(total);
        String percent = String.format("%0"+totalStr.length()+"d/%s [", completed, totalStr);
        String result = percent + repetition("=", progress) + repetition(" ", 20 - progress) + "]";
        
        try {
                reader.getCursorBuffer().clearBuffer();
                reader.getCursorBuffer().write(result);
                reader.setCursorPosition(w);
                reader.redrawLine();
        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    private String repetition(String s, int progress) {
        
        String str = "";
        
        for (int i = 0; i < progress; i++)
            str += s;
        
        return str;
    }
}
