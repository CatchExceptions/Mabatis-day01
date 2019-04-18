package test;

import mapper.StudentMapper;
import opjo.QueryVo;
import opjo.Specilinfo;
import opjo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    private static SqlSessionFactory sqlSessionFactory;// 单例

    static {// static块-------只做一次
        try {
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    包装的pojo传递
    @Test
    public void demo1() throws IOException {
         SqlSession session=sqlSessionFactory.openSession();
         StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        QueryVo vo=new QueryVo();
        Student student=new Student();
        student.setName("张");
        vo.setStudent(student);
         List<Student> list =studentMapper.selectLike(vo);
        for (Student student1 : list) {
            System.out.println(student1);
        }
        session.close();
    }
    @Test
    public  void demo2() throws IOException {
        SqlSession session=sqlSessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        Map<String,Object> map=new HashMap<>();
        String zdname="name";
        String mohu="张";
        int cout=2;
        int index=0;
        int starpage=index+cout-1;
        map.put("zdname",zdname);
        map.put("mohu",mohu);
        map.put("index",index);
        map.put("cout",cout);
        map.put("starpage",starpage);
        List<Student> list=studentMapper.selectMap(map);
        for (Student student1 : list) {
            System.out.println(student1);
        }
    }
//    模糊查询,任意字段排序,分页显示
    @Test
    public void demo3() throws IOException {
        SqlSession session=sqlSessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        Map<String,Object> map2=new HashMap<>();
        int pageNum=1;
        int pageSize=5;
        int start=(pageNum-1)*pageSize;
        String colNameLike="spilinfoname";
        String keyword="无";
        String colNameOrder="spilinfoid";
        map2.put("pageNum",pageNum);
        map2.put("pageSize",pageSize);
        map2.put("start",start);
        map2.put("colNameLike",colNameLike);
        map2.put("keyword",keyword);
        map2.put("colNameOrder",colNameOrder);
        List<Specilinfo> list2=studentMapper.selectMap2(map2);
        for (Specilinfo specilinfo : list2) {
            System.out.println(specilinfo);
        }
        session.close();
    }
    @Test
   public  void demo4(){
        SqlSession session=sqlSessionFactory.openSession();
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        List<Specilinfo> list3=studentMapper.selectMap3("电",1,5,0);
        for (Specilinfo specilinfo : list3) {
            System.out.println(specilinfo);
        }
    }
}

