
public class YearAndMonth {
	// private int m_Month;
	private int[] firstNumber = new int[12]; // 각 달의 첫 날에 대한 패널번호를 저장할 변수
	private int[] endNumber = new int[12]; // 각 달의 마지막 날에 대한 패널번호를 저장할 변수
	private int[][] remainDay = new int[12][31]; // 첫날 패널번호 ~ 마지막 날 패널번호 범위에 속하지 않은 패널번호 저장할 변수

	public YearAndMonth() // firstNumber에 각 달의 첫날 패널 번호 저장
	{ // endNumber는 각 달의 일 수만 저장
		// m_Month = (m >= 1 && m <= 12) ? m : 0; // m_Month에 0이하나 13이상의 값이 들어오면 0으로 초기화

		firstNumber[0] = 1; // 1월 시작 패널 번호
		firstNumber[1] = 4;
		firstNumber[2] = 4;
		firstNumber[3] = 0;
		firstNumber[4] = 2;
		firstNumber[5] = 5;
		firstNumber[6] = 0;
		firstNumber[7] = 3;
		firstNumber[8] = 6;
		firstNumber[9] = 2;
		firstNumber[10] = 4;
		firstNumber[11] = 6; // 12월 시작 패널 번호

		endNumber[0] = 31; // 1월 시작 패널 번호 + 1월에서 쓰는 패널 갯수 - 1
		endNumber[1] = 31;
		endNumber[2] = 34;
		endNumber[3] = 29;
		endNumber[4] = 32;
		endNumber[5] = 34;
		endNumber[6] = 30;
		endNumber[7] = 33;
		endNumber[8] = 35;
		endNumber[9] = 32;
		endNumber[10] = 33;
		endNumber[11] = 36;

	}

	public int firstDay(int m) // 해당 달의 첫 패널 번호를 넘겨 줌
	{
		return firstNumber[m];
	}

	public int endDay(int m) // 해당 달의 끝 패널 번호를 넘겨 줌
	{
		return endNumber[m];
	}

	public void remainDay(int m) // firstNumber[m] ~ endNumber[m]에 속하지 않으면 1로 저장
	{ 							 // 1로 저장 된 값은 메인에서 버튼이 아닌 회색 패널로 띄움
		int i;

		for (i = 0; i < 42; i++) {
			if (i < firstNumber[m])
				remainDay[m][i] = 0;
			else if (i > endNumber[m])
				remainDay[m][i] = 0;
			else
				remainDay[m][i] = 1;
		}
	}

}