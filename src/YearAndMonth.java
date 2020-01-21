
public class YearAndMonth {
	// private int m_Month;
	private int[] firstNumber = new int[12]; // �� ���� ù ���� ���� �гι�ȣ�� ������ ����
	private int[] endNumber = new int[12]; // �� ���� ������ ���� ���� �гι�ȣ�� ������ ����
	private int[][] remainDay = new int[12][31]; // ù�� �гι�ȣ ~ ������ �� �гι�ȣ ������ ������ ���� �гι�ȣ ������ ����

	public YearAndMonth() // firstNumber�� �� ���� ù�� �г� ��ȣ ����
	{ // endNumber�� �� ���� �� ���� ����
		// m_Month = (m >= 1 && m <= 12) ? m : 0; // m_Month�� 0���ϳ� 13�̻��� ���� ������ 0���� �ʱ�ȭ

		firstNumber[0] = 1; // 1�� ���� �г� ��ȣ
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
		firstNumber[11] = 6; // 12�� ���� �г� ��ȣ

		endNumber[0] = 31; // 1�� ���� �г� ��ȣ + 1������ ���� �г� ���� - 1
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

	public int firstDay(int m) // �ش� ���� ù �г� ��ȣ�� �Ѱ� ��
	{
		return firstNumber[m];
	}

	public int endDay(int m) // �ش� ���� �� �г� ��ȣ�� �Ѱ� ��
	{
		return endNumber[m];
	}

	public void remainDay(int m) // firstNumber[m] ~ endNumber[m]�� ������ ������ 1�� ����
	{ 							 // 1�� ���� �� ���� ���ο��� ��ư�� �ƴ� ȸ�� �гη� ���
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