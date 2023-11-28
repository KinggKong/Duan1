package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.thuvien.dao.ThongKeDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongKeDoanhThuJPanel extends JPanel {
	DefaultComboBoxModel<Integer> modelCbxYear = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Integer> modelCbxMonth = new DefaultComboBoxModel<>();
	ThongKeDao thongKeDao = new ThongKeDao();
	private JComboBox monthComboBox;
	private JComboBox yearComboBox;

	public ThongKeDoanhThuJPanel() {
		setLayout(new BorderLayout());

		// Create JComboBox for month selection
		monthComboBox = new JComboBox<>();
		fillCbxMonth();
		monthComboBox.setModel(modelCbxMonth);

		// Create JComboBox for year selection
		yearComboBox = new JComboBox<>();
		fillCbxYear();
		yearComboBox.setModel(modelCbxYear);

		JButton updateButton = new JButton("Refresh");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateChart();
			}
		});

		// Create a JPanel for holding the JComboBox components horizontally
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.add(monthComboBox);
		comboBoxPanel.add(yearComboBox);
		comboBoxPanel.add(updateButton);

		// Add the JPanel with JComboBox components to the top of the panel
		add(comboBoxPanel, BorderLayout.NORTH);

		// Create the pie chart
		JFreeChart pieChart = createPieChart();
		ChartPanel chartPanel = new ChartPanel(pieChart);

		// Add the ChartPanel to the center of the panel
		add(chartPanel, BorderLayout.CENTER);
	}

	public PieDataset createDataset(float tienPhat, float tienThe) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Tiền Thẻ", tienThe);
		dataset.setValue("Tiền Phạt", tienPhat);
		return dataset;
	}

	public JFreeChart createPieChart() {
		Integer month = (Integer) monthComboBox.getSelectedItem();
		Integer year = (Integer) yearComboBox.getSelectedItem();
		float tienPhat = thongKeDao.getTienPhat(month, year).getTienPhat();
		float tienThe = thongKeDao.getTienThe(month, year).getTienThe();
		JFreeChart chart = ChartFactory.createPieChart(
				"Biểu đồ thống kê doanh thu tháng " + month + "/" + year + "".toUpperCase(),
				createDataset(tienPhat, tienThe), true, true, true);
		chart.getPlot().setForegroundAlpha(0.7f);
		PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1} ({2})",
				new java.text.DecimalFormat("0"), new java.text.DecimalFormat("0%"));
		((org.jfree.chart.plot.PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
		return chart;
	}

	void fillCbxYear() {
		List<Integer> listYear = thongKeDao.getAllYear();
		modelCbxYear.removeAllElements();
		for (Integer year : listYear) {
			modelCbxYear.addElement(year);
		}
	}

	void fillCbxMonth() {
		List<Integer> listMonth = thongKeDao.getAllMonth();
		modelCbxMonth.removeAllElements();
		for (Integer year : listMonth) {
			modelCbxMonth.addElement(year);
		}
	}

	private void updateChart() {
		// Perform the logic to update the chart based on the selected month and year
		// For simplicity, this example updates the chart with random values
		JFreeChart updatedChart = createPieChart();
		ChartPanel chartPanel = new ChartPanel(updatedChart);
		chartPanel.setPreferredSize(new Dimension(1325, 575));
		remove(1); // Remove the existing ChartPanel
		add(chartPanel, BorderLayout.CENTER); // Add the updated ChartPanel
		revalidate(); // Refresh the layout
		repaint(); // Repaint the panel
	}

}
