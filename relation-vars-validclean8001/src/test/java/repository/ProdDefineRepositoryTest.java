package repository;

import com.example.ValidateCleanBoot;
import entity.ProdDefine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.repository.ProdDefineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    /**
     * spring data jpa find by id
     */
    @Test
    public void testFindById() {
        repository.findById(1).ifPresent( prodDefine -> log.info(prodDefine.toString()));
    }

    /**
     * spring data jpa page_query
     */
    @Test
    public void testPage() {
        Page<ProdDefine> prodDefinePage = repository.findAll(PageRequest.of(0, 20, Sort.by("id")));
        prodDefinePage.get().forEach( prodDefine -> log.info(prodDefine.toString()));
    }
}
