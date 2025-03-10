package controller.Reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import model.modelAldComputerService.AldComputerService;
import view.viewReports.ReportsDialogView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportsController {

    private final ReportsDialogView view;

    public ReportsController(ReportsDialogView reportsView, AldComputerService service) {
        this.view = reportsView;
        this.view.setSaveReportAsPDFButtonActionListener(this.saveReportAsPDFButtonActionListener());
    }

    private Connection getDBConnection() {
        String URL = "jdbc:mariadb://" + view.getIpAddress() + ":" + view.getPort() + "/" + view.getDbName();
        String user = view.getUser();
        String password = view.getPassword();

        try {
            Connection connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Conectado a: " + URL);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
            return null;
        }
    }

    private ActionListener saveReportAsPDFButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReportAndExportToPDF();
            }
        };
    }

    private void createReportAndExportToPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar informe como PDF");
        fileChooser.setSelectedFile(new File("Report_5_5.pdf"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        int userSelection = fileChooser.showSaveDialog(view);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try (Connection connection = getDBConnection()) {
                if (connection != null) {
                    connection.setAutoCommit(false);
                    JasperReport report = JasperCompileManager.compileReport(ReportsController.class.getClassLoader().getResourceAsStream("reportTemplate/Report_5_4.jrxml"));
                    JasperPrint print = JasperFillManager.fillReport(report, null, connection);
                    JasperViewer.viewReport(print, true);
                    JasperExportManager.exportReportToPdfFile(print, filePath);
                    System.out.println("Informe exportado a PDF correctamente en: " + filePath);
                }

            } catch (SQLException | JRException e) {
                System.out.println("Error durante la generación del reporte: " + e.getMessage());
            }
        } else {
            System.out.println("Exportación de informe cancelada por el usuario.");
        }
    }
}
