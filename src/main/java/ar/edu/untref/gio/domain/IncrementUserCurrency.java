package ar.edu.untref.gio.domain;

public class IncrementUserCurrency implements UserCurrencyOperation {

    private UserRepository userRepository;
    private Double amount;

    public IncrementUserCurrency(UserRepository userRepository, Double amount) {
        this.userRepository = userRepository;
        this.amount = amount;
    }

    @Override
    public User execute(User user) {
        user.incrementCoins(amount);
        userRepository.add(user);
        return user;
    }
}
