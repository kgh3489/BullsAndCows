package bulls_and_cows;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import bulls_and_cows.dao.DAO;
import bulls_and_cows.utils.DBUtils;

public class App {
	
	public static void main(String[] args) throws Exception {
		//DBUtils.dropAndCreateTable(); //테이블 없을 시 주석 해제 후 사용
		DAO dao = new DAO();
		Game game = new Game(dao);
		User user = new User();
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("==================숫자 야구 게임==================");
			System.out.println("무엇을 할까요?..... [ 1.회원가입  2.로그인  3.게임시작 ]");
			System.out.print("번호 선택 : ");
			int selectedNum = sc.nextInt();

			//1번 입력 시 회원가입
			if (selectedNum == 1) {
				dao.signUp();
			//2번 입력 시 로그인
			} else if (selectedNum == 2) {
				System.out.print("아이디를 입력하세요 : ");
				String userId = sc.next();
				
				user.setUserId(userId);
				
				System.out.print("비밀번호를 입력하세요(숫자로 입력) : ");
				int password = sc.nextInt();
				
				if (dao.signIn(userId, password) == true) { //ID, PW가 일치한다면 게임시작
					user.setPassword(password);
					break;
				}
				else
					continue;
			} 
			
			//3번 입력 시 로그인게임시작
			else if (selectedNum == 3) break;
			
			//다른 번호 입력시 초기화면
			else System.out.println("잘못된 번호입니다.");	
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("-------------------<<게임시작>>-------------------");
		System.out.println("------------------------------------------------");
		game.playGame(user);
	}
}

