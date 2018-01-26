package com.peter;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.mysql.jdbc.*;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.peter.Main.URL;
import static com.peter.Main.USERNAME;
import static javax.swing.UIManager.getString;
import static sun.java2d.cmm.ColorTransform.In;

/**
 * Created by Peter on 17.11.17.
 */
//     фрейм ИГРЫ ===========================================================================================================
class  Window_1  extends JFrame
{
    JButton all, sorted, played, notplayed, editstatus, delete, add ;
    JLabel namegames, show;
    JTextField game;
    JTextArea shown;
    protected static String z;
    Events v = new Events();

    public Window_1(String s)
    {
        super(s);
        setLayout(null);
        all = new JButton("show all games");
        all. setBounds(120, 25, 200, 30);
        all.setToolTipText("Если здесь нажать здесь, то можно увидеть все игры");

        sorted = new JButton("sorted games");
        sorted.setBounds(120, 105, 200, 30);

        played = new JButton("played this game");
        played.setBounds(120, 145, 200, 30);

        notplayed = new JButton("not played this game");
        notplayed.setBounds(120, 185, 200, 30);

        editstatus = new JButton("edit status of game");
        editstatus.setBounds(120, 225, 200, 30);

        delete = new JButton("delete  this game");
        delete.setBounds(120, 265, 200, 30);

        namegames = new JLabel("Enter games");
        namegames.setBounds(30,70, 100, 15);

        add = new JButton("Add the game");
        add.setBounds(120,305, 200, 30);

        show = new JLabel("results:");
        show.setBounds(60, 345, 200, 30);

        game = new JTextField(15);
        game.setBounds(120, 65, 200, 30);

        shown = new JTextArea();
        shown.setBounds(120, 355, 400, 230);

        shown.setLineWrap(true);
        shown.setWrapStyleWord(true);
        add(namegames);
        add(game);
        add(all);
        add(sorted);
        add(played);
        add(notplayed);
        add(editstatus);
        add(delete);
        add(add);
        add(show);
        add(shown);
        all.addActionListener(v);
        sorted.addActionListener(v);
        played.addActionListener(v);
        notplayed.addActionListener(v);
        editstatus.addActionListener(v);
        add.addActionListener(v);
        delete.addActionListener(v);

    }


    class Events implements ActionListener
    {
         String zzz;
//  метод. который понадобиться ниже, для сортировки ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++===
        public  boolean isGreaterThan(String a, String b)
        {
            return a.compareTo(b) > 0; //возращает тру если первая строка больше второй
        }

        public void actionPerformed(ActionEvent e)

