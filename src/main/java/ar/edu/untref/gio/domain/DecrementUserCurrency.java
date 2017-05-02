package ar.edu.untref.gio.domain;

public class DecrementUserCurrency implements UserCurrencyOperation {

    private UserRepository userRepository;
    private Double amount;

    public DecrementUserCurrency(UserRepository userRepository, Double amount) {
        this.userRepository = userRepository;
        this.amount = amount;
    }

    public User execute(User user) {
        user.decrementCoins(amount);
        userRepository.add(user);
        return user;
    }

}
