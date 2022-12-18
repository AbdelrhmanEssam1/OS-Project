/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package os2finalhope;

/**
 *
 * @author mahamed abdelshafey
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class Buttongreen extends JButton {

    public Buttongreen() {
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, new Color(0, 179, 44), 0, getHeight(), new Color(13, 239, 66)));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.dispose();
        super.paint(grphcs);
    }
}
