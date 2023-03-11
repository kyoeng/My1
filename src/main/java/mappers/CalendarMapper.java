package mappers;


import vo.ScheduleVO;

import java.util.List;

public interface CalendarMapper {

    /* 해당 달의 일정 가져오기 (반복 X) */
    List<ScheduleVO> getMonthDataD(ScheduleVO vo);

    /* 해당 달의 일정 가져오기 (반복) */
    List<ScheduleVO> getMonthDataE(ScheduleVO vo);

    /* 해당 일의 일정 가져오기 (반복 X) */
    List<ScheduleVO> getDetailDataD(ScheduleVO vo);

    /* 해당 일의 일정 가져오기 (반복) */
    List<ScheduleVO> getDetailDataE(ScheduleVO vo);

    /* 일정 등록 */
    int insertD(ScheduleVO vo);

    /* 일정 등록 (반복) */
    int insertE(ScheduleVO vo);
}
