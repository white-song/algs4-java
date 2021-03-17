package practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

interface Product {
    void show();
}

class ProductA implements Product {
    @Override
    public void show() { }
}

class ProductB implements Product {
    @Override
    public void show() {}
}

interface Factory {
    Product creatProduct();
}

class ProductAFactory implements Factory {

    @Override
    public Product creatProduct() {
        return new ProductA();
    }
}


interface Obervered {
    boolean registerObserver(Observer observer);
    boolean removeObserver(Observer observer);
    void notifyObserver(String msg);
}

interface Observer {
    void update(String message);
}

class WatchedServer implements Obervered {

    List<Observer> observers = new ArrayList<>();

    @Override
    public boolean registerObserver(Observer observer) {
        return observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    @Override
    public void notifyObserver(String msg) {
        for (Observer o : observers) {
            o.update(msg);
        }
    }
}

class WatchUser implements Observer {

    Logger log = LoggerFactory.getLogger(WatchUser.class);

    @Override
    public void update(String message) {
        //todo somethong
        log.info(message);
    }

    public static void main(String[] args) {
        Obervered obervered = new WatchedServer();
        Observer user = new WatchUser();
        obervered.registerObserver(user);
        obervered.notifyObserver("remove");
    }
}

interface IUserDao {
    void save(String info);
}

class UserDao implements IUserDao {

    @Override
    public void save(String info) {

    }
}

class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(target, args);
            }
        });
    }
}


public class DesignPatterns {
}
