package notepad;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.print.*;
import javax.print.attribute.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.Calendar;
import java.text.SimpleDateFormat;
        
public class Notepad extends JFrame implements ActionListener,Printable {
    JMenuBar mbr;
    JMenu file,edit,format ,view,help;
    JMenuItem New ,open,save,saveas, pagesetup,print,exit;
    JMenuItem undo,cut,copy,paste,delete,find,findnext,replace,g,selectall,time;
    JMenuItem wordwrap,font;
    JMenuItem statusbar,aboutnotepad,viewhelp;
    
    JTextArea ta1;
    String content ,path="";
    static about abt;
    static font_chooser fc;
    static find finder;
    Notepad()
    {
        setTitle("Notepad");
        setIconImage(Toolkit.getDefaultToolkit().getImage("a.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        
        ta1=new JTextArea(10,10);
        ta1.setBackground(new Color(200,200,200));
        add(ta1);
        
        mbr=new JMenuBar();
        setJMenuBar(mbr);
        
        file=new JMenu("File");
        edit=new JMenu("Edit");
        format=new JMenu("Formate");
        view=new JMenu("View");
        help=new JMenu("Help");
        
        New =new JMenuItem("New");
        open=new JMenuItem("Open");
        save=new JMenuItem("Save");
        saveas=new JMenuItem("Save As");
        pagesetup=new JMenuItem("Page Setup");
        print=new JMenuItem("Print");
        exit=new JMenuItem("Exit");
        
        undo=new JMenuItem("Undo");
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        delete =new JMenuItem("Delete");
        find=new JMenuItem("Find");
        findnext=new JMenuItem("Find Next");
        replace=new JMenuItem("Replace");
        g=new JMenuItem("goto");
        selectall=new JMenuItem("Select All");
        time=new JMenuItem("Time/Date");
        
        wordwrap=new JMenuItem("Word Wrap");
        font=new JMenuItem("Font");
        statusbar=new JMenuItem("Status Bar");
        
        viewhelp=new JMenuItem("View Bar");
        aboutnotepad=new JMenuItem("About Notepad");
        
        file.add(New);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.addSeparator();
        file.add(pagesetup);
        file.add(print);
        file.addSeparator();
        file.add(exit);
        
        edit.add(undo);
        edit.addSeparator();
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.addSeparator();
        edit.add(find);
        edit.add(findnext);
        edit.add(replace);
        edit.add(g);
        edit.addSeparator();
        edit.add(selectall);
        edit.add(time);
        
        
        format.add(wordwrap);
        format.add(font);
        view.add(statusbar);
        help.add(viewhelp);
        help.add(aboutnotepad);
        
        mbr.add(file);
        mbr.add(edit);
        mbr.add(format);
        mbr.add(view);
        mbr.add(help);
        
        New.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveas.addActionListener(this);
        
        pagesetup.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        
        undo.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        find.addActionListener(this);
        findnext.addActionListener(this);
        replace.addActionListener(this);
        
        selectall.addActionListener(this);
        wordwrap.addActionListener(this);
        viewhelp.addActionListener(this);
        font.addActionListener(this);
        
        abt=new about(this);
        fc=new font_chooser(this);
        finder=new find(this);
        finder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==New)
            file_new();
        if(e.getSource()==open)
            file_open();
        if(e.getSource()==save)
            file_save();
        if(e.getSource()==saveas)
            file_save_as();
        if(e.getSource()==pagesetup)
            page_setup();
        if(e.getSource()==print)
            file_print();
        if(e.getSource()==exit)
            file_close();
        if(e.getSource()==cut)
            edit_cut();
        
        if(e.getSource()==copy)
            edit_copy();
        if(e.getSource()==paste)
            edit_paste();
        if(e.getSource()==delete)
            edit_delete();
        if(e.getSource()==time)
            edit_timedate();
        if(e.getSource()==selectall)
            edit_selectall();
        
        if(e.getSource()==wordwrap)
            format_wordwarp();
        if(e.getSource()==viewhelp)
            help_about();
        
        if(e.getSource()==font)
            format_font();
        if(e.getSource()==find)
            edit_find();
        if(e.getSource()==findnext)
            edit_find_next();
//        if(e.getSource()==)       
 
    }
    
    public void file_new(){
        if(ta1.getText().equals("")|| ta1.getText().equals(content))
        {
            ta1.setText("");
            content="";
            path="";
            setTitle("untitled-Notepad");
            
        }
        else
        {
            int a=JOptionPane.showConfirmDialog(null, "The text has been changed\n Do You want to save the changes?");
            if(a==0)
                file_save();
            else if(a==1)
            {
                ta1.setText("");
                path="";
                setTitle("untitile_Notepad");
            }
            else if(a==2)
                return;
        }
    }
    public void file_save()
    {
        System.out.println("Hellooooo");
        if(path.equals(""))
        {
            file_save_as();
            return;
        }
        try
        {
            FileWriter fw=new FileWriter(path);
            fw.write(ta1.getText());
            content=ta1.getText();
            fw.close();
            
        }catch(Exception i)
        {
            JOptionPane.showMessageDialog(this,"Faild to save the file","Error",JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
       
      
       public void file_save_as(){
           JFileChooser fc=new JFileChooser();
           fc.setFileSelectionMode(JFileChooser.FILES_ONLY);// to choose only file
           int r=fc.showSaveDialog(this);
           if(r==JFileChooser.CANCEL_OPTION)///If it will cancel then return noting
               return;
           File myfile=fc.getSelectedFile();
           System.out.println(myfile);
           if(myfile==null || myfile.getName().equals(""))
           {
               JOptionPane.showConfirmDialog(this,"A file with same name already exists!\n Are you sure want to overwrite?");
               if(r!=0)
                   return;
           }
           try
           {
               FileWriter fw=new FileWriter(myfile);
               fw.write(ta1.getText());
               content=ta1.getText();
               setTitle(myfile.getName()+"-Notepad");
               fw.close();
           }
           catch(Exception e)
           {
               JOptionPane.showMessageDialog(this, "Failed to save the file ","Error",JOptionPane.ERROR_MESSAGE);
           }
           
           
        
    }
       public void file_open(){
           JFileChooser fc=new JFileChooser(); //!st choose the file 
           fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//TO choose the file whichis already exist and is of type file
           int r=fc.showOpenDialog(this);//it will open the dialog box for choose file
           if(r==fc.CANCEL_OPTION)// IF CLICK CANCEL OPOTION then return nothing
               return;
           File myfile=fc.getSelectedFile();
           if(myfile==null || myfile.getName().equals(""))
           {
               JOptionPane.showMessageDialog(this,"Select a file!","ERROR",JOptionPane.ERROR_MESSAGE);
               return;
               
           }
           try
           {
               BufferedReader input =new BufferedReader(new FileReader(myfile));
               StringBuffer str=new StringBuffer();
               String line;
               while((line=input.readLine())!=null)//st is declared ass String avobe
                   str.append(line+"\n");
               ta1.setText(str.toString());
               content=ta1.getText();
               path=myfile.toString();
               setTitle(myfile.getName()+"-Notepad");
               
           }catch(FileNotFoundException e)
           {
               JOptionPane.showMessageDialog(null,"File not found: "+e);
           }
           catch(IOException e)
           {
               JOptionPane.showMessageDialog(null,"IO ERROR: "+e);
           }
    }
    public void page_setup()
    {
        PrinterJob job=PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
        PageFormat pf=job.pageDialog(aset);
        job.setPrintable(obj,pf);/// obj is Object of class Notepad declare below in program
        boolean ok=job.printDialog(aset);
        if(ok)
        {
            try
            {
                job.print(aset);
            }
            catch(PrinterException ex){
                /* THe Job did not succesfully complete*/
            }
        }
        
    }
    
    public int print(Graphics g,PageFormat pf,int page) throws PrinterException
    {
        if(page>0)
        {
            //We have only one page, and 'page' is zero-based
            return NO_SUCH_PAGE;
        }
        /*
        User (0,0) is typically outside the imageable area ,so we must 
        * translate by  the X and Y values in the Pageformat to avoid clipping
        */
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(),pf.getImageableY());
        /*
        now we perform our rendering
        */
        g.drawString(ta1.getText(),100,100);
        /*tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
      
    public void file_print()
    {
        PrinterJob printer=PrinterJob.getPrinterJob();
        HashPrintRequestAttributeSet printAttr=new HashPrintRequestAttributeSet();
        printer.setPrintable(obj);// obj is Object of class Notepad declare below in program
        if(printer.printDialog(printAttr))//Display print dialog
        {
            try
            {
                printer.print(printAttr);
            }
            catch(PrinterException e)
            {
                JOptionPane.showMessageDialog(this,"Failed to print the file:"+e ,"Error",JOptionPane.ERROR_MESSAGE);
                
            }
        }
        
    }
    public  void file_close()
    {
        if(ta1.getText().equals(content))
        {
            ta1.setText("");
            path="";
            System.exit(0);
        }
        else if(ta1.getText().equals("")&& content==null)
        {
            ta1.setText("");
            path="";
            System.exit(0);
        }
        else
        {
            int a=JOptionPane.showConfirmDialog(null,"The text has been change\n Do you want to save the changes?");
            if(a==0)
                file_save();
            else if(a==1)
            {
                ta1.setText("");
                path="";
                setTitle("untitled- Notepad");
                
            }
            else if(a==2)
                return;
        }
    }
     public void edit_cut()
     {
         ta1.cut();
         
     }
     public void edit_copy()
     {
         ta1.copy();
     }
     
     public void edit_paste()
     {
         ta1.paste();
     }
     public void edit_delete()
     {
         String temp=ta1.getText();
         ta1.setText(temp.substring(0,ta1.getSelectionStart())+temp.substring(ta1.getSelectionEnd()));
         
     }
     public void edit_selectall()
     {
         ta1.selectAll();
     }
     public void edit_timedate()
     {
         try
         {
             int start=ta1.getSelectionStart();
             int end=ta1.getSelectionEnd();
             Calendar cal=Calendar.getInstance();/// contain in  util pacge 
             SimpleDateFormat sdf=new SimpleDateFormat("dd//mm//yyyy h:m a");//To set the formate of date we can change to another valid format
             String now=sdf.format(cal.getTime()); //to get currnt time
             String temp1=ta1.getText().substring(0,start);
             String temp2=ta1.getText().substring(end);
             ta1.setText(temp1+""+now+""+temp2);
             ta1.select(start+1, start+1+now.length());                         
         }
         catch(NullPointerException e){}
     }
     
     public void format_wordwarp()
     {
      if(ta1.getLineWrap()==false)
          ta1.setLineWrap(true);
      else
          ta1.setLineWrap(false);      
      
     }
     
     public void help_about()
     {
         abt.window.setVisible(true); //window is object of JFram declared in about class
     }
     public void format_font()
     {
         fc.window.setVisible(true);//window is object of JFram declared in about class
     }
     public void edit_font()
     {
         fc.window.setVisible(true);//window is object of JFram declared in about class
     }
     public void edit_find()
     {
         finder.setVisible(true);
     }
     public void edit_find_next()
     {
         finder.find_next();
         
     }
     public void edit_replace()
     {
         finder.setVisible(true);
     }
     static Notepad obj; /// Creatin object of notepad class used above in program
     
     public static void main(String[] args)
     {
         obj=new Notepad();
         obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         obj.setVisible(true);
     }
}
