package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.ServicesFactoryImpl;

public class BusinessFactories {
    public static ServicesFactory services = new ServicesFactoryImpl();
}
