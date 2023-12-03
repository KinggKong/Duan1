package com.thuvien.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;

import com.thuvien.dao.ThongKeDao;
import com.thuvien.dao.TopSachMuonDao;
import com.thuvien.entity.SachTopLuotMuon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongKeTopSachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model;
	TopSachMuonDao topSachMuonDao = new TopSachMuonDao();
	ThongKeDao thongKeDao = new ThongKeDao();
	private JComboBox<Integer> cbxYear;
	DefaultComboBoxModel modelCbxYear = new DefaultComboBoxModel<>();

	public ThongKeTopSachJPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Top 10 sách có lượt mượn cao nhất");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(393, 10, 666, 51);
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(87, 150, 1148, 336);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] column = { "Tên Sách", "Số Lượt Mượn" };
		Object[][] row = {

		};
		model = new DefaultTableModel(row, column);
		table.setModel(model);

		cbxYear = new JComboBox();
		cbxYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Integer year = (Integer) cbxYear.getSelectedItem();
				fillDataToTable(year);
			}
		});
		cbxYear.setBounds(575, 88, 173, 26);
		cbxYear.setModel(modelCbxYear);
		add(cbxYear);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				fillCbxYear();
				return null;
			}

			@Override
			protected void done() {
				super.done();
			}

		};
		worker.execute();
	}

	void fillCbxYear() {
		List<Integer> listYear = thongKeDao.getAllYear();
		modelCbxYear.removeAllElements();
		for (Integer year : listYear) {
			modelCbxYear.addElement(year);
		}
	}

	void fillDataToTable(Integer year) {
		SwingWorker<List<SachTopLuotMuon>, Void> worker = new SwingWorker<List<SachTopLuotMuon>, Void>() {

			@Override
			protected List<SachTopLuotMuon> doInBackground() throws Exception {
				return topSachMuonDao.getTopSachLuotMuon(year);
			}

			@Override
			protected void done() {
				try {
					List<SachTopLuotMuon> list = get();
					model.setRowCount(0);
					for (SachTopLuotMuon s : list) {
						Object[] row = { s.getTenSach(), s.getLuotMuon() };
						model.addRow(row);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}
}
