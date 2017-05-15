package ar.edu.untref.gio.domain;


import org.apache.commons.lang3.RandomStringUtils;

public class TokenRandomAlphanumericGenerator implements TokenGenerator {

    private static final int DEFAULT_TOKEN_LENGTH = 12;
    private Integer tokenLength;

    public TokenRandomAlphanumericGenerator(Integer tokenLength) {
        this.tokenLength = tokenLength;
    }

    @Override
    public String generate() {
        if (tokenLength == null) {
            tokenLength = DEFAULT_TOKEN_LENGTH;
        }

        return RandomStringUtils.randomAlphanumeric(tokenLength);
    }

}
