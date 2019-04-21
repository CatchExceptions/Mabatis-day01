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
    public List<Specilinfo> selectbysql(@Param("spilinfoname") String spilinfoname);
    public List<Specilinfo> selectbyid(int i);
//    所有查询
public List<Specilinfo> selectbigList(Map<String,Object> map);
//批量增加
    public  int doubleinsert(List<Student> list);
//批量删除
    public  int doubledelete(List<Student> list);
//    批量修改
    public int  doubleupdata(List<Student> list);
}
