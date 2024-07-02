import com.otocado.basket.BasketSplitter;
import com.otocado.services.BasketReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class test1 {

    @Test
    public void testAdd() throws IOException {
        BasketSplitter basketSplitter = new BasketSplitter("/home/bartek/Documents/Docs/Zadanie/ZadanieRekrutacyjny/src/main/resources/config-0.json");
        BasketReader basketReader = new BasketReader("/home/bartek/Documents/Docs/Zadanie/ZadanieRekrutacyjny/src/main/resources/basket-0.json");

        Map<String, List<String>> result = basketSplitter.split(basketReader.getBasket());

        assertEquals("{Courier=[Garden Chair, Espresso Machine], Express Delivery=[Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.), Carrots (1kg)]}", result.toString(), "Test message");
    }

}