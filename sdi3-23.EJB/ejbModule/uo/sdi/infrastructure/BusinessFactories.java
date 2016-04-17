package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;

public class BusinessFactories {
    private final static String CONFIG_FILE = "/factories.properties";
    
//    public static ServicesFactory services = new ServicesFactoryImpl();
    public static ServicesFactory services = 
	    (ServicesFactory) BusinessFactoriesHelper
	    	.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
}
