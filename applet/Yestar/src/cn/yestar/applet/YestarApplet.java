package cn.yestar.applet;

import java.awt.Color;

import prefuse.util.ui.JPrefuseApplet;

public class YestarApplet extends JPrefuseApplet {
	public void init() {
		this.setBackground(new Color(200, 200, 200));
        this.setContentPane(
            cn.yestar.YestarApplication.demo());
    }
}
