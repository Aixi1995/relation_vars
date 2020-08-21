import com.example.ValidateCleanBoot;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.repository.ProdDefineRepository;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/21 15:58
 * @desc
 */
@SpringBootTest(classes = {ValidateCleanBoot.class})
@Slf4j
public class ProdDefineRepositoryTest {

    @Autowired
    private ProdDefineRepository repository;

    @Test
    public void testFindById() {
        repository.findById(1).ifPresent( prodDefine -> {
            log.info(prodDefine.toString());
        });
    }
}
