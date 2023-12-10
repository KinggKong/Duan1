package com.thuvien.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.labels.PieSectionLabelGenerator;
//import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;

import com.thuvien.dao.ThongKeDao;

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

		JButton btnXuatPDF = new JButton("Xuất PDF");
		btnXuatPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToPDF();
			}
		});
		comboBoxPanel.add(btnXuatPDF);

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

//	private void exportToPDF() {
//		try {
//			Integer month = (Integer) monthComboBox.getSelectedItem();
//			Integer year = (Integer) yearComboBox.getSelectedItem();
//			JFreeChart pieChart = createPieChart();
//
//			// Tạo file tạm thời PNG cho biểu đồ
//			String tempChartFilePath = "tempChart.png";
//			saveChartAsImage(pieChart, tempChartFilePath, 800, 600);
//
//			// Tạo file PDF và thêm biểu đồ vào nó
//			String pdfFilePath = "H:\\PDF/chartExport.pdf";
//			createPDF(pdfFilePath, "Biểu đồ thống kê doanh thu tháng " + month + "/" + year, tempChartFilePath);
//
//			// Xoá file tạm thời PNG
//			File tempChartFile = new File(tempChartFilePath);
//			tempChartFile.delete();
//
//			JOptionPane.showMessageDialog(null, "PDF exported successfully!");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			JOptionPane.showMessageDialog(null, "Error exporting PDF.");
//		}
//	}

	private void exportToPDF() {
		try {
			Integer month = (Integer) monthComboBox.getSelectedItem();
			Integer year = (Integer) yearComboBox.getSelectedItem();
			JFreeChart pieChart = createPieChart();

			// Tạo file tạm thời PNG cho biểu đồ
			String tempChartFilePath = "tempChart.png";
			saveChartAsImage(pieChart, tempChartFilePath, 800, 600);

			// Tạo tên tệp PDF dựa trên tháng và năm
//			String timestamp = new SimpleDateFormat("MM_yyyy").format(new Date());
			String pdfFilePath = "H:/PDF/chartExport_" + month + "_" + year + ".pdf";

			// Tạo file PDF và thêm biểu đồ vào nó
			createPDF(pdfFilePath, "Doanh thu tháng " + month + "/" + year, tempChartFilePath,
					thongKeDao.getTienPhat(month, year).getTienPhat(), thongKeDao.getTienThe(month, year).getTienThe());

			// Xoá file tạm thời PNG
			File tempChartFile = new File(tempChartFilePath);
			tempChartFile.delete();

			JOptionPane.showMessageDialog(null, "PDF exported successfully!");
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error exporting PDF.");
		}
	}

//	private void createPDF(String pdfFilePath, String title, String chartImagePath) throws Exception {
//		Document document = new Document();
//		PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
//		document.open();
//
//		// Thêm tiêu đề vào tài liệu PDF
//		document.add(new Paragraph(title));
//
//		// Lấy kích thước thực của trang PDF
//		float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
//		float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
//
//		// Tạo đối tượng Image với kích thước đã điều chỉnh
//		com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(chartImagePath);
//		chartImage.scaleToFit(documentWidth, documentHeight);
//
//		// Thêm biểu đồ từ file ảnh vào tài liệu PDF
//		document.add(chartImage);
//
//		// Đóng tài liệu
//		document.close();
//	}

	private void createPDF(String pdfFilePath, String title, String chartImagePath, float tienPhat, float tienThe)
			throws Exception {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
		document.open();

		// Add title to the PDF
		document.add(new Paragraph("Monthly statistics chart: " + title));

		// Add a line break
		document.add(new Paragraph("\n"));

		// Create a table for displaying statistics
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(70);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		// Add headers to the table
		PdfPCell headerCell1 = new PdfPCell(new Phrase("Revenue Type"));
		PdfPCell headerCell2 = new PdfPCell(new Phrase("Amount"));
		table.addCell(headerCell1);
		table.addCell(headerCell2);

		// Add data to the table
		table.addCell("Card Amount ");
		table.addCell(String.valueOf(format.format(tienThe)));
		table.addCell("Fines");
		table.addCell(String.valueOf(format.format(tienPhat)));

		// Add the table to the PDF
		document.add(table);

		// Add a line break
		document.add(new Paragraph("\n"));

		// Add the chart image to the PDF
		float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
		float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
		com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(chartImagePath);
		chartImage.scaleToFit(documentWidth, documentHeight);
		document.add(chartImage);

		// Close the document
		document.close();
	}

	private void saveChartAsImage(JFreeChart chart, String filePath, int width, int height) throws IOException {
		BufferedImage image = chart.createBufferedImage(width, height);
		ImageIO.write(image, "png", new File(filePath));
	}

}
