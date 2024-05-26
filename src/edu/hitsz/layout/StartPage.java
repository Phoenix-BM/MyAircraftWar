package edu.hitsz.layout;

import edu.hitsz.application.*;
import edu.hitsz.application.Game.EasyGame;
import edu.hitsz.application.Game.Game;
import edu.hitsz.application.Game.HardGame;
import edu.hitsz.application.Game.NormalGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JComboBox MusicSwitch;
    private JLabel label;
    private boolean musicState = false;

    public StartPage(){

        MusicSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) MusicSwitch.getSelectedItem();
                switch (selectedOption) {
                    case "开":
                        musicState = true;
//                        game.setOpenMusic(true);
//                        game.startMusic();
                        System.out.println("Music on");
                        break;
                    case "关":
                        musicState = false;
//                        game.setOpenMusic(false);
//                        game.endMusic();
                        System.out.println("Music off");
                        break;
                }
            }
        });


        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Easy Mode");
                Game easyGame = new EasyGame(musicState);
                playMusic(easyGame);
                addGamePanel(easyGame, "My Aircraftwar - Easy Mode");

            }
        });
        MediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Normal Mode");
                Game normalGame = new NormalGame(musicState);
                playMusic(normalGame);
                addGamePanel(normalGame, "My Aircraftwar - Normal Mode");

            }
        });
        HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hard Mode");
                Game hardGame = new HardGame(musicState);
                playMusic(hardGame);
                addGamePanel(hardGame, "My Aircraftwar - Hard Mode");

            }
        });

    }
    private void addGamePanel(Game game, String title) {
        Main.cardPanel.add(game);
        Main.cardLayout.last(Main.cardPanel);
        game.action();

    }
    public JPanel getMainPanel() {
        return this.MainPanel;
    }
    public void playMusic(Game game){
        if(musicState){
            game.startMusic();
        }
        else{
            game.endMusic();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My AircraftWar");
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        frame.setContentPane(new StartPage().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1024);
        frame.setVisible(true);
    }
}

//        TopPanel = new JPanel();
//        BottomPanel = new JPanel();
//
//        TopPanel.setLayout(null);
//        TopPanel.setSize(500, 400);
//        BottomPanel.setSize(500,400);
//
//        EasyButton = new JButton("简单模式");
//        EasyButton.setBounds(200,50,120,50);
//
//        MediumButton = new JButton("普通模式");
//        MediumButton.setBounds(200,150,120,50);
//
//        HardButton = new JButton("困难模式");
//        HardButton.setBounds(200, 250,120,50);
//
//        TopPanel.add(EasyButton);
//        TopPanel.add(MediumButton);
//        TopPanel.add(HardButton);
//
//        BottomPanel.setLayout(null);
//
//        label = new JLabel("音效");
//        MusicSwitch = new JComboBox<>();
//        MusicSwitch.setBounds(200, 600, 80,20);
//        label.setBounds(160,600,30,20);
//        MusicSwitch.addItem("开");
//        MusicSwitch.addItem("关");
//
//        BottomPanel.add(MusicSwitch);
//        BottomPanel.add(label);


//                Game game = new Game();
//
//                edu.hitsz.layout.CardLayoutDemo.cardPanel.add(game.getMainPanel());
//                edu.hitsz.layout.CardLayoutDemo.cardLayout.last(edu.hitsz.layout.CardLayoutDemo.cardPanel);
//
//                game.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_1);

//                cardLayoutDemo.getFrame().setTitle("My Aircraftwar - Easy Mode");
//                cardLayoutDemo.getFrame().add(game);
//                cardLayoutDemo.getFrame().setSize(800, 1024);
//                cardLayoutDemo.getFrame().setVisible(true);

//                game.action();

//                JFrame frame = new JFrame("My Aircraftwar - Easy Mode");
//                frame.setSize(500, 800);
//
//                frame.add(game);
//                frame.setVisible(true);
