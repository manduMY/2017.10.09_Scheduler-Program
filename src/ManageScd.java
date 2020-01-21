import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageScd {
   public final static int MAX = 1024; // 스케줄 패널에 입력될 스케줄의 최대 길이를 상수로 저장
   // private ArrayList<ScheduleSave> dataList;
   // private ScheduleSave data;
   private String Jan[][], Feb[][], Mar[][], Apr[][], May[][], Jun[][], // 각 월을 2차원 변수로 저장한다.
         Jul[][], Aug[][], Sep[][], Oct[][], Nov[][], Dec[][]; //
   private YearAndMonth m; // 각 월의 첫 번째 날과 마지막 날 정보를 얻기 위해 YearAndMonth 클래스의 객체를 선언
   private int number; // 각 월에서 0번 패널 부터 첫날 이전까지의 패널들을 걸러 내기 위해 사용할 변수

   public ManageScd() {
      // dataList = new ArrayList<ScheduleSave>();
      Jan = new String[42][10]; // 각 월의 해당 일을 패널번호로 받고 해당 일의 스케줄을 10개까지 순서대로 입력 받는다.
      Feb = new String[42][10];
      Mar = new String[42][10];
      Apr = new String[42][10];
      May = new String[42][10];
      Jun = new String[42][10];
      Jul = new String[42][10];
      Aug = new String[42][10];
      Sep = new String[42][10];
      Oct = new String[42][10];
      Nov = new String[42][10];
      Dec = new String[42][10];

      for (int i = 0; i < 42; i++) // 각 월, 일 , 스케줄들의 내용을 공백으로 초기화 한다.
         for (int j = 0; j < 10; j++)
            Jan[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Feb[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Mar[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Apr[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            May[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Jun[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Jul[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Aug[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Sep[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Oct[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Nov[i][j] = null;
      for (int i = 0; i < 42; i++)
         for (int j = 0; j < 10; j++)
            Dec[i][j] = null;

      m = new YearAndMonth(); // 각 월의 첫날 정보를 사용하기 위해 m을 객체 변수로 생성

      // 휴일에 대한 정보와 학교 스케줄(1,2월)에 대한 하드코딩
      number = m.firstDay(0) - 1; // 임시 변수에 1월의 첫날에 대한 패널 정보를 저장 (1을 빼는 이유는 첫날 이전 까지만 패널들을 걸러내기 위함)
      Jan[1 + number][0] = "New Year";
      Jan[29 + number][0] = "Semester off to school";
      Jan[29 + number][1] = "Semester return to school";
      Jan[30 + number][0] = "Semester off to school";
      Jan[30 + number][1] = "Semester return to school";
      Jan[31 + number][0] = "Semester off to school";
      Jan[31 + number][1] = "Semester return to school";

      number = m.firstDay(1) - 1; // 임시 변수에 2월의 첫날에 대한 패널 정보를 저장
      Feb[1 + number][0] = "Semester off to school";
      Feb[1 + number][1] = "Semester return to school";
      Feb[2 + number][0] = "Semester off to school";
      Feb[2 + number][1] = "Semester return to school";
      Feb[12 + number][0] = "Sign up for classes";
      Feb[13 + number][0] = "Sign up for classes";
      Feb[14 + number][0] = "Sign up for classes";
      Feb[16 + number][0] = "Korean New Year's Day";
      Feb[19 + number][0] = "Sign up for classes";
      Feb[22 + number][0] = "Pay tuition";
      Feb[23 + number][0] = "Pay tuition";
      Feb[23 + number][1] = "Graduation ceremony";
      Feb[26 + number][0] = "Pay tuition";
      Feb[26 + number][1] = "Entrance ceremony";
      Feb[27 + number][0] = "Pay tuition";
      Feb[28 + number][0] = "Pay tuition";

      number = m.firstDay(2) - 1; // 임시 변수에 3월의 첫날에 대한 패널 정보를 저장
      Mar[1 + number][0] = "Independence Movement Day";

      number = m.firstDay(4) - 1; // 임시 변수에 5월의 첫날에 대한 패널 정보를 저장
      May[5 + number][0] = "Children's Day";
      May[7 + number][0] = "05.05  Substitution holiday";
      May[22 + number][0] = "Buddha's Birthday";

      number = m.firstDay(5) - 1; // 임시 변수에 6월의 첫날에 대한 패널 정보를 저장
      Jun[6 + number][0] = "Memorial Day";
      Jun[13 + number][0] = "Local election day";

      number = m.firstDay(7) - 1; // 임시 변수에 8월의 첫날에 대한 패널 정보를 저장
      Aug[15 + number][0] = "National Liberation Day";

      number = m.firstDay(8) - 1; // 임시 변수에 9월의 첫날에 대한 패널 정보를 저장
      Sep[24 + number][0] = "Korean Thanksgiving Day";
      Sep[26 + number][0] = "Substitution holiday";

      number = m.firstDay(9) - 1; // 임시 변수에 10월의 첫날에 대한 패널 정보를 저장
      Oct[3 + number][0] = "Foundation Day";
      Oct[9 + number][0] = "Hangul Proclamation Day";

      number = m.firstDay(11) - 1; // 임시 변수에 12월의 첫날에 대한 패널 정보를 저장
      Dec[25 + number][0] = "Christmas";

   }

   public boolean inputData(int nMonth, int nDay, String strScd) { // 해당 월, 일에 null 상태인 라벨에 내용을 저장하고 그 라벨에 내용이 저장되었다는 boolean값 true 저장
      if (nMonth > 12 || nMonth < 1)                        // 10개 라벨을 전부 돌면서 값이 저장 되지않으면 저장되지 않았다는 boolean값 false 저장
         return false;
      switch (nMonth) {
      case 1: {
         for (int i = 0; i < 10; i++) {
            if (Jan[nDay][i] == null) {
               Jan[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 2: {
         for (int i = 0; i < 10; i++) {
            if (Feb[nDay][i] == null) {
               Feb[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 3: {
         for (int i = 0; i < 10; i++) {
            if (Mar[nDay][i] == null) {
               Mar[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 4: {
         for (int i = 0; i < 10; i++) {
            if (Apr[nDay][i] == null) {
               Apr[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 5: {
         for (int i = 0; i < 10; i++) {
            if (May[nDay][i] == null) {
               May[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 6: {
         for (int i = 0; i < 10; i++) {
            if (Jun[nDay][i] == null) {
               Jun[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 7: {
         for (int i = 0; i < 10; i++) {
            if (Jul[nDay][i] == null) {
               Jul[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 8: {
         for (int i = 0; i < 10; i++) {
            if (Aug[nDay][i] == null) {
               Aug[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 9: {
         for (int i = 0; i < 10; i++) {
            if (Sep[nDay][i] == null) {
               Sep[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 10: {
         for (int i = 0; i < 10; i++) {
            if (Oct[nDay][i] == null) {
               Oct[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 11: {
         for (int i = 0; i < 10; i++) {
            if (Nov[nDay][i] == null) {
               Nov[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }
      case 12: {
         for (int i = 0; i < 10; i++) {
            if (Dec[nDay][i] == null) {
               Dec[nDay][i] = strScd;
               return true;
            }
         }
         return false;
      }

      }
      return false;
   }

   public String getScd(int nMonth, int nDay, int nNum) {      // 파라미터로 넘겨받은 월, 일, 스케줄번호에 대해 스케줄 내용이 있으면 넘겨주고, 아니면 공백을 넘겨줌
      switch (nMonth) {
      case 1:
         if (Jan[nDay][nNum - 1] != null)
            return Jan[nDay][nNum - 1];
         else
            return "";
      case 2:
         if (Feb[nDay][nNum - 1] != null)
            return Feb[nDay][nNum - 1];
         else
            return "";
      case 3:
         if (Mar[nDay][nNum - 1] != null)
            return Mar[nDay][nNum - 1];
         else
            return "";
      case 4:
         if (Apr[nDay][nNum - 1] != null)
            return Apr[nDay][nNum - 1];
         else
            return "";
      case 5:
         if (May[nDay][nNum - 1] != null)
            return May[nDay][nNum - 1];
         else
            return "";
      case 6:
         if (Jun[nDay][nNum - 1] != null)
            return Jun[nDay][nNum - 1];
         else
            return "";
      case 7:
         if (Jul[nDay][nNum - 1] != null)
            return Jul[nDay][nNum - 1];
         else
            return "";
      case 8:
         if (Aug[nDay][nNum - 1] != null)
            return Aug[nDay][nNum - 1];
         else
            return "";
      case 9:
         if (Sep[nDay][nNum - 1] != null)
            return Sep[nDay][nNum - 1];
         else
            return "";
      case 10:
         if (Oct[nDay][nNum - 1] != null)
            return Oct[nDay][nNum - 1];
         else
            return "";
      case 11:
         if (Nov[nDay][nNum - 1] != null)
            return Nov[nDay][nNum - 1];
         else
            return "";
      case 12:
         if (Dec[nDay][nNum - 1] != null)
            return Dec[nDay][nNum - 1];
         else
            return "";
      }
      return "";
   }

   public boolean deleteData(int nMonth, int nDay, int nNum) {  // 해당 월, 일의 스케줄 번호를 체크박스로 넘겨 받아 null 값이 아니면 null로 초기화하고 setScd() 함수를 호출한다.
      switch (nMonth) {                               // setScd() 함수는 스케줄 10개 중에 중간에 있는 스케줄이 지워지면 밑에 있는 스케줄들을 차례차례 올려준다. 
      case 1:                                       
         if (Jan[nDay][nNum - 1] != null) {
            Jan[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 2:
         if (Feb[nDay][nNum - 1] != null) {
            Feb[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 3:
         if (Mar[nDay][nNum - 1] != null) {
            Mar[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 4:
         if (Apr[nDay][nNum - 1] != null) {
            Apr[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 5:
         if (May[nDay][nNum - 1] != null) {
            May[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 6:
         if (Jun[nDay][nNum - 1] != null) {
            Jun[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 7:
         if (Jul[nDay][nNum - 1] != null) {
            Jul[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 8:
         if (Aug[nDay][nNum - 1] != null) {
            Aug[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 9:
         if (Sep[nDay][nNum - 1] != null) {
            Sep[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 10:
         if (Oct[nDay][nNum - 1] != null) {
            Oct[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 11:
         if (Nov[nDay][nNum - 1] != null) {
            Nov[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;
      case 12:
         if (Dec[nDay][nNum - 1] != null) {
            Dec[nDay][nNum - 1] = null;
            setScd(nMonth, nDay);
            return true;
         } else
            return false;

      }

      return false;         // 입력받은 파라미터의 월 정보가 1~12 값이 아니면 false로 넘겨준다.
   }

   public boolean updateData(int nMonth, int nDay, int nNum, String scd) {      // 월, 일, 체크박스번호와 스케줄내용을 입력 받아서 공백이 아니면 그 스케줄 내용으로 새로 저장
      switch (nMonth) {                                          // 공백이라면 새로 값을 입력 받은 후 inputData()함수에서 값을 저장한다.
      case 1:
         if (Jan[nDay][nNum - 1] != null) {
            Jan[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 2:
         if (Feb[nDay][nNum - 1] != null) {
            Feb[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 3:
         if (Mar[nDay][nNum - 1] != null) {
            Mar[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 4:
         if (Apr[nDay][nNum - 1] != null) {
            Apr[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 5:
         if (May[nDay][nNum - 1] != null) {
            May[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 6:
         if (Jun[nDay][nNum - 1] != null) {
            Jun[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 7:
         if (Jul[nDay][nNum - 1] != null) {
            Jul[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 8:
         if (Aug[nDay][nNum - 1] != null) {
            Aug[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 9:
         if (Sep[nDay][nNum - 1] != null) {
            Sep[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 10:
         if (Oct[nDay][nNum - 1] != null) {
            Oct[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 11:
         if (Nov[nDay][nNum - 1] != null) {
            Nov[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }
      case 12:
         if (Dec[nDay][nNum - 1] != null) {
            Dec[nDay][nNum - 1] = scd;
            return true;
         } else {
            return inputData(nMonth, nDay, scd);
         }

      }
      return false;         // 입력 받은 월이 1~12에 없으면 false 값을 반환한다.
   }

   public void setScd(int nMonth, int nDay) { // 해당 월, 일의 스케줄들 중 하나가 삭제 될 경우 그 아래에 있는 스케줄들을 한칸씩 위로 올린다.
	      switch (nMonth) {
	      case 1: {
	         for (int i = 0; i < 10; i++) {
	            if (Jan[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Jan[nDay][j - 1] = Jan[nDay][j];
	                  Jan[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 2: {
	         for (int i = 0; i < 10; i++) {
	            if (Feb[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Feb[nDay][j - 1] = Feb[nDay][j];
	                  Feb[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 3: {
	         for (int i = 0; i < 10; i++) {
	            if (Mar[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Mar[nDay][j - 1] = Mar[nDay][j];
	                  Mar[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 4: {
	         for (int i = 0; i < 10; i++) {
	            if (Apr[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Apr[nDay][j - 1] = Apr[nDay][j];
	                  Apr[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 5: {
	         for (int i = 0; i < 10; i++) {
	            if (May[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  May[nDay][j - 1] = May[nDay][j];
	                  May[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 6: {
	         for (int i = 0; i < 10; i++) {
	            if (Jun[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Jun[nDay][j - 1] = Jun[nDay][j];
	                  Jun[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 7: {
	         for (int i = 0; i < 10; i++) {
	            if (Jul[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Jul[nDay][j - 1] = Jul[nDay][j];
	                  Jul[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 8: {
	         for (int i = 0; i < 10; i++) {
	            if (Aug[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Aug[nDay][j - 1] = Aug[nDay][j];
	                  Aug[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 9: {
	         for (int i = 0; i < 10; i++) {
	            if (Sep[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Sep[nDay][j - 1] = Sep[nDay][j];
	                  Sep[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 10: {
	         for (int i = 0; i < 10; i++) {
	            if (Oct[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Oct[nDay][j - 1] = Oct[nDay][j];
	                  Oct[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 11: {
	         for (int i = 0; i < 10; i++) {
	            if (Nov[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Nov[nDay][j - 1] = Nov[nDay][j];
	                  Nov[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }
	      case 12: {
	         for (int i = 0; i < 10; i++) {
	            if (Dec[nDay][i] == null) {
	               for (int j = i + 1; j < 10; j++) {
	                  Dec[nDay][j - 1] = Dec[nDay][j];
	                  Dec[nDay][j] = null;
	               }
	            } // if
	         } // for
	         return;
	      }

	      } // switch
	   }

   // today
   public static String getTodayDate() {            // 오늘 날짜를 계산하기 위한 함수
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("MM. dd. ");
      return sdf.format(date);
   }
}