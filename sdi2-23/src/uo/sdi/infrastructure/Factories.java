package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.ServicesFactoryImpl;
import uo.sdi.presentation.BeanFactory;
import uo.sdi.presentation.impl.SimpleBeanFactory;

public class Factories {
    public static ServicesFactory services = new ServicesFactoryImpl();
    public static BeanFactory beans = new SimpleBeanFactory();
}
