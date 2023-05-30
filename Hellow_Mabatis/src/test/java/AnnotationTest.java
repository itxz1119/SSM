import org.junit.*;

public class AnnotationTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass=========");
    }

    @Before
    public  void before() {
        System.out.println("Before=========");
    }

    @Test
    public void test01() {
        System.out.println("test01=========");
    }

    @Test
    public void test02() {
        System.out.println("test02=========");
    }

    @Test
    public void test03() {
        System.out.println("test03=========");
    }

    @After
    public  void After() {
        System.out.println("After=========");
    }

    @AfterClass
    public static void AfterClass() {
        System.out.println("AfterClass=========");
    }


}
