import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TestJavaFeature {

    @Test
    public void testOptional() {
        try{
            final Optional<String> name = Optional.of("NAMe");
            assertNotNull(name);
            assertTrue(name.isPresent());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
