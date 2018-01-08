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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QualityCalculateFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
					QualityCalculateFrame frame = new QualityCalculateFrame();
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
	public QualityCalculateFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 786, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ResultSet resultSetService = null;
		try {
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "root");
			resultSetService = getAllServicesFromDB(connection, "public");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<ServiceEntity> ServiceList = generateServiceList(resultSetService);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 750, 363);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblWybierzUsugDla = new JLabel("Wybierz us\u0142ug\u0119, dla kt\u00F3rej chcesz dokona\u0107 oceny jako\u015Bci");
		lblWybierzUsugDla.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWybierzUsugDla.setBounds(155, 11, 440, 24);
		panel.add(lblWybierzUsugDla);
		for(int i = 0; i< ServiceList.size(); i++){
			JLabel lblUsuga = new JLabel(ServiceList.get(i).name);
			lblUsuga.setBounds(10, 39+(i*30), 371, 14);
			panel.add(lblUsuga);
			JButton btnOblicz = new JButton("Oblicz");
			btnOblicz.setName(Integer.toString(ServiceList.get(i).id_service));
			btnOblicz.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					calculateQuality(Integer.parseInt(btnOblicz.getName()),"public");
				}

				
			});
			btnOblicz.setBounds(400, 35+(i*30), 89, 23);
			panel.add(btnOblicz);
		};
		
		
		
		
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
	private void calculateQuality(int id_service, String dbName) {
		Double P=0.00;
		Double O=0.00;
		Double S=0.00;
		String getQuestionnare = "select * " + "from " + dbName + ".questionare WHERE service_id_service = " + id_service;
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "root");
			Statement stmt1 = null;
			stmt1 = connection.createStatement(1004, 1007);
			ResultSet resultSetQuestionnare = null;
			resultSetQuestionnare = stmt1.executeQuery(getQuestionnare);
			resultSetQuestionnare.next();
				int questionareId1 = resultSetQuestionnare.getInt("id_questionnaire");
				String getQuestions1 = "select * from " + dbName + ".question WHERE questionare_id_questionnaire = " + questionareId1;
				ResultSet resultSetQuestions1 = null;
				Statement stmt2 = null;
				stmt2 = connection.createStatement(1004, 1007);
				resultSetQuestions1 = stmt2.executeQuery(getQuestions1);
				while(resultSetQuestions1.next()){
					int criteriaId = resultSetQuestions1.getInt("criteria_id_criteria");
					int answerId = resultSetQuestions1.getInt("answer_id_answer");
						//Pobranie wartoœci kryterium
						String getCriteria = "select * from " + dbName + ".criteria WHERE id_criteria = " + criteriaId;
						ResultSet resultSetCriteria = null;
						Statement stmt3 = null;
						stmt3 = connection.createStatement(1004, 1007);
						resultSetCriteria = stmt3.executeQuery(getCriteria);
						resultSetCriteria.next();
						int criteriaRatioId = resultSetCriteria.getInt("criteria_ratio_id_cr");
						String getCriteriaValue = "select * from " + dbName + ".criteria_ratio WHERE id_cr = " + criteriaRatioId;
						ResultSet resultSetCriteriaRatio = null;
						Statement stmt4 = null;
						stmt4 = connection.createStatement(1004, 1007);
						resultSetCriteriaRatio = stmt4.executeQuery(getCriteriaValue);
						resultSetCriteriaRatio.next();
						Double crValue = resultSetCriteriaRatio.getDouble("criteria_value");
						
						//Pobranie wartoœci odpowiedzi
						String getAnswer = "select * from " +dbName + ".answer WHERE id_answer = " + answerId;
						ResultSet resultSetAnswers = null;
						Statement stmt5 = null;
						stmt5 = connection.createStatement(1004, 1007);
						resultSetAnswers = stmt5.executeQuery(getAnswer);
						resultSetAnswers.next();
						int answerValue = resultSetAnswers.getInt("value");
						O = O + (answerValue*crValue);
				}
				resultSetQuestionnare.next();
				int questionareId = resultSetQuestionnare.getInt("id_questionnaire");
				String getQuestions = "select * from " + dbName + ".question WHERE questionare_id_questionnaire = " + questionareId;
				ResultSet resultSetQuestions = null;
				Statement stmt6 = null;
				stmt6 = connection.createStatement(1004, 1007);
				resultSetQuestions = stmt6.executeQuery(getQuestions);
				while(resultSetQuestions.next()){
					int criteriaId = resultSetQuestions.getInt("criteria_id_criteria");
					int answerId = resultSetQuestions.getInt("answer_id_answer");
						//Pobranie wartoœci kryterium
						String getCriteria = "select * from " + dbName + ".criteria WHERE id_criteria = " + criteriaId;
						ResultSet resultSetCriteria = null;
						Statement stmt3 = null;
						stmt3 = connection.createStatement(1004, 1007);
						resultSetCriteria = stmt3.executeQuery(getCriteria);
						resultSetCriteria.next();
						int criteriaRatioId = resultSetCriteria.getInt("criteria_ratio_id_cr");
						String getCriteriaValue = "select * from " + dbName + ".criteria_ratio WHERE id_cr = " + criteriaRatioId;
						ResultSet resultSetCriteriaRatio = null;
						Statement stmt4 = null;
						stmt4 = connection.createStatement(1004, 1007);
						resultSetCriteriaRatio = stmt4.executeQuery(getCriteriaValue);
						resultSetCriteriaRatio.next();
						Double crValue = resultSetCriteriaRatio.getDouble("criteria_value");
						
						//Pobranie wartoœci odpowiedzi
						String getAnswer = "select * from " +dbName + ".answer WHERE id_answer = " + answerId;
						ResultSet resultSetAnswers = null;
						Statement stmt5 = null;
						stmt5 = connection.createStatement(1004, 1007);
						resultSetAnswers = stmt5.executeQuery(getAnswer);
						resultSetAnswers.next();
						int answerValue = resultSetAnswers.getInt("value");
						P = P + (answerValue*crValue);
				
						

						
				}
				
			
		} catch (SQLException e) {
			System.out.println("Query siê wyjeba³o");
			e.printStackTrace();
		}
		//Przypisanie oceny jakoœci do us³ugi
		S = P-O;
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "root");
			String updateQualityScore = "UPDATE public.service SET quality_score= ? WHERE id_service =" + id_service +";";
			PreparedStatement preparedStatement = connection.prepareStatement(updateQualityScore);
			preparedStatement.setDouble(1, S);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
