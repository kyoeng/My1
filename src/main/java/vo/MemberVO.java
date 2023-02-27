package vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberVO {

    private String id;
    private String password;
    private String name;
    private int point;
    private String phone;
    private String date_created;
    private String upload_image;			// 이미지 파일명을 위한 필드 ( 실제 DB에 저장될 내용 )
    private MultipartFile upload_imageF;    // 이미지 파일을 전달받기 위한 필드

}
