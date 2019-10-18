package cgpa;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.*;
import sun.audio.*;
import java.text.DecimalFormat;


public class CGPA2 {
  

    public CGPA2() throws ClassNotFoundException, SQLException
        {   
            
            conn = Mysql.ConnectSQL();
            frame1 = new JFrame("CGPA calculator");
            frame1.setSize(1300,500);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pan = new JPanel();
            pan1 = new JPanel();
            pan2 = new JPanel();
            pan3 = new JPanel();
            pan4 = new JPanel();
            pan5 = new JPanel();
            pan6 = new JPanel();
            panbtn = new JPanel();
            pangba = new JPanel();
            calculate = new JButton("Calculate");
            button = new Button();
            reset = new JButton("Reset");
            nextsem = new JButton("Next Semester");
            mod1 = new JComboBox(subject);
            mod2 = new JComboBox(subject);
            mod3 = new JComboBox(subject);
            mod4 = new JComboBox(subject);
            mod5 = new JComboBox(subject);
            gpa = new JTextArea(10,30);
          
            sem = new JLabel(semesters[number]);
            
            n1 = new JLabel();
            n2 = new JLabel();
            n3 = new JLabel();
            n4 = new JLabel();
            n5 = new JLabel();
            
            c1 = new JLabel();
            c2 = new JLabel();
            c3 = new JLabel();
            c4 = new JLabel();
            c5 = new JLabel();
            
            tf1 = new JLabel();
            tf2 = new JLabel();
            tf3 = new JLabel();
            tf4 = new JLabel();
            tf5 = new JLabel();
            
            t1 = new JLabel();
            t2 = new JLabel("");
            t3 = new JLabel("");
            t4 = new JLabel("");
            t5 = new JLabel("");
            
            mark1 = new JTextField();
            mark2 = new JTextField();
            mark3 = new JTextField();
            mark4 = new JTextField();
            mark5 = new JTextField();
            
            m1 = new JLabel("Module ID ");
            m2 = new JLabel("Module Name");
            m3 = new JLabel("Credit");
            m4 = new JLabel("Marks");
            m5 = new JLabel("Grade");
            m6 = new JLabel("Grade Point");
            
  
            
            pan.setLayout(new GridLayout(0,6,10,10));
            pan.add(pan1);
            pan1.add(sem);
            pan.add(pan2);
            pan.add(pan3);
            pan.add(pan4);
            pan.add(pan5);
            pan.add(pan6);
          
            pan.add(m1);
            pan.add(m2);
            pan.add(m3);
            pan.add(m4);
            pan.add(m5);
            pan.add(m6);
            
            pan.add(mod1);
            combo();
            mod1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try{    //Retrieved from https://www.youtube.com/watch?v=HXFJsaC4fs4
                           // This method is to get the subject name and credit by selecting its termcode in database
                            String sql = "select * from subject where moduleid=?"; //This syntax is to find the data of subject names and credit through its registered termcode in table
                            PreparedStatement pst1 = conn.prepareStatement(sql); 
                            pst1.setString(1,(String)mod1.getSelectedItem());                 
                            ResultSet rs1 = pst1.executeQuery();
                        
                                      while(rs1.next()){
                                        n1.setText(rs1.getString("modulename")); //Display the module name after selecting its termcode
                                        c1.setText(rs1.getString("credit")); //Display the module name after selecting its termcode
                                    }
                                    
                            
                        } catch (SQLException ex) {
                       ex.printStackTrace();
                    }
                    }
                    }); 
            pan.add(n1);
            pan.add(c1);
            pan.add(mark1);
            mark1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) //This method is to input the marks into the textfield and display its grade and calculated gradepoint
                {
                     input = mark1.getText();
                     credit = Double.parseDouble(c1.getText());
                     font = tf1.getText();
                     setGrade(); 
                   
                     tf1.setText(grade); //Display the grade
                     t1.setText(String.valueOf(creditvalue)); //Display the calculated gradepoint
                     if(grade=="F" || grade=="D"){  //The font of the grade and gradepoint will become red if the grade is F or d
                            tf1.setForeground(Color.red);
                            t1.setForeground(Color.red);
                     }
                     else{
                         tf1.setForeground(Color.black);
                            t1.setForeground(Color.black);
                     }
                                        }
                                        });
            pan.add(tf1);
            pan.add(t1);   
            pan.add(mod2);
            mod2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                          
                            String sql = "select * from subject where moduleid=?";
                            PreparedStatement pst1 = conn.prepareStatement(sql);
                            pst1.setString(1,(String)mod2.getSelectedItem());                           
                            ResultSet rs1 = pst1.executeQuery();
                        
                                      while(rs1.next()){
                                        
                                        n2.setText(rs1.getString("modulename"));
                                        c2.setText(rs1.getString("credit"));
                                    }
                            if(n2.getText().equals(n1.getText())|| n2.getText().equals(n3.getText()) || n2.getText().equals(n4.getText()) ||n2.getText().equals(n5.getText())){
                                n2.setText("Invalid same subjects");
                            }
                            
                        } catch (SQLException ex) {      
                       ex.printStackTrace();
                    }
                    }
                    }); 
            pan.add(n2);
            pan.add(c2);
            pan.add(mark2);
            mark2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                     input = mark2.getText();
                     credit = Double.parseDouble(c2.getText());
                     setGrade();
                     tf2.setText(grade);
                     t2.setText(String.valueOf(creditvalue));
                      if(grade=="F" || grade=="D"){
                            tf2.setForeground(Color.red);
                            t2.setForeground(Color.red);
                     }
                     else{
                         tf2.setForeground(Color.black);
                            t2.setForeground(Color.black);
                     }
                }
                  });
            
            pan.add(tf2);
            pan.add(t2);
            
            pan.add(mod3);
            mod3.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                           
                            String sql = "select * from subject where moduleid=?";
                            PreparedStatement pst1 = conn.prepareStatement(sql);
                            pst1.setString(1,(String)mod3.getSelectedItem());                           
                            ResultSet rs1 = pst1.executeQuery();
                        
                                      while(rs1.next()){
                                        n3.setText(rs1.getString("modulename"));
                                        c3.setText(rs1.getString("credit"));
                                    }
                                    if(n3.getText().equals(n1.getText())|| n3.getText().equals(n2.getText()) || n3.getText().equals(n4.getText()) ||n3.getText().equals(n5.getText())){
                                n3.setText("Invalid same subjects");
                            }  
                            
                        } catch (SQLException ex) {
                       ex.printStackTrace();
                    }
                    }
                    }); 
            pan.add(n3);
            pan.add(c3);
            pan.add(mark3);
            mark3.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                     input = mark3.getText();
                     credit = Double.parseDouble(c3.getText());
                     setGrade();
                     tf3.setText(grade);
                     t3.setText(String.valueOf(creditvalue));
                     if(grade=="F" || grade=="D"){
                            tf3.setForeground(Color.red);
                            t3.setForeground(Color.red);
                     }
                     else{
                         tf3.setForeground(Color.black);
                            t3.setForeground(Color.black);
                     }
                                        }
                                        });
            pan.add(tf3);
            pan.add(t3);          
            pan.add(mod4);
            mod4.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                           
                            String sql = "select * from subject where moduleid=?";
                            PreparedStatement pst1 = conn.prepareStatement(sql);
                            pst1.setString(1,(String)mod4.getSelectedItem());                           
                            ResultSet rs1 = pst1.executeQuery();
                        
                                      while(rs1.next()){
                                        n4.setText(rs1.getString("modulename"));
                                        c4.setText(rs1.getString("credit"));
                                    }
                             if(n4.getText().equals(n1.getText())|| n4.getText().equals(n2.getText()) || n4.getText().equals(n3.getText()) ||n4.getText().equals(n5.getText())){
                                n4.setText("Invalid same subjects");
                            }          
                            
                        } catch (SQLException ex) {
                       ex.printStackTrace();
                    }
                    }
                    }); 
            pan.add(n4);
            pan.add(c4);
            pan.add(mark4);
            mark4.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {   
                     input = mark4.getText();
                     credit = Double.parseDouble(c4.getText());
                     setGrade();
                     tf4.setText(grade);
                     t4.setText(String.valueOf(creditvalue));
                      if(grade=="F" || grade=="D"){
                            tf4.setForeground(Color.red);
                            t4.setForeground(Color.red);
                     }
                     else{
                         tf4.setForeground(Color.black);
                            t4.setForeground(Color.black);
                     }
                                        }
                                        });
            pan.add(tf4);
            pan.add(t4);
            pan.add(mod5);
            mod5.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try{
                           
                            String sql = "select * from subject where moduleid=?";
                            PreparedStatement pst1 = conn.prepareStatement(sql);
                            pst1.setString(1,(String)mod5.getSelectedItem());                           
                            ResultSet rs1 = pst1.executeQuery();
                        
                                      while(rs1.next()){
                                        n5.setText(rs1.getString("modulename"));
                                        c5.setText(rs1.getString("credit"));
                                    }
                              if(n5.getText().equals(n1.getText())|| n5.getText().equals(n2.getText()) || n5.getText().equals(n4.getText()) ||n5.getText().equals(n4.getText())){
                                n5.setText("Invalid same subjects");
                            }         
                            
                        } catch (SQLException ex) {
                       ex.printStackTrace();
                    }
                    }
                    }); 
            pan.add(n5);
            pan.add(c5);
            pan.add(mark5);
            
            
            mark5.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                     input = mark5.getText();
                     credit = Double.parseDouble(c5.getText());
                     setGrade();
                     tf5.setText(grade);
                     t5.setText(String.valueOf(creditvalue));
                      if(grade=="F" || grade=="D"){
                            tf5.setForeground(Color.red);
                            t5.setForeground(Color.red);
                     }
                     else{
                         tf5.setForeground(Color.black);
                            t5.setForeground(Color.black);
                     }
                                        
                                        }
                                        });
           
  
            pan.add(tf5);
            pan.add(t5);
            mark1.addKeyListener(new KeyAdapter(){ //To make the textfield to only accept numbers.Retrieved from https://www.youtube.com/watch?v=9B5ZgItFxNA&t=176s
              public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                if(!(Character.isDigit(key))){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                e.consume();
        }
       }
                
            });
            mark2.addKeyListener(new KeyAdapter(){
              public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                 if(!(Character.isDigit(key))){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                e.consume();
        }
       }
                
            });
             mark3.addKeyListener(new KeyAdapter(){
              public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                if(!(Character.isDigit(key))){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                e.consume();
        }
       }
                
            });
            mark4.addKeyListener(new KeyAdapter(){
             public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                 if(!(Character.isDigit(key))){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                e.consume();
        }
          }
            });
              mark5.addKeyListener(new KeyAdapter(){
             public void keyTyped(KeyEvent e){
                char key = e.getKeyChar();
                if((key < '0' || key >'9') && key != '\b'){
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                e.consume();
        }
          }
            });
           
            pangba.add(gpa);
            panbtn.add(calculate);
            calculate.addActionListener(button);
            
            panbtn.add(reset); 
            reset.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                   reset();
                }
            });
            
        
            panbtn.add(nextsem);
            nextsem.addActionListener(new ActionListener(){
                PreparedStatement  pst;
              public void actionPerformed(ActionEvent ae) //This method is to store or update the value of total credit ,total grade point into the database according to its semester
              {     
                    label=sem.getText(); //get the value from label for if else statement to determine the place for the value to store according to its semester
                  try{
                      if(label.equals("Semester 1")){
                      query1="Update cgpa set gradepoint='"+total+"' ,credit='"+credittotal+"' ,gpa='"+gpa1+"'  where semester='1'";
                       pst=conn.prepareStatement(query1);
                      }
                      else if(label.equals("Semester 2")){
                      query1="Update cgpa set gradepoint='"+semgradepoint+"' ,credit='"+semcredit+"' ,gpa='"+gpa1+"'  where semester='2'";
                       pst=conn.prepareStatement(query1);
                      }
                      else if(label.equals("Semester 3")){
                      query1="Update cgpa set gradepoint='"+semgradepoint+"' ,credit='"+semcredit+"' ,gpa='"+gpa1+"'  where semester='3'";
                       pst=conn.prepareStatement(query1);
                      }
                      else if(label.equals("Semester 4")){
                      query1="Update cgpa set gradepoint='"+semgradepoint+"' ,credit='"+semcredit+"' ,gpa='"+gpa1+"'  where semester='4'";
                       pst=conn.prepareStatement(query1);
                      }
                      else if(label.equals("Semester 5")){
                      query1="Update cgpa set gradepoint='"+semgradepoint+"' ,credit='"+semcredit+"' ,gpa='"+gpa1+"'  where semester='5'";
                       pst=conn.prepareStatement(query1);
                      }
                      
                      pst.execute();
                      JOptionPane.showMessageDialog(null, "Proceed Next Semester"); 
                      pst.close();
                      do{  //This allow the semester in the JLabel to change everytime when you click on the next sem button
                      number=number+1;
                      sem.setText(semesters[number]);
                      reset();
                      }while(number==5);
                   
                      
                  }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "You have reached the final semester"); //After 5 semester this message will show when u click the next sem button again
                
                  }
                  
              }
            });
            
            frame1.add(pan, BorderLayout.PAGE_START);
            frame1.add(pangba, BorderLayout.CENTER);
            frame1.add(panbtn, BorderLayout.PAGE_END);
            tm = new Timer(100, new ActionListener(){ //This is a thread to set the timer for the method to perform retrieved from https://www.youtube.com/watch?v=wfYYyz7kbfM
                public void actionPerformed(ActionEvent e){ //This method is to change the frame's background color every second

                    pan.setBackground(new Color(i,150,70));
                    pangba.setBackground(new Color(i,150,70));
                    panbtn.setBackground(new Color(i,150,70));
                    pan1.setBackground(new Color(i,150,70));
                    pan2.setBackground(new Color(i,150,70));
                    pan3.setBackground(new Color(i,150,70));
                    pan4.setBackground(new Color(i,150,70));
                    pan5.setBackground(new Color(i,150,70));
                    pan6.setBackground(new Color(i,150,70));
                    i++;
                    if(i==255){ 
                        i=80;
                    }
              
                    
                }
               
            });
            tm.start(); 
            frame1.setVisible(true);
            
        }
    public void combo(){ //This method is to add the list of termcodes into the JCombobox
            try{
                            String query = "select * from subject";
                            PreparedStatement pst = conn.prepareStatement(query);
                            ResultSet rs = pst.executeQuery();
                                  
                                    while(rs.next()){
                                        mod1.addItem(rs.getString("moduleid"));
                                        mod2.addItem(rs.getString("moduleid"));
                                        mod3.addItem(rs.getString("moduleid"));
                                        mod4.addItem(rs.getString("moduleid"));
                                        mod5.addItem(rs.getString("moduleid"));
                                    }      
                        } catch (SQLException ex) {
                       ex.printStackTrace();
                    }
        }
   
    
    class Button implements ActionListener
    { 

        
        public void actionPerformed(ActionEvent e) {//This method allows to calculate the gpa from 1 to 5 value inputed.
            label=sem.getText();
           double gp1,gp2 ,gp3 ,gp4,gp5 ;
           double cre1,cre2 ,cre3,cre4 ,cre5 ;
           String g1 = t1.getText();
           String g2 = t2.getText();
           String g3 = t3.getText();
           String g4 = t4.getText();
           String g5 = t5.getText();
           String credit1 = c1.getText();
           String credit2 = c2.getText();
           String credit3 = c3.getText();
           String credit4 = c4.getText();
           String credit5 = c5.getText();
          
           
           int j=1; //A counter which counts how many inputs, default=1 subject atleast 

//Gather total no. of inputs
            if (g2!="") {
              j++;
            }
            if (g3!="") {
              j++;
            }
            if (g4!="") {
              j++;
            }
            if (g5!="") {
              j++;
            }

//Convert inputs to double, and compute results according to no.of inputs
                if (j==1) {
               gp1 = Double.parseDouble(g1);
               gp2 = 0;
               gp3 = 0;
               gp4 = 0;
               gp5 = 0;

               cre1 = Double.parseDouble(credit1);
               cre2 = 0;
               cre3 = 0;
               cre4 = 0;
               cre5 = 0;

               total = gp1; //calculation for total gradepoint
               credittotal = cre1; //calculation for total credit 
            }

            if (j==2) {
               gp1 = Double.parseDouble(g1);
               gp2 = Double.parseDouble(g2);
               gp3 = 0;
               gp4 = 0;
               gp5 = 0;

               cre1 = Double.parseDouble(credit1);
               cre2 = Double.parseDouble(credit2);
               cre3 = 0;
               cre4 = 0;
               cre5 = 0;

               total = gp1+gp2;
               credittotal = cre1+cre2;
            }

            if (j==3) {
               gp1 = Double.parseDouble(g1);
               gp2 = Double.parseDouble(g2);
               gp3 = Double.parseDouble(g3);
               gp4 = 0;
               gp5 = 0;

               cre1 = Double.parseDouble(credit1);
               cre2 = Double.parseDouble(credit2);
               cre3 = Double.parseDouble(credit3);
               cre4 = 0;
               cre5 = 0;

               total = gp1+gp2+gp3;
               credittotal = cre1+cre2+cre3;
            }

            if (j==4) {
               gp1 = Double.parseDouble(g1);
               gp2 = Double.parseDouble(g2);
               gp3 = Double.parseDouble(g3);
               gp4 = Double.parseDouble(g4);
               gp5 = 0;

               cre1 = Double.parseDouble(credit1);
               cre2 = Double.parseDouble(credit2);
               cre3 = Double.parseDouble(credit3);
               cre4 = Double.parseDouble(credit4);
               cre5 = 0;

               total = gp1+gp2+gp3+gp4;
               credittotal = cre1+cre2+cre3+cre4;
            }

            if (j==5) {
               gp1 = Double.parseDouble(g1);
               gp2 = Double.parseDouble(g2);
               gp3 = Double.parseDouble(g3);
               gp4 = Double.parseDouble(g4);
               gp5 = Double.parseDouble(g5);

               cre1 = Double.parseDouble(credit1);
               cre2 = Double.parseDouble(credit2);
               cre3 = Double.parseDouble(credit3);
               cre4 = Double.parseDouble(credit4);
               cre5 = Double.parseDouble(credit5);

               total = gp1+gp2+gp3+gp4+gp5;
               credittotal = cre1+cre2+cre3+cre4+cre5;
            }
          
           
           
         
            switch (label) { //This switch case is to get the value of total credit and total gradepoint from the database base on its registered id or can be called as semester
                case "Semester 2":
                    try {
                        pst=conn.prepareStatement("select gradepoint,credit from cgpa where semester = 1");
                        read();                     
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }   break;
                case "Semester 3":
                    try {
                        pst=conn.prepareStatement("select gradepoint,credit from cgpa where semester = 2");
                        read();
                      
                    } catch (SQLException ex) {
                       ex.printStackTrace();
                    }   break;
                case "Semester 4":
                    try {
                        pst=conn.prepareStatement("select gradepoint,credit from cgpa where semester = 3");
                        read();
              } catch (SQLException ex) {
                        ex.printStackTrace();
                    }   break;
                case "Semester 5":
                    try {
                        pst=conn.prepareStatement("select gradepoint,credit from cgpa where semester = 4");
                       read();                  
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }   break;
                default:
                    break;
            }
            
            
            gpa1 = total/credittotal;//This is the calculation for gpa
            gpa.setText("Total Grade Point: " +total);
            DecimalFormat f = new DecimalFormat("0.00");//This allows to make the value into 2 decimal places
             gpa.append("\nGPA: " +f.format(gpa1));
             if(sem.getText()!="Semester 1"){ //This makes the cgpa not to show at semester 1
             gpa.append("\nCGPA: "+f.format(cgpatotal));
             }
             if(gpa1<2 && sem.getText().equals("Semester 1")){ //It will the given message if the user fail their first semester
                 gpa.append("\nGoodluck next sem!!!");
            }
             //This given message will show in the textarea if the user's cgpa is lower than 2.0
             if(cgpatotal<2 && sem.getText().equals("Semester 2")||cgpatotal<2 && sem.getText().equals("Semester 3")||cgpatotal<2 && sem.getText().equals("Semester 4")||cgpatotal<2 && sem.getText().equals("Semester 5")){
                 gpa.append("\nAlert you may need to retake the failed subject!!!");
            }
        }
   }
    public void setGrade(){ //This method is to set the grade for the marks and calculate the gradepoint for the subject
                         marks = Double.parseDouble(input);
                        if(marks >100){
                            grade="Invalid marks";
                        }
                        else if(marks >= 80){
                            grade="A";
                            creditvalue= credit * 4.0;
                            
                                        }
                        else if(marks >= 70){
                           grade="B+";
                             creditvalue= credit * 3.5;
                                        }
                        else if(marks >= 65){
                              grade="B";
                            creditvalue= credit * 3.0;
                                        }
                        else if(marks >= 55){
                              grade="C+";
                             creditvalue= credit * 2.5;
                                        }
                        else if(marks >= 50){
                              grade="C";
                             creditvalue= credit * 2.0;
                                        }
                        else if(marks >= 40){
                              grade="D";
                             creditvalue= credit * 1.0;
                                        }
                        else if(marks >= 0){
                              grade="F";
                             creditvalue= credit * 0.0;
                        }                                     
   }
    public void reset(){//This method is to set everything back to default except for the semester label
                      mod1.setSelectedIndex(0);
                      mod2.setSelectedIndex(0);
                      mod3.setSelectedIndex(0);
                      mod4.setSelectedIndex(0);
                      mod5.setSelectedIndex(0);
                      n1.setText("");
                      n2.setText("");
                      n3.setText("");
                      n4.setText("");
                      n5.setText("");
                      c1.setText("");
                      c2.setText("");
                      c3.setText("");
                      c4.setText("");
                      c5.setText("");
                      mark1.setText("");
                      mark2.setText("");
                      mark3.setText("");
                      mark4.setText("");
                      mark5.setText("");
                      tf1.setText("");
                      tf2.setText("");
                      tf3.setText("");
                      tf4.setText("");
                      tf5.setText("");
                      t1.setText("");
                      t2.setText("");
                      t3.setText("");
                      t4.setText("");
                      t5.setText("");
                      gpa.setText("");
}
    public void read() throws SQLException{  //This method is to get the value of gradepoint and credit from the database and calculate its cgpa
                        ResultSet rs=pst.executeQuery();
                        String totalgradepoint= rs.getString("gradepoint");
                        String totalcredit= rs.getString("credit");
                        double gradepointsem1 = Double.parseDouble(totalgradepoint);
                        double creditpointsem1 = Double.parseDouble(totalcredit);
                        semcredit = creditpointsem1+credittotal;
                        semgradepoint= gradepointsem1+total;
                        cgpatotal= semgradepoint/semcredit; //The calculation for the cgpa
   }
    
        
     

     
    public void startBGMusic(){ //This method is to play the background music
    AudioPlayer myBackgroundPlayer = AudioPlayer.player;
    ContinuousAudioDataStream myLoop = null;
    try {
          AudioStream myBackgroundMusic = new AudioStream(new FileInputStream(new File("C:\\Users\\Windows 10\\Desktop\\10.wav")));//The absolute path for the music file
          AudioData myData = myBackgroundMusic.getData();
          myLoop = new ContinuousAudioDataStream(myData);
    }catch(Exception error){
        System.out.println("File Not Found");
        System.out.println(error);
    }
    myBackgroundPlayer.start(myLoop);//To loop the music after the music ended which is infinite   
}  
   
    Timer tm;
    double cgpatotal,semcredit,semgradepoint,credit,marks,total,credittotal,creditvalue,gpa1;
    int i=80,number;
    String query1,font,input,grade,label;
    PreparedStatement  pst;
    Connection conn = null;
    Button button;
    JFrame frame1;
    JPanel pan1,pan2,pan3,pan4,pan5,pan6,pangba,panbtn,pan;
    JButton calculate,reset,nextsem;
    JTextArea gpa;
    JLabel sem,m1,m2,m3,m4,m5,m6,tf1,tf2,tf3,tf4,tf5,n1,n2,n3,n4,n5,c1,c2,c3,c4,c5,t1,t2,t3,t4,t5;
    JTextField mark1,mark2,mark3,mark4,mark5,sem1gp,sem1credit;
    JComboBox mod1,mod2,mod3,mod4,mod5;
    String[] subject = {"Module ID"};
    String[] semesters = {"Semester 1", "Semester 2", "Semester 3", "Semester 4","Semester 5"};
       
}
