package inz;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainFrame {
	private JFrame frame;
	JDBCDriver driver;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField txtAsd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public MainFrame() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "root");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
		ResultSet resultSetService = null;
		ResultSet resultSetQuestionnare = null;
		ResultSet resultSetQuestion = null;
		ResultSet resultSetCriteriaRatio = null;
		ResultSet resultSetCriteria = null;
		ResultSet resultSetAnswer = null;
		try {
			resultSetService = getAllServicesFromDB(connection, "public");
			resultSetQuestionnare = getAllQuestionnaireFromDB(connection, "public");
			resultSetQuestion = getAllQuestionsFromDB(connection, "public");
			resultSetCriteriaRatio = getAllCriteriaRatioFromDB(connection, "public");
			resultSetCriteria = getAllCriteriaFromDB(connection, "public");
			resultSetAnswer = getAllAnswersFromDB(connection, "public");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<ServiceEntity> ServiceList = generateServiceList(resultSetService);
		ArrayList<CriteriaObject> CriteriaList = generateCriteriaList(resultSetCriteria, resultSetCriteriaRatio);
		ArrayList<QuestionnareObject> QuestionnareList = generateQuestionnareList(resultSetService,
				resultSetQuestionnare);
		frame = new JFrame();
		frame.setBounds(100, 100, 1820, 980);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblOcenaJakoci = new JLabel("US\u0141UGI");
		lblOcenaJakoci.setBounds(828, 6, 88, 25);
		lblOcenaJakoci.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblOcenaJakoci);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 6, 1784, 432);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		table_1 = new JTable();
		table_1.setBounds(0, 27, 1784, 163);
		panel_3.add(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
						{ ServiceList.get(0).id_service, ServiceList.get(0).name, ServiceList.get(0).quality_score },
						{ ServiceList.get(1).id_service, ServiceList.get(1).name, ServiceList.get(1).quality_score },
						{ ServiceList.get(2).id_service, ServiceList.get(2).name, ServiceList.get(2).quality_score }, },
				new String[] { "New column", "New column", "New column" }));
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ServiceAddFrame serviceAddFrame = new ServiceAddFrame();
				serviceAddFrame.main(null);
				//DODAWANIE US£UGI
			}
		});
		label_1.setIcon(new ImageIcon("E:\\Studia\\ProjektINZ\\inz\\icons\\add-128.png"));
		label_1.setSize(new Dimension(180, 180));
		label_1.setBounds(10, 240, 191, 181);
		panel_3.add(label_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 449, 875, 481);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblKwestionariusze = new JLabel("KWESTIONARIUSZE");
		lblKwestionariusze.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKwestionariusze.setBounds(333, 11, 208, 29);
		panel.add(lblKwestionariusze);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] {
				{ QuestionnareList.get(0).id, QuestionnareList.get(0).name, QuestionnareList.get(0).service },
				{ QuestionnareList.get(1).id, QuestionnareList.get(1).name, QuestionnareList.get(1).service },
				{ QuestionnareList.get(2).id, QuestionnareList.get(2).name, QuestionnareList.get(2).service },
				{ QuestionnareList.get(3).id, QuestionnareList.get(3).name, QuestionnareList.get(3).service }, },
				new String[] { "L. p", "Nazwa", "Us\u0142uga" }));
		table_2.getColumnModel().getColumn(0).setMaxWidth(200);
		table_2.setBounds(10, 51, 855, 227);
		panel.add(table_2);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnswerAddFrame answerAddFrame = new AnswerAddFrame();
				answerAddFrame.main(null);
			}
		});
		label.setSize(new Dimension(180, 180));
		label.setIcon(new ImageIcon("E:\\Studia\\ProjektINZ\\inz\\icons\\Answer.png"));
		label.setBounds(211, 289, 191, 181);
		panel.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("E:\\Studia\\ProjektINZ\\inz\\icons\\add-128.png"));
		label_2.setSize(new Dimension(180, 180));
		label_2.setBounds(10, 289, 191, 181);
		panel.add(label_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(919, 449, 875, 481);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblKryteria = new JLabel("KRYTERIA");
		lblKryteria.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKryteria.setBounds(381, 11, 112, 31);
		panel_1.add(lblKryteria);

		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
				new Object[][] { { CriteriaList.get(0).id, CriteriaList.get(0).name, CriteriaList.get(0).value },
						{ CriteriaList.get(1).id, CriteriaList.get(1).name, CriteriaList.get(1).value },
						{ CriteriaList.get(2).id, CriteriaList.get(2).name, CriteriaList.get(2).value },
						{ CriteriaList.get(3).id, CriteriaList.get(3).name, CriteriaList.get(3).value },
						{ CriteriaList.get(4).id, CriteriaList.get(4).name, CriteriaList.get(4).value }, },
				new String[] { "L. p", "Nazwa", "Warto\u015B\u0107" }));
		table_3.setBounds(10, 53, 855, 224);
		panel_1.add(table_3);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("E:\\Studia\\ProjektINZ\\inz\\icons\\add-128.png"));
		label_3.setSize(new Dimension(180, 180));
		label_3.setBounds(10, 288, 191, 181);
		panel_1.add(label_3);
	}

	private ArrayList<ServiceEntity> generateServiceList(ResultSet resultSetService) {
		ArrayList<ServiceEntity> ServiceList = new ArrayList<ServiceEntity>();
		try {

			while (resultSetService.next()) {
				ServiceEntity service = new ServiceEntity(resultSetService.getInt("id_service"),
						resultSetService.getString("name"), resultSetService.getDouble("quality_score"));
				ServiceList.add(service);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ServiceList;
	}

	private ArrayList<CriteriaObject> generateCriteriaList(ResultSet resultSetCriteria,
			ResultSet restultSetCriteriaRatio) {
		ArrayList<CriteriaObject> CriteriaList = new ArrayList<CriteriaObject>();
		try {
			while (resultSetCriteria.next()) {
				while (restultSetCriteriaRatio.next()) {
					if (resultSetCriteria.getInt("criteria_ratio_id_cr") == restultSetCriteriaRatio.getInt("id_cr")) {
						CriteriaObject criteria = new CriteriaObject(resultSetCriteria.getInt("id_criteria"),
								resultSetCriteria.getString("name"),
								restultSetCriteriaRatio.getDouble("criteria_value"));
						CriteriaList.add(criteria);
					}
				}
				while (restultSetCriteriaRatio.previous()) {
				}
				;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return CriteriaList;
	}

	private ArrayList<QuestionnareObject> generateQuestionnareList(ResultSet resultSetService,
			ResultSet restultSetQuestionnare) {

		ArrayList<QuestionnareObject> QuestionnareList = new ArrayList<QuestionnareObject>();
		try {
			while (resultSetService.previous()) {
			}
			;
			while (restultSetQuestionnare.next()) {
				while (resultSetService.next()) {
					if (restultSetQuestionnare.getInt("service_id_service") == resultSetService.getInt("id_service")) {
						QuestionnareObject questionnare = new QuestionnareObject(
								restultSetQuestionnare.getInt("id_questionnaire"),
								restultSetQuestionnare.getString("name"), resultSetService.getString("name"));
						QuestionnareList.add(questionnare);
					}
				}
				while (resultSetService.previous()) {
				}
				;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return QuestionnareList;
	}

	public static ResultSet getAllServicesFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".service";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getAllAnswersFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".answer";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getAllCriteriaFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".criteria";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getAllCriteriaRatioFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".criteria_ratio";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getAllQuestionnaireFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".questionare";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getAllQuestionsFromDB(Connection con, String dbName) throws SQLException {

		Statement stmt = null;
		String query = "select * " + "from " + dbName + ".question";
		ResultSet rs = null;
		try {
			stmt = con.createStatement(1004, 1007);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		return rs;
	}
}
