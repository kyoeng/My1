package mappers;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import vo.MemberVO;

@Repository
public class MemberMapperImpl implements MemberMapper {

    // MyBatis SqlSession 을 위한 변수
    private final SqlSessionTemplate sqlSessionTemplate;
    // Mapper 에 대한 namespace 저장 변수
    private static final String NAMESPACE = "mappers.MemberMapper";


    // 생성자 ( 하나만 있으므로 @Autowired 생략 )
    public MemberMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    // 로그인을 위한 method
    @Override
    public MemberVO selectOne(MemberVO vo) {
        return sqlSessionTemplate.selectOne(NAMESPACE + ".login", vo);
    }

    // 회원가입 id check를 위한 method
    @Override
    public int idCheck(String id) { return sqlSessionTemplate.selectOne(NAMESPACE + ".idCheck", id); }

    // 회원가입을 위한 method
    @Override
    public int insert(MemberVO vo) {
        return sqlSessionTemplate.insert(NAMESPACE + ".join", vo);
    }
}
