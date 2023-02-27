package vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Data
public class DateVO {

    private String year = "";
    private String month = "";
    private String date = "";
    private String value = "";
    private String schedule = "";
    private String schedule_detail = "";


    // default 생성자
    public DateVO() {}

    // 달력만 사용 시 사용될 생성자 ===
    public DateVO(String year, String month, String date, String value) {
        if ((month != null && month != "") && (date != null && date != "")) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.value = value;
        }
    }

    // 스케줄 사용 시 사용될 생성자 ===
    public DateVO(String year, String month, String date, String value, String schedule, String schedule_detail) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.value = value;
        this.schedule = schedule;
        this.schedule_detail = schedule_detail;
    }


    // 이전년도, 이전달, 다음년도, 다음달 구하는 메서드 *****
    private Map<String, Integer> beforeAfterCal(int searchYear, int searchMonth) {

        // return 할 Map 변수
        Map<String, Integer> beforeAfterData = new HashMap<>();

        int beforeYear = searchYear;
        int beforeMonth = searchMonth - 1;
        int afterYear = searchYear;
        int afterMonth = searchMonth + 1;


        // 1월 = 0, 12월 = 11
        if(beforeMonth < 0) {
            beforeMonth = 11;
            beforeYear = searchYear - 1;
        }

        if(afterMonth > 11) {
            afterMonth = 0;
            afterYear = searchYear + 1;
        }

        beforeAfterData.put("beforeYear", beforeYear);
        beforeAfterData.put("beforeMonth", beforeMonth);
        beforeAfterData.put("afterYear", afterYear);
        beforeAfterData.put("afterMonth", afterMonth);

        return beforeAfterData;

    }


    // 날짜에 관련된 정보를 가지고 오는 메서드 *****
    public Map<String, Integer> todayInfo(DateVO date) {

        // return 을 위한 Map 변수
        Map<String, Integer> todayData = new HashMap<>();

        // 해당 월의 첫날을 위한 Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.getYear()), Integer.parseInt(date.getMonth()), 1);

        int startDate = calendar.getMinimum(Calendar.DATE);                 // 시작 날짜
        int endDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);     // 마지막 날짜
        int start = calendar.get(Calendar.DAY_OF_WEEK);                     // 시작 요일

        // 현재 날짜 정보
        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat yearF = new SimpleDateFormat("yyyy");
        SimpleDateFormat monF = new SimpleDateFormat("M");

        int todayYear = Integer.parseInt(yearF.format(todayCal.getTime()));
        int todayMonth = Integer.parseInt(monF.format(todayCal.getTime()));

        // 넘어온 날짜 정보
        int searchYear = Integer.parseInt(date.getYear());
        int searchMonth = Integer.parseInt(date.getMonth()) + 1;

        int today = -1;

        if(todayYear == searchYear && todayMonth == searchMonth) {
            SimpleDateFormat dateF = new SimpleDateFormat("dd");
            today = Integer.parseInt(dateF.format(todayCal.getTime()));
        }

        searchMonth = searchMonth - 1;

        Map<String, Integer> beforeAfterCal = beforeAfterCal(searchYear, searchMonth);

        todayData.put("start", start);              // 시작 요일
        todayData.put("startDate", startDate);      // 시작 날짜
        todayData.put("endDate", endDate);          // 마지막 날짜
        todayData.put("today", today);              // 현재 날짜
        todayData.put("searchYear", searchYear);    // 넘어온 년도
        todayData.put("searchMonth", searchMonth);  // 넘어온 월
        todayData.put("beforeYear", beforeAfterCal.get("beforeYear"));      // 이전 년도
        todayData.put("beforeMonth", beforeAfterCal.get("beforeMonth"));    // 이전 월
        todayData.put("afterYear", beforeAfterCal.get("afterYear"));        // 다음 년도
        todayData.put("afterMonth", beforeAfterCal.get("afterMonth"));      // 다음 월

        return todayData;

    }

}
