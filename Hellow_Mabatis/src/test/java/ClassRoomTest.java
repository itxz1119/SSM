import com.bjpowernode.mapper.ClassRoomMapper;
import com.bjpowernode.pojo.ClassRoom;
import com.bjpowernode.util.MybatisUtil2;
import org.junit.Test;

public class ClassRoomTest {

    private ClassRoomMapper classRoomMapper = MybatisUtil2.openSession(true).getMapper(ClassRoomMapper.class);

    @Test
    public void findById(){
        ClassRoom room = classRoomMapper.findById(1);
        System.out.println(room);
    }

}
