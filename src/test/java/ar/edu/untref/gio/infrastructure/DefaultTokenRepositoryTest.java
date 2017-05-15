package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenRandomAlphanumericGenerator;
import ar.edu.untref.gio.domain.TokenRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@WebAppConfiguration
public class DefaultTokenRepositoryTest {

    private static final int VALID_USER_ID = 1;
    private static final int TOKEN_LENGTH = 16;

    @Resource(name = "defaultTokenRepository")
    private TokenRepository tokenRepository;

    @Test
    public void whenFindTokensWithoutTokenThenResultIsEmpty() {
        String tokenAlpha = generateTokenAlphanumeric();
        List<Token> tokens = this.tokenRepository.findBy(VALID_USER_ID, tokenAlpha);
        Assert.assertTrue(tokens.isEmpty());
    }

    @Test
    public void whenFindTokensWithValidTokenThenResultContainsThisToken() {
        String tokenAlpha = generateTokenAlphanumeric();
        this.tokenRepository.add(new Token(VALID_USER_ID, tokenAlpha));

        List<Token> tokens = this.tokenRepository.findBy(VALID_USER_ID, tokenAlpha);
        Assert.assertEquals(tokenAlpha, tokens.stream().findFirst().get().getValue());
    }

    @Test
    public void whenDeleteValidTokenThenResultIsEmpty() {
        String tokenAlpha = generateTokenAlphanumeric();
        this.tokenRepository.add(new Token(VALID_USER_ID, tokenAlpha));

        this.tokenRepository.remove(VALID_USER_ID);

        List<Token> tokens = this.tokenRepository.findBy(VALID_USER_ID, tokenAlpha);
        Assert.assertTrue(tokens.isEmpty());
    }

    @Test
    public void whenDeleteValidTokenWithMultipleRowThenResultIsEmpty() {
        String tokenAlpha = generateTokenAlphanumeric();
        this.tokenRepository.add(new Token(VALID_USER_ID, tokenAlpha));
        this.tokenRepository.add(new Token(VALID_USER_ID, tokenAlpha));

        this.tokenRepository.remove(VALID_USER_ID);

        List<Token> tokens = this.tokenRepository.findBy(VALID_USER_ID, tokenAlpha);
        Assert.assertTrue(tokens.isEmpty());
    }

    private String generateTokenAlphanumeric() {
        return new TokenRandomAlphanumericGenerator(TOKEN_LENGTH).generate();
    }

}
