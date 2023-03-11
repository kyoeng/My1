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
    private ScheduleVO schedule;


    // default 생성자
    public DateVO() {}

    // 생성자
    public DateVO(String year, String month, String date, String value) {
        if ((month != null && month != "") && (date != null && date != "")) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.value = value;
        }
    }

    // 생성자
    public DateVO(String year, String month, String date, String value, ScheduleVO schedule) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.value = value;
        this.schedule = schedule;
    }



    private Map<String, Integer> beforeAfterCal(int searchYear, int searchMonth) {


        Map<String, Integer> beforeAfterData = new HashMap<>();

        int beforeYear = searchYear;
        int beforeMonth = searchMonth - 1;
        int afterYear = searchYear;
        int afterMonth = searchMonth + 1;



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



    public Map<String, Integer> todayInfo(DateVO date) {


        Map<String, Integer> todayData = new HashMap<>();


        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.getYear()), Integer.parseInt(date.getMonth()), 1);

        int startDate = calendar.getMinimum(Calendar.DATE);                 // ���� ��¥
        int endDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);     // ������ ��¥
        int start = calendar.get(Calendar.DAY_OF_WEEK);                     // ���� ����


        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat yearF = new SimpleDateFormat("yyyy");
        SimpleDateFormat monF = new SimpleDateFormat("M");

        int todayYear = Integer.parseInt(yearF.format(todayCal.getTime()));
        int todayMonth = Integer.parseInt(monF.format(todayCal.getTime()));


        int searchYear = Integer.parseInt(date.getYear());
        int searchMonth = Integer.parseInt(date.getMonth()) + 1;

        int today = -1;

        if(todayYear == searchYear && todayMonth == searchMonth) {
            SimpleDateFormat dateF = new SimpleDateFormat("dd");
            today = Integer.parseInt(dateF.format(todayCal.getTime()));
        }

        searchMonth = searchMonth - 1;

        Map<String, Integer> beforeAfterCal = beforeAfterCal(searchYear, searchMonth);

        todayData.put("start", start);
        todayData.put("startDate", startDate);
        todayData.put("endDate", endDate);
        todayData.put("today", today);
        todayData.put("searchYear", searchYear);
        todayData.put("searchMonth", searchMonth);
        todayData.put("beforeYear", beforeAfterCal.get("beforeYear"));
        todayData.put("beforeMonth", beforeAfterCal.get("beforeMonth"));
        todayData.put("afterYear", beforeAfterCal.get("afterYear"));
        todayData.put("afterMonth", beforeAfterCal.get("afterMonth"));

        return todayData;

    }

}
