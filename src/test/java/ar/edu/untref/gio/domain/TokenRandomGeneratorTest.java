package ar.edu.untref.gio.domain;

import org.junit.Assert;
import org.junit.Test;

public class TokenRandomGeneratorTest {

    private static final int DEFAULT_LENGTH = 12;

    @Test
    public void whenGenerateTokenWithFixedLengthThenReturnTokenWithThatLength() {
        Integer tokenLength = 16;
        String token = new TokenRandomAlphanumericGenerator(tokenLength).generate();

        Assert.assertEquals(tokenLength.intValue(), token.length());
    }

    @Test
    public void whenGenerateTokenWithNullTokenLengthThenReturnTokenWithDefaultLength() {
        Integer tokenLength = null;
        String token = new TokenRandomAlphanumericGenerator(tokenLength).generate();

        Assert.assertEquals(DEFAULT_LENGTH, token.length());
    }

}
