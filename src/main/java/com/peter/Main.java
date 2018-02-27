package com.peter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public  class Main
{
     static final String URL = "jdbc:mysql://localhost:3306/media_manager";
    static final String USERNAME = "root";
    public static String d;
 //   public static ResultSet res;

    public static void main(String[] args)
    {
// ЗАПУСК САМОго  ПЕРВОГО ОКНА=========================================================================================
        FirstWindow f = new FirstWindow("MEDIATEKA");
        f.setVisible(true);                                     // сделать окно видимым
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //закроется если нажать  на крестик
        f.setSize(300, 300);                                    //задается размер фрейма
        f.setResizable(false);                                  //нельзя растягитвать оено
        f.setLocationRelativeTo(null);                          //делает локацию фрейма по центру




//        w.setSize(300, 300);
//        w.setResizable(false);
//        w.setLocationRelativeTo(null);
    }
}
//САМОЕ ПЕРВОЕ ОКНО====================================================================================================
     class  FirstWindow extends JFrame
     {
         JButton games, movies, books;
         Event k = new Event();
         public FirstWindow(String s)
           {
             super(s);
             setLayout(null);
             games = new JButton("GAMES");
             games.setBounds(45, 25, 200, 30);
             add(games);
             movies = new JButton("MOVIES");
             movies.setBounds(45, 105, 200, 30);
             add(movies);
             books = new JButton("BOOKS");
             books.setBounds(45, 185, 200, 30);
             add(books);
             games.addActionListener(k);
             movies.addActionListener(k);
             books.addActionListener(k);


           }
             class Event implements ActionListener
             {
                 public void actionPerformed(ActionEvent e)
                 {
                     if (e.getSource() == games)
                     {
                         Window_1 w = new Window_1("GAMES");
                         w.setVisible(true);
                         w.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                         w.setSize(600, 650);
                         w.setResizable(false);
                         w.setLocationRelativeTo(null);
                     }
                     else  if ( e.getSource() == movies)
                     {
                         Window_2 z = new Window_2("MOVIES");
                         z.setVisible(true);
                         z.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                         z.setSize(600, 650);
                         z.setResizable(false);
                         z.setLocationRelativeTo(null);
                     }
                     else  if ( e.getSource() == books )
                     {
                         Window_3 x = new Window_3("MOVIES");
                         x.setVisible(true);
                         x.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                         x.setSize(600, 650);
                         x.setResizable(false);
                         x.setLocationRelativeTo(null);
                     }
                 }
             }
     }



