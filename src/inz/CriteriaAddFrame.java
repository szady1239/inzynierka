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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriteriaAddFrame extends JFrame {

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
					CriteriaAddFrame frame = new CriteriaAddFrame();
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
	public CriteriaAddFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblDodajNoweKryterium = new JLabel("Dodaj nowe kryterium");
		lblDodajNoweKryterium.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDodajNoweKryterium.setBounds(87, 11, 239, 25);
		panel.add(lblDodajNoweKryterium);

		JLabel lblPodajNazwNowego = new JLabel("Podaj nazw\u0119 nowego kryterium:");
		lblPodajNazwNowego.setBounds(10, 56, 177, 14);
		panel.add(lblPodajNazwNowego);

		JLabel lblPodajWartoWsplczynnika = new JLabel("Podaj warto\u015B\u0107 wsp\u00F3lczynnika kryterium:");
		lblPodajWartoWsplczynnika.setBounds(10, 87, 207, 14);
		panel.add(lblPodajWartoWsplczynnika);

		textField = new JTextField();
		textField.setBounds(262, 53, 142, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(262, 84, 142, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnWylij = new JButton("Wy\u015Blij");
		btnWylij.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// WYWO�ANIE ZAPISANIA DO BAZY
				Connection connection = null;

				try {

					connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
							"root");

				} catch (SQLException e) {

					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();

				}
				String name = textField.getText();
				float criteriaValue = Float.parseFloat((textField_1.getText()));
				try {
					insertQuestionnareToDB(connection, "public", name, criteriaValue);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnWylij.setBounds(315, 205, 89, 23);
		panel.add(btnWylij);
	}

	public static void insertQuestionnareToDB(Connection con, String dbName, String name, float crValue)
			throws SQLException {

		Statement stmt = null;
		String currentSeqCrIdValue = "select currval('seq_cr_id')";
		String insertQuery_cr = "insert into " + dbName + ".criteria_ratio"
				+ "(id_cr, criteria_value) values (" + "nextval('seq_cr_id')" + crValue + ");";
		String insertQuery_criteria = "insert into " + dbName + ".criteria"
				+ "(id_criteria, name, criteria_ratio_id_cr) values (" + "nextval('seq_criteria_id')" + ", '"
				+ name + "', " + ", '"+  currentSeqCrIdValue + "', " + ");";

		try {
			stmt = con.createStatement(1004, 1007);
			stmt.executeUpdate(insertQuery_cr);
			stmt.executeUpdate(insertQuery_criteria);
		} catch (SQLException e) {
			System.out.println("Query si� wyjeba�o");
			e.printStackTrace();
		}
	}

}
