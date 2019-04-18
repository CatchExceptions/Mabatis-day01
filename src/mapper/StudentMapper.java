package mapper;

import opjo.QueryVo;
import opjo.Specilinfo;
import opjo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
//    传递包装opjo
    public List<Student> selectLike(QueryVo vo);
    public List<Student> selectMap(Map<String,Object> map);
    public List<Specilinfo> selectMap2(Map<String,Object> map);
    public List<Specilinfo> selectMap3(@Param(value = "keyword")String keyword,@Param(value = "pageNum" )int pageNum,@Param(value = "pageSize")int pageSize,@Param(value = "start")int start);
}
