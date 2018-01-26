package com.peter;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.peter.Main.URL;
import static com.peter.Main.USERNAME;

/**
 * Created by Peter on 17.11.17.
 */
class  Window_3  extends JFrame
{
    JButton all, sorted, played, notplayed, editstatus, delete, add;
    JLabel namegames, show;
    JTextField game;
    JTextArea shown;
    String i;
    Events v = new Events();

    public Window_3(String s)
    {
        super(s);

        setLayout(null);
        all = new JButton("show all games");
        all.setBounds(120, 25, 200, 30);
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
        namegames.setBounds(30, 70, 100, 15);

        add = new JButton("Add the game");
        add.setBounds(120,305, 200, 30);

        show = new JLabel("results:");
        show.setBounds(60, 305, 200, 30);

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
        //         getContentPane().add(panel);
    }
    class Events implements ActionListener
    {

        public void actionPerformed(ActionEvent e)

        {
//кнопка -"получить все игры" ==========================================================================================
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

//КНОПКА - получить "отсортированые игры"===============================================================================
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
                    while (res.next())
                    {
                        list.add(res.getString(1));
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
//КНОПКА - получить "сигранные игры"====================================================================================
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
//КНОПКА - получить "не  игранные игры"=================================================================================
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
//КНОПКА - изменить статус игры=========================================================================================
            if (e.getSource() == editstatus)
            {
                Connection connection;
                try {

                    Driver driver;
                    driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);


                    connection = DriverManager.getConnection(URL, USERNAME, null);
                    Statement statement = connection.createStatement();
                    String s = game.getText();
                    ArrayList<String> list = new ArrayList<String>();
                    ResultSet res = statement.executeQuery("select  *  from games ");
                    String d = "";
                    while (res.next())

                    {
                        list.add(res.getString("game_name"));
//                             d = d + res.getString("game_name") + (System.getProperty("line.separator"));
//                             shown.setText(d);
                    }
                    for (int i = 0; i < list.size(); i++)
                    {
                        if (s.equals(list.get(i)))
                        {
                            JOptionPane.showConfirmDialog( null, null);
                            JOptionPane.showMessageDialog(null,"Doyou want to change the status");

                        }
                        else
                        {
                            System.out.println();
                            JOptionPane.showMessageDialog(null,"Game is absend");
                        }
                    }

                    statement.close();
                    connection.close();

                } catch (SQLException e1)
                {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }
    }
}
