package com.tourism.GUI;

import javax.swing.JFrame;

/**
 * MainFrame
 */
public class MainFrame extends JFrame {
  public MainFrame() {
    initComp();
  }

  public void initComp() {
    this.setSize(500, 500);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    MainFrame main = new MainFrame();
  }
}
