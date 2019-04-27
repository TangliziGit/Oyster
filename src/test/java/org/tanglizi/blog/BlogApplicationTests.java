package org.tanglizi.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tanglizi.blog.dao.ExampleQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    private Logger logger= LoggerFactory.getLogger(BlogApplication.class);

    @Test
    public void testCombineWithLogic() {
    }

}
