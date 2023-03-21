package vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchCri extends Criteria {

    // 검색 기능을 위한 필드
    private String keyword;
    private String check;

}
