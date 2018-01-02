package inz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AnswerAddFrame extends JFrame {
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
					AnswerAddFrame frame = new AnswerAddFrame();
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
	public AnswerAddFrame() {
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
		try {
			resultSetService = getAllServicesFromDB(connection, "public");
			resultSetQuestionnare = getAllQuestionnaireFromDB(connection, "public");
			resultSetQuestion = getAllQuestionsFromDB(connection, "public");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1007, 860);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 971, 539);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblDodajOdpowiedzi = new JLabel("Wybierz kwestionariusz");
		lblDodajOdpowiedzi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDodajOdpowiedzi.setBounds(359, 11, 252, 27);
		panel.add(lblDodajOdpowiedzi);
		
		JButton btnPowrtDoEkranu = new JButton("Powr\u00F3t do ekranu g\u0142\u00F3wnego");
		btnPowrtDoEkranu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.show(false);;
			}
		});
		btnPowrtDoEkranu.setBounds(696, 505, 190, 23);
		panel.add(btnPowrtDoEkranu);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 951, 765);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblUdzielOdpowiedziWypeniajc = new JLabel(
				"Udziel odpowiedzi wype\u0142niaj\u0105c pola warto\u015Bciami z zakresu 1-7");
		lblUdzielOdpowiedziWypeniajc.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUdzielOdpowiedziWypeniajc.setBounds(200, 10, 550, 42);
		panel_1.add(lblUdzielOdpowiedziWypeniajc);
		Map<Integer, Integer> Answers = new LinkedHashMap<Integer, Integer>();
		JButton btnWylij = new JButton("Wy\u015Blij");
		btnWylij.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setVisible(false);
				panel.setVisible(true);
				for (Map.Entry<Integer, Integer> entry : Answers.entrySet()) {
					Connection connection = null;
					try {

						connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres",
								"postgres", "root");

					} catch (SQLException e) {

						System.out.println("Connection Failed! Check output console");
						e.printStackTrace();

					}

					try {
						insertAnswertoDB(connection, "public", entry.getKey(), entry.getValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		btnWylij.setBounds(823, 731, 118, 23);
		panel_1.add(btnWylij);

		panel_1.setVisible(false);

		ArrayList<QuestionnareObject> QuestionnareList = generateQuestionnareList(resultSetService,
				resultSetQuestionnare);
		for (int i = 0; i < QuestionnareList.size(); i++) {
			JButton btnNewButton = new JButton(QuestionnareList.get(i).name);
			btnNewButton.setBounds(10, (54 + (i * 30)), 951, 27);
			btnNewButton.setName(Integer.toString(QuestionnareList.get(i).id));
			btnNewButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					panel.setVisible(false);
					panel_1.setVisible(true);
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
					try {
						resultSetService = getAllServicesFromDB(connection, "public");
						resultSetQuestionnare = getAllQuestionnaireFromDB(connection, "public");
						resultSetQuestion = getAllQuestionsFromDB(connection, "public");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList<QuestionEntity> QuestionList = generateQuestionList(resultSetQuestionnare, resultSetQuestion,
							Integer.parseInt(btnNewButton.getName()));
					int j = 0;
					for (j = 0; j < QuestionList.size(); j++) {
						JTextField textField = new JTextField(10);
						textField.setBounds(783, 50 + 30 * j, 86, 20);
						textField.setName(Integer.toString(QuestionList.get(j).id_question));
						textField.addFocusListener(new FocusAdapter() {
							@Override
							public void focusLost(FocusEvent arg0) {
								Answers.put(Integer.parseInt(textField.getName()),
										Integer.parseInt(textField.getText()));
							}
						});
						panel_1.add(textField);
						textField.setColumns(10);
						JLabel lblPytanie = new JLabel(QuestionList.get(j).description);
						lblPytanie.setBounds(10, 50 + 30 * j, 672, 27);
						panel_1.add(lblPytanie);
					}
				}

			});
			panel.add(btnNewButton);
		}
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

	public static void insertAnswertoDB(Connection con, String dbName, int idAnswer, int value) throws SQLException {

		Statement stmt = null;
		String insertQuery = "insert into " + dbName + ".answer" + "(id_answer, value) values(" + idAnswer + "," + value
				+ ");";
		String updateQuery = "update " + dbName + ".question" + " set answer_id_answer" +"=" + idAnswer
				+ " where id_question=" + idAnswer + ";";

		try {
			stmt = con.createStatement(1004, 1007);
			stmt.executeUpdate(insertQuery);
			stmt.executeUpdate(updateQuery);
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
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

	private ArrayList<QuestionEntity> generateQuestionList(ResultSet restultSetQuestionnare,
			ResultSet resultSetQuestion, int questionnareId) {
		ArrayList<QuestionEntity> QuestionList = new ArrayList<QuestionEntity>();
		try {
			while (restultSetQuestionnare.previous()) {
			}
			;
			while (resultSetQuestion.previous()) {
			}
			;
			while (restultSetQuestionnare.next()) {
				while (resultSetQuestion.next()) {
					if ((restultSetQuestionnare.getInt("id_questionnaire") == resultSetQuestion
							.getInt("questionare_id_questionnaire"))
							&& questionnareId == restultSetQuestionnare.getInt("id_questionnaire")) {
						QuestionEntity question = new QuestionEntity(resultSetQuestion.getInt("id_question"),
								resultSetQuestion.getString("description"),
								resultSetQuestion.getInt("criteria_id_criteria"),
								resultSetQuestion.getInt("questionare_id_questionnaire"),
								resultSetQuestion.getInt("answer_id_answer"));
						QuestionList.add(question);
					}
				}
				while (resultSetQuestion.previous()) {
				}
				;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return QuestionList;
	}
}
