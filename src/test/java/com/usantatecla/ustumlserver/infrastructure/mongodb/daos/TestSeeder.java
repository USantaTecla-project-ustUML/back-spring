package com.usantatecla.ustumlserver.infrastructure.mongodb.daos;

import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.ClassEntity;
import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.MemberEntity;
import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.PackageEntity;
import com.usantatecla.ustumlserver.infrastructure.mongodb.entities.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TestSeeder {

    public static String SESSION_OPEN = "OPEN";
    public static String INSIDE_PACKAGE_ID = "package123ID";

    private Seeder seeder;
    private PackageDao packageDao;
    private ClassDao classDao;
    private UserDao userDao;
    private SessionDao sessionDao;

    @Autowired
    public TestSeeder(Seeder seeder, PackageDao packageDao, ClassDao classDao, UserDao userDao, SessionDao sessionDao) {
        this.seeder = seeder;
        this.packageDao = packageDao;
        this.classDao = classDao;
        this.userDao = userDao;
        this.sessionDao = sessionDao;
        this.initialize();
    }

    public void initialize() {
        this.seeder.initialize();
    }

    public void seedOpen() {
        ClassEntity classEntity = ClassEntity.builder().id("class123ID").name("TestClass").build();
        this.classDao.save(classEntity);
        PackageEntity packageEntity = PackageEntity.builder()
                .id(TestSeeder.INSIDE_PACKAGE_ID)
                .name("test").build();
        this.packageDao.save(packageEntity);
        Optional<PackageEntity> mainPackageDB = this.packageDao.findById(Seeder.PROJECT_ID);
        if (mainPackageDB.isPresent()) {
            PackageEntity mainPackage = mainPackageDB.get();
            List<MemberEntity> memberEntities = List.of(packageEntity, classEntity);
            mainPackage.setMemberEntities(memberEntities);
            this.packageDao.save(mainPackage);
            SessionEntity openSession = SessionEntity.builder()
                    .sessionId(TestSeeder.SESSION_OPEN)
                    .memberEntities(List.of(mainPackage))
                    .build();
            this.sessionDao.save(openSession);
        }
    }

    public void seedClose() {
        this.seedOpen();
        SessionEntity sessionEntity = this.sessionDao.findBySessionId(TestSeeder.SESSION_OPEN);
        List<MemberEntity> memberEntities = sessionEntity.getMemberEntities();
        memberEntities.add(this.packageDao.findByName("test"));
        sessionEntity.setMemberEntities(memberEntities);
        this.sessionDao.save(sessionEntity);
    }

}
