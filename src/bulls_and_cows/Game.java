package bulls_and_cows;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import bulls_and_cows.dao.DAO;

public class Game {
	
	private final DAO dao;
	
	public Game(DAO dao) {
		this.dao = dao;
	}

	public void playGame(User user) {
		Scanner scanner = new Scanner(System.in);

		String gameNumber = "";

		Set<Integer> set = new HashSet<>(); //중복방지를 위한 set

		while(true){
			int number = (int)(Math.random()*10);
			if(!set.contains(number)) {
				gameNumber+=Integer.toString(number);
			}
			set.add(number);
			if(set.size()==4){
		        break;
		    }
		} 
		        
		 int chance = 1 ;
		 	//System.out.println(gameNumber);
		// 기회는 열 번 
		// S : 같은 자리에 수까지 같은 경우 
		// B : 숫자가 같은 경우
		// O : 하나도 같지 않은 경우

		//System.out.println(gameNumber);

		while(chance <= 10){

			int strike = 0;
			int ball = 0;
			int out = 0;

			if(chance == 10){
			    System.out.print("마지막 도전입니다! 4자리 숫자를 입력해주세요 :");
			}else{
			    System.out.print(chance+ "번째 도전 ! 4자리 숫자를 입력해주세요 : ");
			}

			String answer = scanner.nextLine();

			for(int i=0;i<4;i++){
			    if(gameNumber.charAt(i)==answer.charAt(i)){
			        strike+=1;
			    } else{
			         	if(answer.indexOf(gameNumber.charAt(i))!=-1){
			         		ball+=1;
			         	}else{
			         		out+=1;
			         	}
		        }
		    }
			
		    if(strike==4){ //정답을 맞힌다면
		        System.out.println("정답입니다! 게임을 종료합니다!");
		        user.setRecord(Integer.toString(chance)); //사용한 기회를 기록에 저장
		        dao.addRecord(user.getUserId(), Integer.toString(chance)); //데이터베이스에 기록 저장
		        break;
		    }else{
		        System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);
		    }

		    if(chance == 10){
		        System.out.println("아쉬워요 ㅠ.ㅠ 정답은 "+gameNumber+" 입니다!");
		    }

		    chance+=1;
		}
		
	}
}
