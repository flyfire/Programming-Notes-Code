package me.ztiany.test.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.6.12 23:18
 */
@Component("service")
public class ServiceImpl implements Service {

    @Autowired
    @Qualifier("worker")
    private Worker mWorker;

    @Override
    public void start() {
        mWorker.doWork();
    }

}