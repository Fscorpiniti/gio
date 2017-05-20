package ar.edu.untref.gio.domain;

import java.util.List;

public interface InvestmentRepository extends Repository<UserInvestment> {

    List<Investment> getAll();

    List<UserInvestment> findByUserId(Integer ownerId);
}
