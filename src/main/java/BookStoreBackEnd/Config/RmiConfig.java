package BookStoreBackEnd.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import BookStoreBackEnd.Service.RmiService;

@Configuration
public class RmiConfig {
    @Bean
    public RmiProxyFactoryBean rmiService(){
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://127.0.0.1:8090/RmiService");
        rmiProxyFactory.setServiceInterface(RmiService.class);
        //rmiProxyFactory.setCacheStub(true);
        //rmiProxyFactory.setRefreshStubOnConnectFailure(true);
        //rmiProxyFactory.setLookupStubOnStartup(false);
        return rmiProxyFactory;

    }

}
