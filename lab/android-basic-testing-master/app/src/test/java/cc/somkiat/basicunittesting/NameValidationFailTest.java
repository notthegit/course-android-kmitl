package cc.somkiat.basicunittesting;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;

public class NameValidationFailTest {
    @Test
    public void name_is_null(){
        NameValidation validation = new NameValidation();
        boolean result = validation.isEmpty("");
        assertFalse("ต้องไม่ผ่านนะ เพราะว่ามันมีค่าว่าง !!",result);
    }
    @Test
    public void name_too_short(){
        NameValidation validation = new NameValidation();
        boolean result = validation.okName("A");
        assertFalse("ต้องไม่ผ่านนะ เพราะชื่อสั้นไป !!",result);
    }
    @Test
    public void name_too_long(){
        NameValidation validation = new NameValidation();
        boolean result = validation.okName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse("ต้องไม่ผ่านนะ เพราะชื่อสยาวไป !!",result);
    }
}
