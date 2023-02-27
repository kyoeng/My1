package mappers;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import vo.MemberVO;

@Repository
public class MemberMapperImpl implements MemberMapper {

    // MyBatis SqlSession �� ���� ����
    private final SqlSessionTemplate sqlSessionTemplate;
    // Mapper �� ���� namespace ���� ����
    private static final String NAMESPACE = "mappers.MemberMapper";


    // ������ ( �ϳ��� �����Ƿ� @Autowired ���� )
    public MemberMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    // �α����� ���� method
    @Override
    public MemberVO selectOne(MemberVO vo) {
        return sqlSessionTemplate.selectOne(NAMESPACE + ".login", vo);
    }

    // ȸ������ id check�� ���� method
    @Override
    public int idCheck(String id) { return sqlSessionTemplate.selectOne(NAMESPACE + ".idCheck", id); }

    // ȸ�������� ���� method
    @Override
    public int insert(MemberVO vo) {
        return sqlSessionTemplate.insert(NAMESPACE + ".join", vo);
    }
}
