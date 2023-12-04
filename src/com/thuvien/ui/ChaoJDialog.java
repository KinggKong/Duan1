package com.thuvien.ui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class ChaoJDialog extends JDialog {

	private JProgressBar pgbLoading;

	public ChaoJDialog(Frame parent, boolean modal) {
		super(parent, modal);
		init();
		setUndecorated(true);
		setBounds(100, 100, 720, 392);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChaoJDialog.class.getResource("/icon/FPT_Polytechnic.png")));
		lblNewLabel.setBounds(199, 10, 391, 314);
		getContentPane().add(lblNewLabel);

		pgbLoading = new JProgressBar();
		pgbLoading.setStringPainted(true);
		pgbLoading.setForeground(Color.GREEN);
		pgbLoading.setBounds(0, 364, 720, 28);
		getContentPane().add(pgbLoading);

	}

	public void init() {
//		ActionListener actionListener = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Xử lý công việc ở đây khi Timer kích hoạt
//			}
//		};
//
//		Timer timer = new Timer(10, actionListener);
//		timer.start(); // Bắt đầu Timer
		new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = pgbLoading.getValue();
				if (value < pgbLoading.getMaximum()) {
					pgbLoading.setValue(value + 1);
				} else {
					ChaoJDialog.this.dispose();
				}

			}
		}).start();
		;
	}
}
