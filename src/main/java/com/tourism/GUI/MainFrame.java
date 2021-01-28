package com.tourism.GUI;

import javax.swing.JFrame;

/**
 * MainFrame
 */
public class MainFrame extends JFrame {
  static final long serialVersionUID = 4L;

  public MainFrame() {
    initComp();
  }

  public void initComp() {
    if (true) {
      LoginFrom loginFrom = new LoginFrom();
    }

    this.setSize(500, 500);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    MainFrame main = new MainFrame();
  }
}

class LoginFrom extends JFrame {

  public LoginFrom() {
    init();
  }

  public void init() {
    this.setTitle("Dang nhap");
    this.setSize(300, 500);
    this.setVisible(true);
  }

}
