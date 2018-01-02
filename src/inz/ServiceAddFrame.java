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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
					ServiceAddFrame frame = new ServiceAddFrame();
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
	public ServiceAddFrame() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDodajNowUsug = new JLabel("Dodaj now\u0105 us\u0142ug\u0119");
		lblDodajNowUsug.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDodajNowUsug.setBounds(114, 11, 205, 25);
		contentPane.add(lblDodajNowUsug);

		JPanel panel = new JPanel();
		panel.setBounds(10, 35, 414, 215);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNazwaNowegoSerwisu = new JLabel("Nazwa nowej us\u0142ugi: ");
		lblNazwaNowegoSerwisu.setBounds(10, 11, 137, 25);
		panel.add(lblNazwaNowegoSerwisu);

		textField = new JTextField();
		textField.setBounds(122, 13, 282, 72);
		panel.add(textField);
		textField.setColumns(10);
		
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
				try {
					insertServiceToDB(connection, "public", name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnWylij.setBounds(315, 181, 89, 23);
		panel.add(btnWylij);
	}

	public static void insertServiceToDB(Connection con, String dbName, String name) throws SQLException {

		Statement stmt = null;
		String insertQuery = "insert into " + dbName + ".service" + "(id_service, name, quality_score) values ("
				+ "nextval('seq_service_id')" + ", '" + name + "', " + "0" + ");";

		try {
			stmt = con.createStatement(1004, 1007);
			stmt.executeUpdate(insertQuery);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
	}
}
