package com.ztiany.springf.test.tx;

/*
create table spring_test_account(
    id int(20) NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    money varchar(20) DEFAULT NULL,
    PRIMARY KEY (id)
);
 */
public interface AccountDao {

    //加钱
    void increaseMoney(Integer id, Double money);

    //减钱
    void decreaseMoney(Integer id, Double money);
}
