package de.fhaachen.gpm.practicum.RestServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@RestController
public class StudentController {
	MysqlConnect mysqlConnect;

	@RequestMapping("/student")
	public void addThesisForStudent(@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "thesis_title") String thesis_title,
			@RequestParam(value = "supervisor") String supervisor) {
		System.out.println("StudentId: " + studentId);
		System.out.println("Thesis_Title: " + thesis_title);

		Statement statement = null;
		ResultSet resultSet = null;

		mysqlConnect = new MysqlConnect();

		try {
			mysqlConnect.connect("gpm_server1");
			statement = mysqlConnect.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from student where id = '" + studentId + "'");

			if (resultSet.next()) {
				System.out.println("User enthalten");
				mysqlConnect.disconnect();
				mysqlConnect.connect("gpm_server2");
				statement = mysqlConnect.getConnection().createStatement();
				statement.executeUpdate("Insert Into thesis(student_id, title, supervisor, approved) VALUES("
						+ studentId + ",'" + thesis_title + "', '" + supervisor + "', -1)");
			} else {
				System.out.println("User nicht enthalten");
			}

			mysqlConnect.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/studentApproval")
	public void updateApprovalForStudent(@RequestParam(value = "studentId") Long studentId,@RequestParam(value = "result") String result) {
		System.out.println("StudentId: " + studentId);
		System.out.println("Result: " + result);

		Statement statement = null;

		mysqlConnect = new MysqlConnect();

		try {

			mysqlConnect.connect("gpm_server2");
			statement = mysqlConnect.getConnection().createStatement();
			
			if(result.equals("true")) {
				statement.executeUpdate("Update thesis set approved = 1 where student_id = " + studentId);
			}else {
				statement.executeUpdate("Update thesis set approved = 0 where student_id = " + studentId);
			}

			mysqlConnect.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
