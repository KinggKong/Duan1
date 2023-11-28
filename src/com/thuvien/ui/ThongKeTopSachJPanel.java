package com.thuvien.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.thuvien.dao.TopSachMuonDao;
import com.thuvien.entity.SachTopLuotMuon;

public class ThongKeTopSachJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model;
	TopSachMuonDao topSachMuonDao = new TopSachMuonDao();

	/**
	 * Create the panel.
	 */
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
		scrollPane.setBounds(84, 128, 1148, 336);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] column = { "Tên Sách", "Số Lượt Mượn" };
		Object[][] row = {

		};
		model = new DefaultTableModel(row, column);
		table.setModel(model);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				fillDataToTable();
				return null;
			}

			@Override
			protected void done() {
				super.done();
			}

		};
		worker.execute();
	}

	void fillDataToTable() {
		SwingWorker<List<SachTopLuotMuon>, Void> worker = new SwingWorker<List<SachTopLuotMuon>, Void>() {

			@Override
			protected List<SachTopLuotMuon> doInBackground() throws Exception {
				return topSachMuonDao.getTopSachLuotMuon();
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
