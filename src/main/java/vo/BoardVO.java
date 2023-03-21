package vo;


import lombok.Data;

@Data
public class BoardVO {

    private int seq;
    private String title;
    private String content;
    private String id;
    private String reg_date;

}
