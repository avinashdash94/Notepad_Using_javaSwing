
package notepad;
import java.awt.*;
import javax.swing.*;
public class about {
    static JFrame window=new JFrame("About Notepad");
    Notepad samp;
    JButton btn;
    public about(Notepad ref)
    {
        samp=ref;
//        Container c=window.getContentPane();
        JPanel p1=new JPanel();
        p1.setLayout(new FlowLayout());
        
        String about="<html>"+
                "<body>"+
                "Created By...<br>"+
                "Avinash Dash <br>"+
                "At Home India <br>"+
                "COmputer Science Student <br>"+
                "Contact: 989898990<br>"+
                "E-Mail:20avinashdash@gmail.com<br>"+
                "Version: 1.8jdk <br>"+
                "Built Date:"+new java.util.Date()+"<br><br>,br>"+
                "<body>"+
                "</html>";
        JLabel l=  new JLabel();
        l.setText(about);
        p1.add(l);
        int w=340;
        int h=350;
        window.setSize(w,h);
        Point center=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x-w/2,center.y/2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(false);
        window.add(p1);              
          
    }
    
}
