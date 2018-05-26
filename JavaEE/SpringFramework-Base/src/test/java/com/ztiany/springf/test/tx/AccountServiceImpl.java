package com.ztiany.springf.test.tx;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/*注解是第二种使用事务模板的方式(针对类中所有方法)*/
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class AccountServiceImpl implements AccountService {

    private AccountDao ad;
    @SuppressWarnings("all")
    private TransactionTemplate tt;

    @Override
    /*仅针对方法，优先级高*/
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(final Integer from, final Integer to, final Double money) {
        //减钱
        ad.decreaseMoney(from, money);
        //你一异常
        int i = 1 / 0;
        //加钱
        ad.increaseMoney(to, money);
    }

    /*@Override
        public void transfer(final Integer from,final Integer to,final Double money) {
            tt.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                    //减钱
                    ad.decreaseMoney(from, money);
                    int i = 1/0;
                    //加钱
                    ad.increaseMoney(to, money);
                }
            });
        }
    */

    public void setAd(AccountDao ad) {
        this.ad = ad;
    }

    public void setTt(TransactionTemplate tt) {
        this.tt = tt;
    }


}
