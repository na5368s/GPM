import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ApprovalService {
	MysqlConnect mysqlConnect;
	
	public boolean isApproved(long studentId) {
		mysqlConnect = new MysqlConnect();
		// init database constants
		
        Statement statement = null;
        ResultSet resultSet = null;
        int countthesis = 0;
        int countmodules = 0;
        int passed_practice_project = 0;
        
        try {
        	mysqlConnect.connect();
			statement = mysqlConnect.getConnection().createStatement();
			resultSet = statement
	                .executeQuery("select count(student.name) As Anzahl_Klausuren, student.passed_practice_project from student inner join written_exam on student_id = student.id \n" + 
	                		"where student_id = '" + studentId +  "'" + 
	                		"group by student.passed_practice_project;");
	        while(resultSet.next()) {
	            countthesis = resultSet.getInt("Anzahl_Klausuren");
	            passed_practice_project = resultSet.getInt("passed_practice_project");
	
	            System.out.println(countthesis + "\t" + passed_practice_project);
	        }
	        
	        resultSet = statement.executeQuery("select count(*) as Anzahl_Module from subject");
            
	        while(resultSet.next()) {
	        	countmodules = resultSet.getInt("Anzahl_Module");
	        }
	        
	        mysqlConnect.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}        
		

		return (countthesis >= (countmodules-2) && passed_practice_project == 1);
	}

}