        {
//кнопка (1)-"получить все игры" =======================================================================================
            if (e.getSource() == all)
            {
                Connection connection;
                try {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery("select  *  from games ");
                    String d = "";
                    while (res.next()) {
                        d = d + res.getString("game_name") + (System.getProperty("line.separator"));
                        shown.setText(d);
                    }
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }

//КНОПКА (2)- получить "отсортированые игры"============================================================================
            if (e.getSource() == sorted)
            {
                Connection connection;
                try {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();

                    ResultSet res = statement.executeQuery("select  *  from games ");

                    ArrayList<String> list = new ArrayList<String>();
                    ArrayList<String> list_2 = new ArrayList<String>();

                    while (res.next()) {
                        list.add(res.getString("game_name"));
                    }

//                    for (int j = 0; j < list.size() ; j++)
//
//                    {
//                        System.out.println("Список не сортированный  " + list.get(j));
//                    }

                    Iterator it = list.iterator();
                    while (it.hasNext())
                    {
                        int index = 0;
                        String min = list.get(0);
                        for (int i = 0; i < list.size() - 1; i++)
                        {
                            if (isGreaterThan(min, list.get(i + 1)))
                            {
                                min = list.get(i + 1);
                                index = i + 1;
                            }
                        }
                        list_2.add(list.get(index));
                        list.remove(index);
                    } Iterator i = list_2.iterator();
                    String d = "";
                    while (i.hasNext())
                    {
                        d = d + i.next() + (System.getProperty("line.separator"));
                        shown.setText(d);

                    }

                        statement.close();

                    connection.close();
                } catch (SQLException e1) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
//КНОПКА (3)- получить "сигранные игры"=================================================================================
            if (e.getSource() == played)
            {
                Connection connection;
                try {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery("select  game_name  from games  where game_status = 1");
                    String d = "";
                    while (res.next())
                    {
                        d = d + res.getString("game_name") + (System.getProperty("line.separator"));
                        shown.setText(d);
                    }
                    statement.close();
                    connection.close();

                } catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
//КНОПКА (4)- получить "не  игранные игры"==============================================================================
            if (e.getSource() == notplayed)
            {
                Connection connection;
                try {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery("select  game_name  from games  where game_status = 0");
                    String d = "";
                    while (res.next())
                    {
                        d = d + res.getString("game_name") + (System.getProperty("line.separator"));
                        shown.setText(d);
                    }
                    statement.close();
                    connection.close();

                } catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
//КНОПКА (5)- изменить статус игры =====================================================================================
            if (e.getSource() == editstatus)
            {
                Connection connection;
                try {

                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    PreparedStatement stat = connection.prepareStatement("select   game_status  from games  where game_name = ?");
                    PreparedStatement st = connection.prepareStatement("update games set game_status = ? where game_name = ?;");
                    String s = game.getText();
                    String zxc = null;
                    ArrayList<String> list = new ArrayList<String>();
                    ResultSet res = statement.executeQuery("select  *  from games ");
                    while (res.next())
                    {
                        list.add(res.getString("game_name"));
                    }

                    for (int i = 0; i < list.size(); i++)
                    {
                        if (s.equals(list.get(i)))
                        {

                            zxc = list.get(i);

                        }
                    }

                    if ( zxc != null)
                    {
                       int but = 1;
                        int result = JOptionPane.showConfirmDialog(Window_1.this,
                                "You, really want to change status?",
                                " Confirm",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (result == JOptionPane.YES_OPTION)
                       {
                            stat.setString(1,zxc);
                          ResultSet rs = stat.executeQuery();
//============================================================================================================================================
                          while(rs.next())
                          {
                               but = Integer.parseInt(rs.getString(1)) ;
                          }
//======================================================================================================================
                          if (but == 1)
                          {
                              st.setString(2,zxc);
                              st.setInt(1,0);
                              st.executeUpdate();
                              JOptionPane.showMessageDialog(null,"Just changed");
                          }
//======================================================================================================================
                        else  if (but == 0)
                          {
                              st.setString(2,zxc);
                              st.setInt(1,1);
                              st.executeUpdate();
                              JOptionPane.showMessageDialog(null,"Just changed");
                          }
                       }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Game is absend");
                    }

                    statement.close();
                    connection.close();

                } catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
// Кнопка (6) - удалить игру ===========================================================================================
            if (e.getSource() == delete)
            {
                Connection connection;
                try {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    PreparedStatement stat = connection.prepareStatement("delete  from games  where  game_name = ?");
                    String s = game.getText();
                    String z = null;
                    ArrayList<String> list2 = new ArrayList<String>();
                    ResultSet res = statement.executeQuery("select  *  from games ");

                    while (res.next())
                    {
                        list2.add(res.getString("game_name"));

                    }
                        for (int i = 0; i < list2.size(); i++)
                        {
                            if (s.equals(list2.get(i)))
                               {

                                z = list2.get(i);

                               }
                        }

                        if ( z != null)
                        {
                            stat.setString(1,z);
                            stat.execute();
                            JOptionPane.showMessageDialog(null,"Just deleted");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Game is absend");
                        }
                    statement.close();
                    connection.close();

                } catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
// Кнопка (7) - добавить игру ==========================================================================================
            if (e.getSource() == add)
            {

                try
                {
                   zzz = game.getText();
                    if (!zzz.isEmpty())
                     {
                         Edding ggg = new Edding("Choose status");
                        ggg.setVisible(true);
                        ggg.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);       //закроется если нажать  на крестик
                        ggg.setSize(300, 300);                                    //задается размер фрейма
                        ggg.setResizable(true);                                  //нельзя растягитвать оено
                        ggg.setLocationRelativeTo(null);
                     }
                    if (zzz.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"Enter the game into text field");
                    }
                }
                catch (Exception e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }

        class Edding extends JFrame
    {
        JButton one, two;
        Event v = new Event();
        public Edding (String s)

        {
            super(s);
            setLayout(null);
            one = new JButton("played");
            one.setBounds(45, 25, 200, 30);
            two = new JButton("not played");
            two.setBounds(45, 105, 200, 30);
            ButtonGroup bb = new ButtonGroup();
            bb.add(one);
            bb.add(two);
            add(one);
            add(two);

            one.addActionListener(v);
            two.addActionListener(v);
        }
        class Event implements ActionListener {
            Connection connection;
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    PreparedStatement stat = connection.prepareStatement("insert into games  (game_name, game_status ) values( ?, ?)");

                    if (e.getSource() == one)                                    // Статус сигранные
                    {
                        stat.setString(1, zzz);
                        stat.setInt(2, 1);
                        stat.execute();
                        JOptionPane.showMessageDialog(null, "Added");
                        setVisible(false);
                    }
                    else  if (e.getSource() == two)                              // Статус  не сигранные
                    {
                        stat.setString(1, zzz);
                        stat.setInt(2, 0);
                        stat.execute();
                        JOptionPane.showMessageDialog(null, "Added");
                        setVisible(false);
                    }
                    statement.close();
                    connection.close();

                }
                 catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }
    }
}}

