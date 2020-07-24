package howl.in.disguise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(TransformerResource.class)
public class TransformerRestServiceApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransformerRepository repository;

    @Test
    public void testCreateTransformer() {
        Transformer transformer = new Transformer();
        transformer.setId((long) 10);
        transformer.setName("Cooper");

        Mockito.when(repository.save(transformer)).thenReturn(transformer);
    }
    @Test
    public void dummyTest(){
        assert(true);
    }

}
