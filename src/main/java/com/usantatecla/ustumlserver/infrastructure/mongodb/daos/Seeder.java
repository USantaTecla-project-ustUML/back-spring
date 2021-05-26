package com.usantatecla.ustumlserver.infrastructure.mongodb.daos;

import com.usantatecla.ustumlserver.domain.model.Role;
import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.PackageEntity;
import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class Seeder {

    public static String PROJECT_ID = "project123";

    private PackageDao packageDao;
    private ClassDao classDao;
    private AccountDao accountDao;
    private SessionDao sessionDao;

    @Autowired
    public  Seeder(PackageDao packageDao, ClassDao classDao, AccountDao accountDao, SessionDao sessionDao) {
        this.packageDao = packageDao;
        this.classDao = classDao;
        this.accountDao = accountDao;
        this.sessionDao = sessionDao;
        this.initialize();
    }

    public void initialize() {
        this.deleteAll();
        this.seed();
    }

    private void deleteAll() {
        this.packageDao.deleteAll();
        this.classDao.deleteAll();
        this.accountDao.deleteAll();
        this.sessionDao.deleteAll();
    }

    private void seed() {
        this.packageDao.save(PackageEntity.builder().id(Seeder.PROJECT_ID).name("name").build());
        String pass = new BCryptPasswordEncoder().encode("a");
        this.accountDao.save(AccountEntity.builder().email("a").password(pass).role(Role.AUTHENTICATED).build());
    }

}
