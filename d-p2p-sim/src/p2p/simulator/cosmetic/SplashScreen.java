/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.simulator.cosmetic;

/**
 *
 * @author gp
 */
import java.net.URL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {

    private Image fullImage,  subImage;
    private int x,  y,  width,  height;
    private int xProgressBar, yProgressBar;
    private int dxProgressBar, dyProgressBar;
    private int progress, maxProgress;
    private String message;
    private Font f1, f2;

    public SplashScreen(String imageFileName) {
        
        super(new Frame());

        f1 = new Font("Serif", Font.BOLD, 14);
        f2 = new Font("Serif", Font.PLAIN, 12);
        
        maxProgress = 100;
        progress = 0;
        message = "";
        
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            File file = new File(imageFileName);
            URL imageUrl = file.toURI().toURL();
            fullImage = toolkit.getImage(imageUrl);
            //subImage = toolkit.getImage(getClass().getResource("img-part.jpg"));

            MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(fullImage, 0);
            //mediaTracker.addImage(subImage, 0);
            mediaTracker.waitForID(0);

            width = fullImage.getWidth(this);
            height = fullImage.getHeight(this);

            Dimension screenSize = toolkit.getScreenSize();

            x = (screenSize.width - width) / 2;
            y = (screenSize.height - height) / 2;
            
            xProgressBar = 0;
            yProgressBar = height - 50;
            
            dxProgressBar = 0;
            dyProgressBar = 5;
            
        } catch (Exception exception) {
            exception.printStackTrace();
            fullImage = null;
        }
        
        // Avoid creating a point with each mousePressed() call
        final Point origin = new Point();
        super.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        super.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });

    }

    public void open() {

        if (fullImage == null) {
            return;
        }
        setBounds(x, y, width, height);
        setVisible(true);
    }

    public void setProgress(String msg, int progress) {
        
        if (this.progress == progress)
            return;
        
        this.progress = progress;
        this.message = msg;
       
        repaint();
    }

    public void close() {
        setVisible(false);
    }

    @Override
    public void paint(Graphics g) {

        if (fullImage == null) {
            return;
        }
        if (maxProgress != progress)
            dxProgressBar = (width * progress) / maxProgress;
        else
            dxProgressBar = width;
        
        if (progress == 0) {
            g.drawImage(fullImage, 0, 0, width, height, this);
            g.setClip(xProgressBar, yProgressBar, width, dyProgressBar);
        }
        g.setColor(new Color(81, 67,71));
        g.fillRect(xProgressBar, yProgressBar, width, dyProgressBar);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(xProgressBar, yProgressBar, dxProgressBar, dyProgressBar);
        
        g.setColor(Color.WHITE);
        g.setFont(f1);
        g.drawString("Nested Balanced Distributed Tree *", xProgressBar + 5, 15);
        g.drawString("Simulation", xProgressBar + 5, 30);
        
        //g.setFont(f2);
        //g.drawString(message, xProgressBar + 5, yProgressBar + 20);
    }
}