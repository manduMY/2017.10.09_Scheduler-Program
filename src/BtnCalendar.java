import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BtnCalendar extends JPanel {
   private int monthValue; // 현재 달이 몇 월인지를 저장

   private JPanel primary, title; // 달력과 달력 위에 몇 월인지를 나타내는 패널
   private JPanel[][] dayPanel; // 열 두개월의 패널들을 각 개월의 해당 일 수 만큼 생성하기 위해 2차원으로 선언

   private JButton btnPrevious, btnAfter; // 이전 달과 다음 달로 넘어가기 위한 버튼
   private JButton[][] dayBtn; // 열 두개월의 패널들을 각 개월의 날들 마다 각각 버튼을 add하기 위해 버튼을 2차원으로 선언
   private BtnAction action; // 달력 부분에서 이전 달, 다음 달로 넘어가거나 해당 날의 버튼의 기능을 정의하는 BtnAction클래스의 객체 변수 선언

   private JLabel titleLabel; // title패널에 몇 월인지 보여주기 위한 라벨
   private JLabel[] sevenDay; // primary패널에 요일들을 나타내기 위한 라벨 배열 선언

   private String[] dayName; // 일, 월, 화, 수, 목, 금, 토를 문자열로 선언
   public int saveMonth, saveDay; // 해당되는 dayBtn을 클릭했을 때 월과 일을 get함수를 통해 저장하고, 삽입 수정 삭제 이외에도 여러 기능에 저장된 날짜가 필요할 때
   // 사용할 변수

   private JButton btnInsert, btnDelete, btnUpdate, btnClose, btnToDay;// 삽입, 삭제, 수정, 종료, today날짜 버튼을 선언
   private JPanel titleBar, scContent; // BtnCalendar에서 선택한 날짜를 불러오기 위한 공간과 스케줄 입력받는 공간을 구분하기 위해 2가지 패널을 만듬
   private JLabel Num[] = new JLabel[10]; // 10가지의 스케줄 번호를 선언
   private Checkbox cBox[] = new Checkbox[10]; // 1~10번까지의 스케줄 번호에 대한 체크박스 선언
   private JTextField txtInput; // 스케줄을 입력하기 위해 텍스트필드 선언
   // private JTextArea textArea; // 스케줄을 입력받고 출력해주기 위해 선언//시간이 되면 저장까지 하고싶음
   private int height = 0; // 체크박스와 스케줄번호의 높이를 일정하게 띄어주기 위해 선언
   private int scNum = 1; // 스케줄 번호를 나타내기 위한 변수
   private AcListener ac; // 체크박스, 입력, 수정, 삭제, 프로그램을 종료하는 기능을 정의하는 ActionListener클래스의 객체 변수 선언
   private JLabel titleDay; // 날짜의 버튼을 클릭하면 그 날이 몇 일인지를 나타내어 주는 라벨
   private JLabel titleLine; // 스케줄 부분에서 날짜와 스케줄 내용들을 구분하기 위한 라벨
   private JLabel dayScd[] = new JLabel[10]; // 오늘 10개 스케줄

   private ManageScd Mscd; // 저장된 문자열을 반환받거나, 삭제, 수정을 위해 ManageScd클래스의 객체변수 선언
   private YearAndMonth M; // 각 월의 날짜를 계산하기 위해 YearAndMonth클래스의 객체변수 선언

   private JLabel dayLabel[][]; // 달력 패널에서 각 날짜에 스케줄을 세개씩 add하기 위한 라벨
   private int day = 1; // 스케줄 번호를 나타내는 정수

   // private CheckAction checkL;

   public BtnCalendar() {
      setPreferredSize(new Dimension(1340, 700));
      setLayout(null);

      ac = new AcListener(); // 체크박스, 삽입, 수정, 삭제, 프로그램 종료, today버튼을 사용하기 위해 생성
      action = new BtnAction(); // 이전 달, 다음 달로 넘어가거나 어떤 날짜에 대한 정보를 얻기위해 생성
      Mscd = new ManageScd(); // 문자열을 삽입하거나, 수정, 삭제를 하기 위해 생성
      M = new YearAndMonth(); // 날짜를 계산 하기위해 생성

      // checkL = new CheckAction();

      saveMonth = 0; // 오늘 날짜를 1월 1일로 초기화
      saveDay = 1;

      primary = new JPanel(); // 달력을 넣기 위한 패널
      primary.setBounds(0, 50, 920, 650);
      primary.setBackground(Color.gray);
      primary.setLayout(null);
      add(primary);

      monthValue = 1; // 처음에 보여주는 월을 1월로 초기화

      dayPanel = new JPanel[12][]; // 각 달의 날짜들을 패널로 넣기 위해 2차원 배열 생성
      sevenDay = new JLabel[7]; // 요일을 라벨로 넣기위해 배열 생성
      dayBtn = new JButton[12][]; // 각 달의 날짜들에 버튼들을 넣기위해 버튼을 2차원 배열 생성
      dayLabel = new JLabel[42][3]; // 각 달의 날짜들에 최대 세 개까지 스케줄을 사용자에게 보여주기 위해 2차원 배열 생성

      dayName = new String[7]; // 일, 월, 화, 수, 목, 금, 토를 각 배열 값에 초기화한다.
      dayName[0] = "Sun";
      dayName[1] = "Mon";
      dayName[2] = "Tue";
      dayName[3] = "Wen";
      dayName[4] = "Thu";
      dayName[5] = "Fri";
      dayName[6] = "Sat";

      for (int i = 0; i < 7; i++) {
         sevenDay[i] = new JLabel();
         sevenDay[i].setText(dayName[i]); // 인덱스 값에 따라 일, 월, 화, 수, 목, 금, 토를 순차적으로 각 라벨에 추가함
         sevenDay[i].setBounds((35 + 130 * i), 10, 66, 20); // dayName으로 초기화한 내용들을 각각 요일의 패널 위치와 x값이 동일하게 위치 고정
         sevenDay[i].setForeground(Color.white);
         sevenDay[i].setFont(new Font("Consolas", Font.BOLD, 20));
         sevenDay[i].setHorizontalAlignment(SwingConstants.CENTER);
         sevenDay[i].setVerticalAlignment(SwingConstants.CENTER);
         primary.add(sevenDay[i]);
      }

      int seven; // seven은 각 개월의 날짜들이 7일이 지나면 줄바꿈을 하기 위해 사용할 임시 정수변수

      for (int m = 0; m < 12; m++) { // m은 1월 ~12월
         dayPanel[m] = new JPanel[42]; // 각 개월의 패널을 42개로 객체 생성
         dayBtn[m] = new JButton[42]; // 각 개월의 버튼도 42개로 객체 생성

         seven = 0; // 다음 개월로 넘어 갈때마다 줄바꿈 변수를 0으로 초기화
         for (int i = 0; i < 42; i++) {

            if (i >= M.firstDay(m) && i <= M.endDay(m)) { // 42개 패널 중에서 각 달의 첫 패널 부터 끝 패널 까지만 이 코드를 수행 한다.
               dayPanel[m][i] = new JPanel(); // 패널 42개 중 시작 패널부터 끝 패널일때만 일에 대한 패널을 생성
               dayPanel[m][i].setBounds((10 + 130 * i) % 910, 40 + seven, 120, 92); // x값을 매번 (패널 x크기+10)만큼 늘리고
               // (패널 x크기+10)*7을 넘으면 다시
               // 10부터 시작한다.
               dayPanel[m][i].setLayout(null); // y값은 계속 똑같이 주다가 패널이 7개 등장했으면 다음 패널부터 y값을 증가시켜서 고정한다.

               if ((i + 1) % 7 == 0) // 패널이 7개 등장하면 (패널 y크기 +10) 만큼 증가시킨다.
                  seven += 102;
               switch (i % 7) {
               case 0:
                  dayPanel[m][i].setBackground(new Color(0xFF9595)); // 일요일 부분을 빨간 색으로 만든다.
                  break;
               case 6:
                  dayPanel[m][i].setBackground(new Color(0x79ABFF)); // 토요일 부분을 파란 색으로 만든다.
                  break;
               default:
                  dayPanel[m][i].setBackground(Color.white); // 나머지 평일들은 하얀 색으로 만든다.
                  break;
               }
               if (m != 0) // 처음에 보여주는 1월 이외의 패널들은 감춘다.
               {
                  dayPanel[m][i].setVisible(false);
               }

               dayBtn[m][i] = new JButton((m + 1) + " / " + (i - M.firstDay(m) + 1) + ""); // 버튼에 월, 일을 나타낸다.
               dayBtn[m][i].setBounds(15, 10, 90, 20);
               dayBtn[m][i].addActionListener(action); // 이 버튼들이 입력되면 스케줄 날짜가 해당 날짜로 바뀌고 날짜 밑에 스케줄들이 나타난다.

               dayPanel[m][i].add(dayBtn[m][i]);
               primary.add(dayPanel[m][i]);

            }

            else { // 42개 패널 중에 첫 패널보다 이전이거나 끝 패널보다 이후면 회색으로 패널을 띄운다.
               dayPanel[m][i] = new JPanel();
               dayPanel[m][i].setBounds((10 + 130 * i) % 910, 40 + seven, 120, 92);

               if ((i + 1) % 7 == 0)
                  seven += 102;

               dayPanel[m][i].setBackground(new Color(0xC9C9C9));
               primary.add(dayPanel[m][i]);
            }
         }
      }
      for (int i = 0; i < 42; i++) // 처음에 사용자에게 보여주는 1월에 있는 날들의 패널들 안에 라벨을 세개씩 띄우기 위함
         for (int j = 0; j < 3; j++)
            dayLabel[i][j] = new JLabel();
      for (int i = 0; i < 42; i++) {
         if (i >= M.firstDay(0) && i <= M.endDay(0)) {
            for (int j = 0; j < 3; j++) {
               dayLabel[i][j].setText(Mscd.getScd(1, day, j + 1)); // 1월의 해당하는 날에 스케줄을 null이 아닌 곳에 추가시켜준다.
               dayLabel[i][j].setFont(new Font("바탕체", Font.BOLD, 10));
               dayLabel[i][j].setBounds(5, 10 + 20 * (j + 1), 150, 20);
               dayPanel[0][i].add(dayLabel[i][j]);
            }
            day++;
         }
      }

      title = new JPanel(); // 이전 달, 다음 달로 넘어가는 버튼과 지금 보고 있는 달이 몇 월인지를 보여주기 위한 패널
      title.setBounds(0, 0, 920, 50);
      title.setBackground(Color.black);
      title.setLayout(null);
      add(title);

      btnPrevious = new JButton("◀"); // 이전 달로 넘어가는 버튼
      btnPrevious.setBounds(10, 10, 50, 30);
      btnPrevious.addActionListener(action);
      title.add(btnPrevious);

      btnAfter = new JButton("▶"); // 다음 달로 넘어가는 버튼
      btnAfter.setBounds(860, 10, 50, 30);
      btnAfter.addActionListener(action);
      title.add(btnAfter);

      titleLabel = new JLabel("2018 / 0" + monthValue); // 지금 보고 있는 달이 몇 월인지 보여주는 라벨
      titleLabel.setBounds(0, 5, 910, 50);
      titleLabel.setFont(new Font("Consolas", Font.BOLD, 40));
      titleLabel.setForeground(Color.white);
      titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
      titleLabel.setVerticalAlignment(SwingConstants.CENTER);
      title.add(titleLabel);

      // 왼쪽 패널

      titleBar = new JPanel(); // Today버튼을 add하기 위한 패널
      titleBar.setBounds(920, 0, 420, 50);
      titleBar.setBackground(Color.gray);
      titleBar.setLayout(null);
      add(titleBar);

      /* 스케줄 입력받는 공간을 위해 패널을 만들고 크기와 색을 지정 */
      scContent = new JPanel(); // 스케줄 내용과 삽입, 수정, 삭제, 닫기버튼 그리고 텍스트 필드를 넣기 위한 패널
      scContent.setBounds(920, 50, 420, 650);
      scContent.setBackground(Color.white);
      scContent.setLayout(null);
      add(scContent);

      /* 스케줄 번호 생성 및 스케줄 번호와 크기 폰트 위치 지정 */
      height = 0; // 스케줄 박스의 추가 높이는 0으로 초기화
      for (int i = 0; i < 10; i++) { //
         Num[i] = new JLabel();
         Num[i].setText(scNum + ".");
         Num[i].setBounds(20, 80 + 45 * height, 40, 20);
         Num[i].setFont(new Font("consolas", Font.BOLD, 20));
         scContent.add(Num[i]);
         scNum++;// 스케줄번호 1~10까지 출력하기 위해 증감연산자 사용
         height++;// 스케줄 간격 일정하게 벌리기 위해 증감연산자 사용
      }
      /* 입력버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동 */
      btnInsert = new JButton("Insert"); // 입력버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동
      btnInsert.setBounds(300, 530, 90, 30);
      btnInsert.setFont(new Font("Consolas", Font.BOLD, 15));
      btnInsert.addActionListener(ac);
      scContent.add(btnInsert);

      /* 삭제버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동 */
      btnDelete = new JButton("Delete"); // 삭제버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동
      btnDelete.setBounds(50, 580, 90, 50);
      btnDelete.setFont(new Font("Consolas", Font.BOLD, 15));
      btnDelete.addActionListener(ac);
      scContent.add(btnDelete);

      /* 수정버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동 */
      btnUpdate = new JButton("Update"); // 수정버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동
      btnUpdate.setBounds(160, 580, 90, 50);
      btnUpdate.setFont(new Font("Consolas", Font.BOLD, 15));
      btnUpdate.addActionListener(ac);
      scContent.add(btnUpdate);

      /* 닫기버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동 */
      btnClose = new JButton("Close"); // 닫기버튼의 크기 위치 폰트와 버튼 눌럿을 때 액션리스너 작동
      btnClose.setBounds(270, 580, 90, 50);
      btnClose.setFont(new Font("Consolas", Font.BOLD, 15));
      btnClose.addActionListener(ac);
      scContent.add(btnClose);

      btnToDay = new JButton("Today"); // Today버튼 눌럿을때 오늘 날짜의 월 일이 titleBar에 표시되고 스케줄도 불러오기위해 만듬
      btnToDay.setBounds(320, 10, 90, 30);
      btnToDay.setFont(new Font("Consolas", Font.BOLD, 15));
      btnToDay.setMargin(null);
      btnToDay.addActionListener(ac);
      titleBar.add(btnToDay);

      txtInput = new JTextField("Input the Schedule..", 40); // 텍스트필드로 스케줄을 입력받고 스케줄 쪽으로 저장해 줌
      // textArea = new JTextArea(5, 40);
      // textArea.setEditable(false);// 텍스트가 입력된게 중간에 수정못하도록 설정
      txtInput.setBounds(20, 530, 270, 30);
      // textArea.setBounds(40, 0, 200, 200);
      scContent.add(txtInput);
      // scContent.add(textArea);
      txtInput.addActionListener(ac);

      /* 체크박스 생성함 */
      height = 0;
      for (int i = 0; i < 10; i++) {
         cBox[i] = new Checkbox();
         cBox[i].setBounds(0, 80 + 45 * height, 20, 20);
         scContent.add(cBox[i]);
         cBox[i].addItemListener(ac);
         height++;
      }

      titleDay = new JLabel((saveMonth + 1) + ". " + (saveDay) + ". "); // 스케줄 부분에 클릭한 날짜를 사용자에게 보여줌
      titleDay.setBounds(130, 10, 200, 50);
      titleDay.setFont(new Font("Consolas", Font.BOLD, 40));
      titleDay.setVerticalAlignment(SwingConstants.CENTER);
      titleDay.setHorizontalAlignment(SwingConstants.CENTER);
      scContent.add(titleDay);

      titleLine = new JLabel("___________________________________________________"); // 스케줄 부분에 클릭한 날짜와 스케줄 내용을 구분하는 선
      titleLine.setBounds(0, 30, 600, 50);
      titleLine.setFont(new Font("Consolas", Font.BOLD, 20));
      titleLine.setVerticalAlignment(SwingConstants.CENTER);// 값을 수직선 기준으로 중앙에 맞춤
      // titleLine.setHorizontalAlignment(SwingConstants.CENTER);// 값을 수평선 기준으로 중앙에 맞춤
      scContent.add(titleLine);

      height = 0;
      for (int i = 0; i < 10; i++) {
         dayScd[i] = new JLabel(""); // 스케줄 내용을 공백으로 10개 생성
         dayScd[i].setBounds(60, 80 + 45 * height, 400, 20);
         dayScd[i].setFont(new Font("consolas", Font.BOLD, 20));
         scContent.add(dayScd[i]);
         height++;
      }

      // today
      // titleDay.setText(Mscd.getTodayDate());
      for (int i = 0; i < 10; i++) {
         dayScd[i].setText("" + Mscd.getScd(saveMonth + 1, saveDay, i + 1)); // 오늘날짜(1월 1일)에 저장된 스케줄들을 순서대로 스케줄 쪽에서
                                                            // 띄워 줌
         scContent.add(dayScd[i]);
         height++;
      }

      init();
   }

   public void init() { // init() 함수는 2018년 공휴일의 패널 색깔을 빨간 색으로 바꿔주는 역할을 하는 함수이다.

      Color c = new Color(0xFF9595);
      dayPanel[0][1 + M.firstDay(0) - 1].setBackground(c);

      dayPanel[1][15 + M.firstDay(1) - 1].setBackground(c);
      dayPanel[1][16 + M.firstDay(1) - 1].setBackground(c);
      dayPanel[1][17 + M.firstDay(1) - 1].setBackground(c);

      dayPanel[2][1 + M.firstDay(2) - 1].setBackground(c);

      dayPanel[4][5 + M.firstDay(4) - 1].setBackground(c);
      dayPanel[4][7 + M.firstDay(4) - 1].setBackground(c);
      dayPanel[4][22 + M.firstDay(4) - 1].setBackground(c);

      dayPanel[5][6 + M.firstDay(5) - 1].setBackground(c);
      dayPanel[5][13 + M.firstDay(5) - 1].setBackground(c);

      dayPanel[7][15 + M.firstDay(7) - 1].setBackground(c);

      dayPanel[8][24 + M.firstDay(8) - 1].setBackground(c);
      dayPanel[8][25 + M.firstDay(8) - 1].setBackground(c);
      dayPanel[8][26 + M.firstDay(8) - 1].setBackground(c);

      dayPanel[9][3 + M.firstDay(9) - 1].setBackground(c);
      dayPanel[9][9 + M.firstDay(9) - 1].setBackground(c);

      dayPanel[11][25 + M.firstDay(11) - 1].setBackground(c);
   }

   public String toString() { // 2018년 1월에서 이전 달로 넘어가거나, 2018년 12월에서 다음 달로 넘어갈 경우 사용자에게 보여줄 문자열을 저장
      String str = "올바르지 않은 접근입니다.";
      return str;
   }

   public int getSaveMonth() { // 클릭한 날짜의 월을 저장
      return saveMonth;
   }

   public int getSaveDay() { // 클릭한 날짜의 일을 저장
      return saveDay;
   }

   public void setSaveMonth(int m) { // 클릭한 날짜로 월 값을 수정
      saveMonth = m;
   }

   public void setSaveDay(int d) { // 클릭한 날짜로 일 값을 수정
      saveDay = d;
   }

   private class AcListener implements ActionListener, ItemListener { // 체크박스와 삽입, 수정, 삭제, 오늘 날짜, 프로그램 종료 버튼에 대한 기능
      public void itemStateChanged(ItemEvent event) {
         // TODO Auto-generated method stub
         for (int i = 0; i < 10; i++) {
            if (cBox[i].getState() == true) { // 체크 박스 하나가 체크가 되면
               for (int j = 0; j < 10; j++) {
                  if (j != i)
                     cBox[j].setState(false); // 나머지 체크 박스는 체크 할 수 없음
               }
            }
         }

         // 공휴일과 현재 까지 공개된 학사일정의 스케줄을 수정하거나 삭제하는 것을 방지하기 위하여 해당 날짜들의 해당 스케줄을 체크 하지 못하도록
         // 설정한다.
         if (saveMonth == 0 && saveDay == 1 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 1 && saveDay == 16 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 2 && saveDay == 1 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 4 && saveDay == 5 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
         if (saveMonth == 4 && saveDay == 7 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
         if (saveMonth == 4 && saveDay == 22 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 5 && saveDay == 6 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
         if (saveMonth == 5 && saveDay == 13 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 7 && saveDay == 15 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 8 && saveDay == 24 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
         if (saveMonth == 8 && saveDay == 26 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 9 && saveDay == 3 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
         if (saveMonth == 9 && saveDay == 9 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);

         if (saveMonth == 11 && saveDay == 25 + M.firstDay(saveMonth) - 1)
            cBox[0].setState(false);
      }

      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();
         if (obj == btnInsert || obj == txtInput) { // 삽입 버튼을 누르거나 텍스트 필드에서 엔터를 누르면
            int result;

            result = JOptionPane.showConfirmDialog(scContent, "Will you Input?");
            if (result == JOptionPane.YES_OPTION) {
               /* 입력창에서 yes를 눌렀을 때 텍스트값을 출력해주고 스케줄 10개 입력하면 입력않되도록 설정(아직 구현다 못함) */

               boolean inputDataFlag; // 텍스트필드의 문자열이 공백인지 아닌지를 검사할 boolean 변수, 공백이 아니면 문자열을 저장함
               inputDataFlag = Mscd.inputData(saveMonth + 1, saveDay, txtInput.getText());
               txtInput.setText("");// 입력 후 텍스트필드 내용 삭제

               for (int j = 0; j < 10; j++) {
                  dayScd[j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1)); // inputDataFlag에 값을 저장하며
                                                                     // 저장된 문자열을 저장
                  scContent.add(dayScd[j]); // 저장된 문자열을 스케줄 쪽에서 출력
                  height++; // 다음 문자열에 대해서 y값을 증가시켜 줄 변수
               }
               for (int i = 0; i < 42; i++) {
                  if (i >= M.firstDay(saveMonth) && i <= M.endDay(saveMonth)) { // 방금 스케줄에 출력한 문자열을 달력의 해당 패널에도
                                                                  // 저장하여 출력
                     for (int j = 0; j < 3; j++) {
                        dayLabel[i][j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1));
                        dayPanel[saveMonth][saveDay].add(dayLabel[i][j]);
                     }
                  } // if
               } // for

               if (inputDataFlag) // 문자열이 저장될 경우, 성공이라는 메세지 박스를 띄움
                  JOptionPane.showMessageDialog(scContent, "Success");
               else
                  JOptionPane.showMessageDialog(scContent, "Can't save the data");

            }
            /* 아니오 버튼 누르면 아무일도 일어나지 않음 */
            else if (result == JOptionPane.NO_OPTION) {

            }
            /* 취소버튼 눌러도 아무일도 일어나지 않음 */
            else {

            }

         } // if

         else if (obj == btnDelete) {
            int result;
            boolean deleteDataFlag = false;
            result = JOptionPane.showConfirmDialog(scContent, "Will you Delete?");
            if (result == JOptionPane.YES_OPTION) {
               for (int i = 0; i < 10; i++) {
                  if (cBox[i].getState() == true) { // 10개의 체크박스 중 체크가 되어 있는 체크박스를 발견하면
                     deleteDataFlag = Mscd.deleteData(saveMonth + 1, saveDay, i + 1); // 체크한 문자열이 공백이 아닐 경우
                                                                        // null값으로 초기화하고 true
                                                                        // 반환, 공백이면 false 반환
                  } // 그 함수 안에서 삭제된 스케줄을 위로 올려주는 함수도 호출함
               }

               if (saveMonth == 0 && saveDay == 1)
                  cBox[0].setState(false);
               for (int j = 0; j < 10; j++) { // 삭제한 문자열은 없어지고 위치가 변경된 문자열의 위치가 바뀐 문자열들을 사용자에게 보여줌
                  dayScd[j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1));
                  scContent.add(dayScd[j]);
                  height++;
               }
               for (int i = 0; i < 42; i++) {
                  if (i >= M.firstDay(saveMonth) && i <= M.endDay(saveMonth)) {
                     for (int j = 0; j < 3; j++) { // 달력의 해당 패널에서도 문자 띄워서 사용자에게 보여줌
                        dayLabel[i][j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1));
                        dayPanel[saveMonth][saveDay].add(dayLabel[i][j]);
                     }
                  } // if
               } // for
               if (deleteDataFlag) // 체크한 문자열이 공백이 아닐 경우 삭제 성공 메세지박스를 사용자에게 보여 줌
                  JOptionPane.showMessageDialog(scContent, "Success");
               else
                  JOptionPane.showMessageDialog(scContent, "can't delete the data");
            } else if (result == JOptionPane.NO_OPTION) {

            } else {

            }
         }
         /* 수정버튼 눌렀을 때 else if문 돌아감 그리고 "수정하시겠습니까?"라는 창이 나오게함 */
         else if (obj == btnUpdate) {
            // int updateResult;
            String result;

            for (int i = 0; i < 10; i++) {
               if (cBox[i].getState() == true) { // 10개의 체크박스 중 체크 된 체크박스를 발견하면
                  result = JOptionPane.showInputDialog("input the update schedule", // 수정할 수 있는 메세지 박스를 띄운다.
                        Mscd.getScd(saveMonth + 1, saveDay, i + 1)); // 메세지 박스에는 텍스트 필드로 수정할 수 있는 기능이 있다.

                  if (saveMonth == 0 && saveDay == 1)
                     cBox[0].setState(false);
                  if ((result != null) && (result.length() > 0)) {
                     boolean updateDataFlag;
                     updateDataFlag = Mscd.updateData(saveMonth + 1, saveDay, i + 1, result); // 체크 박스를 검사하여 공백이면
                                                                              // false, 문자열이
                                                                              // 있으면 true를
                                                                              // 저장한다.
                     txtInput.setText("");// 입력후 텍스트필드 내용 삭제
                     for (int j = 0; j < 10; j++) {
                        dayScd[j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1)); // 메세지 박스에서 수정한 내용을
                                                                           // 스케줄 부분으로 사용자에게
                                                                           // 보여준다
                        scContent.add(dayScd[j]);
                        height++;
                     }
                     for (int k = 0; k < 42; k++) {
                        if (k >= M.firstDay(saveMonth) && k <= M.endDay(saveMonth)) {
                           for (int j = 0; j < 3; j++) {
                              dayLabel[k][j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1)); // 메세지
                                                                                       // 박스에서
                                                                                       // 보여준다.
                              dayPanel[saveMonth][saveDay].add(dayLabel[k][j]);
                           }
                        } // if
                     } // for

                     if (updateDataFlag)
                        JOptionPane.showMessageDialog(scContent, "Success"); // 문자열이 수정됬으면 메세지 박스에 성공됬다는 메세지박스를
                                                                  // 사용자에게 보여준다.
                     else
                        JOptionPane.showMessageDialog(scContent, "Can't update the data");
                  }
               }

            }
         } else if (obj == btnClose) { // 닫기 버튼
            int result;
            result = JOptionPane.showConfirmDialog(scContent, "Will you Exit?");
            /* 만약에 yes눌럿으면 시스템 종료 */
            if (result == JOptionPane.YES_OPTION) { // 프로그램 종료
               System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {

            } else {

            }

         }

         else if (obj == btnToDay) {
            // titleDay.setText(Mscd.getTodayDate());
            // 임의로 1월 1일로 지정

            switch (saveDay % 7) { // 오늘 날짜 버튼을 누를 경우 체크 되어있던 달력의 패널를 원래 색으로 바꿈
            case 0:
               dayPanel[saveMonth][saveDay].setBackground(new Color(0xFF9595));
               break;
            case 6:
               dayPanel[saveMonth][saveDay].setBackground(new Color(0x79ABFF));
               break;
            default:
               dayPanel[saveMonth][saveDay].setBackground(Color.WHITE);
               break;
            }

            saveMonth = 0; // 오늘 날짜(1월 1일)의 색은 노란색으로 바꿈
            saveDay = 1;
            dayPanel[saveMonth][saveDay].setBackground(new Color(0xFAF4C0));

            titleLabel.setText("2018 / 01"); // 오늘 날짜에 해당 되는 1월을 패널 상단에 띄워준다.
            monthValue = 1; // 오늘 날짜 1월을 저장한다.

            for (int m = 0; m < 12; m++) {
               if (monthValue - 1 != m) { // 1월 이외의 모든 월의 패널들을 보이지 않게 만든다.
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(false);
                  }
               }

               else if (monthValue - 1 == m) { // 1월 일 경우 1월의 패널을 사용자에게 보여준다.
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(true);
                     if (i >= M.firstDay(m) && i <= M.endDay(m)) {
                        for (int j = 0; j < 3; j++) { // 1월 달력 패널에 있는 내용들도 다시 패널에 추가하여 보여준다.
                           dayLabel[i][j].setText("" + Mscd.getScd(m + 1, i, j + 1));
                           dayPanel[m][i].add(dayLabel[i][j]);
                        }
                     } // if
                  } // for
               }
            }

            titleDay.setText((saveMonth + 1) + ". " + saveDay + ". "); // 스케줄 부분의 날짜를 오늘 날짜로 바꾼다.
            for (int i = 0; i < 10; i++) {
               dayScd[i].setText("" + Mscd.getScd(saveMonth + 1, saveDay, i + 1)); // 1월 1일에 저장된 만큼의 내용을 스케줄 부분으로
               // 사용자에게 보여준다.
               scContent.add(dayScd[i]);
               height++;
            }

         }

         for (int i = 0; i < 10; i++) // 수행 후 체크 박스의 체크를 전부 해제함
            cBox[i].setState(false);
      }

   }

   private class BtnAction implements ActionListener {
      public BtnCalendar c; // BtnCalendar의 객체는 2017년이나 2019년으로 넘어가지 못하게 할 때 문자열을 사용자에게 메세지 박스로 보여주기 위해 선언

      public void actionPerformed(ActionEvent event) {
         c = new BtnCalendar();

         Object obj = event.getSource();
         for (int i = 0; i < 10; i++) // 다음 달로 넘어가면 체크박스의 체크를 전부 해제함
            cBox[i].setState(false);

         if (obj == btnPrevious) { // 이전 달로 넘어가는 버튼
            monthValue--; // 이전 달로 넘어가기 때문에 월 값을 줄임
            if (monthValue <= 0) { // 1월에서 이전으로 넘어가면 2017년 12월이 되기 때문에 못넘어가도록 막음
               monthValue = 1;
               JOptionPane.showMessageDialog(primary, c.toString()); // 메세지박스로 BtnCanlendar에 있는 문자열을 사용자에게 보여줌
            }

            if (monthValue < 10) // 월이 한자리인 달과 두자리인 달(10, 11, 12)과 자릿 수를 맞추기 위함
               titleLabel.setText("2018 / 0" + monthValue);
            else
               titleLabel.setText("2018 / " + monthValue);

            for (int m = 0; m < 12; m++) { // for문으로 12월 달까지 검사하여 해당 달이 아닌 모든 달의 월패널을 보이지 않게 만듬
               if (monthValue - 1 != m) {
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(false);
                  }
               }

               else if (monthValue - 1 == m) { // 해당 달일 경우 월패널과 그 월패널에 스케줄을 최대 3개까지 추가하여 사용자에게 보여줌
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(true);
                     if (i >= M.firstDay(m) && i <= M.endDay(m)) {
                        for (int j = 0; j < 3; j++) {
                           dayLabel[i][j].setText("" + Mscd.getScd(m + 1, i, j + 1));
                           dayPanel[m][i].add(dayLabel[i][j]);
                        }
                     } // if
                  } // for
               }
            }
         }

         else if (obj == btnAfter) { // 다음 달로 넘어가는 버튼
            monthValue++; // 다음 달로 넘어가기 때문에 월값을 올림
            if (monthValue >= 13) { // 12월에서 다음달로 넘어가면 2019년이 되기 때문에 넘어가지 못 하도록 막음
               monthValue = 12;
               JOptionPane.showMessageDialog(primary, c.toString()); // 메세지박스로 BtnCalendar에 있는 문자열을 사용자에게 보여줌
            }

            if (monthValue < 10) // 월이 한자리인 달과 두자리인 달(10, 11, 12)과 자릿 수를 맞추기 위함
               titleLabel.setText("2018 / 0" + monthValue);
            else
               titleLabel.setText("2018 / " + monthValue);

            for (int m = 0; m < 12; m++) { // for문으로 12월 달까지 검사하여 해당 달이 아닌 모든 달의 월패널을 보이지 않게 만듬
               if (monthValue - 1 != m) {
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(false);
                  }
               }

               else if (monthValue - 1 == m) { // 해당 달일 경우 월패널과 그 월패널에 스케줄을 최대 3개까지 추가하여 사용자에게 보여줌
                  for (int i = 0; i < 42; i++) {
                     dayPanel[m][i].setVisible(true);
                     if (i >= M.firstDay(m) && i <= M.endDay(m)) {
                        for (int j = 0; j < 3; j++) {
                           dayLabel[i][j].setText("" + Mscd.getScd(m + 1, i, j + 1));
                           dayPanel[m][i].add(dayLabel[i][j]);
                        }
                     } // if
                  } // for
               }
            }

         }

         else {
            for (int i = 0; i < 42; i++) {
               if (obj == dayBtn[monthValue - 1][i]) { // 다른 날짜의 버튼을 클릭할 경우 방금 전까지 클릭 되어 있던 패널들의 색을 원래 색으로 되돌려 줌
                  switch (saveDay % 7) {
                  case 0:
                     dayPanel[saveMonth][saveDay].setBackground(new Color(0xFF9595));
                     break;
                  case 6:
                     dayPanel[saveMonth][saveDay].setBackground(new Color(0x79ABFF));
                     break;
                  default:
                     dayPanel[saveMonth][saveDay].setBackground(Color.WHITE);
                     break;
                  }
                  init(); // 공휴일인 경우 빨간 색으로 패널을 보여주는 init() 함수 호출

                  dayPanel[monthValue - 1][i].setBackground(new Color(0xFAF4C0)); // 클릭한 패널은 노란색으로 보여줌
                  setSaveMonth(monthValue - 1); // 클릭한 패널의 월 정보를 저장
                  setSaveDay(i); // 클릭한 패널의 일 정보를 저장
                  titleDay.setText((saveMonth + 1) + ". " + (i - M.firstDay(saveMonth) + 1) + ". "); // 달력 패널에서
                                                                                 // 클릭한 버튼의
                                                                                 // 날짜를 스케줄
                                                                                 // 부분의 상단
                                                                                 // 보여줌
                  for (int j = 0; j < 10; j++) { // 클릭한 부분의 스케줄 부분을 저장된 만큼 사용자에게 보여줌
                     dayScd[j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1));
                     scContent.add(dayScd[j]);
                     height++; // 스케줄을 하나 띄울 때마다 y값을 증가시키 위함
                  }

                  if (i >= M.firstDay(saveMonth) && i <= M.endDay(saveMonth)) {
                     for (int j = 0; j < 3; j++) {
                        dayLabel[i][j].setText("" + Mscd.getScd(saveMonth + 1, saveDay, j + 1));
                        dayPanel[saveMonth][saveDay].add(dayLabel[i][j]);
                     }
                  }

               } // if
            } // for
         }
      }
   }
}