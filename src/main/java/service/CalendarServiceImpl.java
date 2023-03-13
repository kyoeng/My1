package service;


import mappers.CalendarMapper;
import org.springframework.stereotype.Service;
import vo.ScheduleVO;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    // 필드
    private final CalendarMapper mapper;

    // 생성자
    public CalendarServiceImpl(CalendarMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 해당 달의 일정을 가져오기 위한 메서드
     * @param vo ScheduleVO
     * @return ScheduleVO List
     */
    @Override
    public List<ScheduleVO> getMonthDataD(ScheduleVO vo) {
        return mapper.getMonthDataD(vo);
    }

    /**
     * 해당 달의 일정을 가져오기 위한 메서드 ( 반복 )
     * @param vo ScheduleVO
     * @return ScheduleVO List
     */
    @Override
    public List<ScheduleVO> getMonthDataE(ScheduleVO vo) {
        return mapper.getMonthDataE(vo);
    }


    /**
     * 해당 일의 일정을 가져오기 위한 메서드
     * @param vo ScheduleVO
     * @return ScheduleVO List
     */
    @Override
    public List<ScheduleVO> getDetailDataD(ScheduleVO vo) {
        return mapper.getDetailDataD(vo);
    }

    /**
     * 해당 일의 일정을 가져오기 위한 메서드 ( 반복 )
     * @param vo ScheduleVO
     * @return ScheduleVO List
     */
    @Override
    public List<ScheduleVO> getDetailDataE(ScheduleVO vo) {
        return mapper.getDetailDataE(vo);
    }


    /**
     * 일정 등록을 위한 메서드
     * @param vo ScheduleVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int insertD(ScheduleVO vo) {
        return mapper.insertD(vo);
    }

    /**
     * 일정 등록을 위한 메서드
     * @param vo ScheduleVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int insertE(ScheduleVO vo) {
        return mapper.insertE(vo);
    }


    /**
     * 일정 삭제를 위한 메서드
     * @param vo ScheduleVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int deleteD(ScheduleVO vo) {
        return mapper.deleteD(vo);
    }


    /**
     * 일정 삭제를 위한 메서드 (반복)
     * @param vo ScheduleVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int deleteE(ScheduleVO vo) {
        return mapper.deleteE(vo);
    }


    /**
     * 오늘 날짜 지우기
     * @param vo ScheduleVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int deleteCron(ScheduleVO vo) {
        return mapper.deleteCron(vo);
    }
}
