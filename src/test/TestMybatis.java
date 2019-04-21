package test;

import mapper.StudentMapper;
import opjo.MyBatisUtils;
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
import java.util.ArrayList;
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
//    二级缓存测试
    @Test
    public void demo5(){
        SqlSession session=MyBatisUtils.getSqlSession(false);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        List<Specilinfo> list4=studentMapper.selectbyid(1);
        System.out.println(list4);
//        如果中间sqlSession去执行commit操作（执行插入、更新、删除），则会清空SqlSession中的一级缓存
//        session.commit();
        System.out.println("*****************");
        StudentMapper studentMapper2=session.getMapper(StudentMapper.class);
        List<Specilinfo> list=studentMapper.selectbyid(1);
        System.out.println(list);
        session.close();
    }
//    动态sql
    @Test
    public void demo6(){
        SqlSession session=MyBatisUtils.getSqlSession(false);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);

        List<Specilinfo> list4=studentMapper.selectbysql("无");
        for (Specilinfo specilinfo : list4) {
            System.out.println(specilinfo);
        }
    }
//    所有查询方法
    @Test
    public void demo7(){
        SqlSession session=MyBatisUtils.getSqlSession(false);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        Map<String,Object> map3=new HashMap<>();
        int pageNum=1;
        int pageSize=5;
        int start=(pageNum-1)*pageSize;
//            int spilinfoid=1;
//        String colNameLike="spilinfoname";
//        String keyword="";
//        String colNameOrder="spilinfoid";
        map3.put("pageNum",pageNum);
        map3.put("pageSize",pageSize);
        map3.put("start",start);
//        map3.put("spilinfoid",spilinfoid);
//        map3.put("colNameLike",colNameLike);
//        map3.put("keyword",keyword);
//        map3.put("colNameOrder",colNameOrder);
        List<Specilinfo> list4=studentMapper.selectbigList(map3);
        for (Specilinfo specilinfo : list4) {
            System.out.println(specilinfo);
        }
    }
//批量增加
    @Test
    public void demo8(){
        SqlSession session=MyBatisUtils.getSqlSession(true);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        List<Student> list=new ArrayList<>();
        list.add(new Student("张三丰",50));
        list.add(new Student("张四丰",80));
        list.add(new Student("张五丰",90));
        list.add(new Student("张六丰",100));
        int i=studentMapper.doubleinsert(list);
        System.out.println("成功插入"+i+"条数据");
        session.close();
    }

//    批量删除
    @Test
    public  void demo9(){
        SqlSession session=MyBatisUtils.getSqlSession(true);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        List<Student> list=new ArrayList<>();
        list.add(new Student(8));
        list.add(new Student(9));
        int i=studentMapper.doubledelete(list);
        System.out.println("成功删除"+i+"条数据");
        session.close();
    }
//    批量修改
    @Test
    public void demo10(){
        SqlSession session=MyBatisUtils.getSqlSession(true);
        StudentMapper studentMapper=session.getMapper(StudentMapper.class);
        List<Student> list2=new ArrayList<>();
       list2.add(new Student(14,"刘学友",60));
        list2.add(new Student(15,"高德华",50));
        int i=studentMapper.doubleupdata(list2);
        System.out.println("成功修改"+i+"条数据");
        session.close();

    }
}

