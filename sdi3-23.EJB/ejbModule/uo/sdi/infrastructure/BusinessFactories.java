package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.LocalEjbServicesLocator;

public class BusinessFactories {
//    public static ServicesFactory services = new ServicesFactoryImpl();
    public static ServicesFactory services = new LocalEjbServicesLocator();
}
