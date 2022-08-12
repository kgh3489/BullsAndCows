package bulls_and_cows.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import bulls_and_cows.utils.DBUtils;

public class DAO {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private String userId;
	private int password;
	private int validPassword;

	Scanner sc = new Scanner(System.in);

	public boolean signUp() {
		final String insertSignUp = "INSERT INTO user(userid, password) VALUES (?, ?)";
		final String insertUserIdToRecord = "INSERT INTO record(userid) VALUES (?)";

		try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(insertSignUp);
				PreparedStatement preparedStatement2 = connection.prepareStatement(insertUserIdToRecord);) {
			System.out.print("아이디를 입력하세요 : ");
			userId = sc.next();

			System.out.print("비밀번호를 입력하세요(숫자로 입력): ");
			password = sc.nextInt();

			System.out.print("비밀번호를 한 번 더 입력해주세요 : "); // 검증을 위한 비밀번호 한 번 더 입력
			validPassword = sc.nextInt(); // 검증을 위한 비밀번호

			if (password == validPassword) {
				// insertSignup 쿼리 VALUES에 set
				preparedStatement1.setString(1, userId);
				preparedStatement1.setInt(2, password);
				// user table insert 실행
				preparedStatement1.executeUpdate();

				// insertUserIdToRecord 쿼리 VALUES에 set
				preparedStatement2.setString(1, userId);
				// record table insert 실행
				preparedStatement2.executeUpdate();

				System.out.println(userId + "(으)로 회원가입 완료!");

				return true;

			} else {
				System.out.println("비밀번호가 올바르지 않습니다.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean signIn(String userId, int password) {
		final String selectSignIn = "SELECT password From user WHERE userId = ?";

		try(Connection connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSignIn);) {

			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getInt(1) == password) {
					System.out.println("로그인 되었습니다!");
					return true; // 로그인 성공
				} else {
					System.out.println("로그인 실패...");
					return false; // 비밀번호 불일치
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			try {
//				resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return false;
	}

public void addRecord(String userId, String record) {
	
		final String insertRank = "UPDATE record AS r SET r.record = ? WHERE r.userid = ? ";

		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(insertRank);				
			) {
			preparedStatement.setString(1, record);
			preparedStatement.setString(2, userId);
			preparedStatement.executeUpdate();
			} catch (SQLException e) {
				
			e.printStackTrace();
		} 
	}

}
