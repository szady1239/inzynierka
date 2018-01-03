package inz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.ComponentOrientation;
import java.awt.Component;

public class QuestionnareAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
					QuestionnareAddFrame frame = new QuestionnareAddFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuestionnareAddFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 31, 414, 219);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPodajNazwKwestionariusza = new JLabel("Podaj nazw\u0119 kwestionariusza:");
		lblPodajNazwKwestionariusza.setBounds(10, 11, 164, 14);
		panel.add(lblPodajNazwKwestionariusza);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField.setBounds(184, 8, 220, 77);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPodajIdUsugi = new JLabel(
				"<html>Podaj id us\u0142ugi, do kt\u00F3rej</br> kwestionariusz ma by\u0107 przypisany:</html>");
		lblPodajIdUsugi.setAutoscrolls(true);
		lblPodajIdUsugi.setVerticalAlignment(SwingConstants.TOP);
		lblPodajIdUsugi.setHorizontalAlignment(SwingConstants.LEFT);
		lblPodajIdUsugi.setBounds(10, 97, 156, 49);
		panel.add(lblPodajIdUsugi);

		textField_1 = new JTextField();
		textField_1.setBounds(184, 96, 220, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnWylij = new JButton("Wy\u015Blij");
		btnWylij.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// WYWO£ANIE ZAPISANIA DO BAZY
				Connection connection = null;

				try {

					connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
							"root");

				} catch (SQLException e) {

					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();

				}
				String name = textField.getText();
				int serviceId = Integer.parseInt(textField_1.getText());
				try {
					insertQuestionnareToDB(connection, "public", name, serviceId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnWylij.setBounds(315, 185, 89, 23);
		panel.add(btnWylij);

		JLabel lblDodajNowyKwestionariusz = new JLabel("Dodaj nowy kwestionariusz");
		lblDodajNowyKwestionariusz.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDodajNowyKwestionariusz.setBounds(73, 6, 287, 25);
		contentPane.add(lblDodajNowyKwestionariusz);
	}

	public static void insertQuestionnareToDB(Connection con, String dbName, String name, int serviceId)
			throws SQLException {

		Statement stmt = null;
		String insertQuery = "insert into " + dbName + ".questionare"
				+ "(id_questionnaire, name, service_id_service) values (" + "nextval('seq_questionnare_id')" + ", '"
				+ name + "', " + serviceId + ");";

		try {
			stmt = con.createStatement(1004, 1007);
			stmt.executeUpdate(insertQuery);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
	}
}
