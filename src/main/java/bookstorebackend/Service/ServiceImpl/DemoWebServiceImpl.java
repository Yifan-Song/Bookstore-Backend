package bookstorebackend.Service.ServiceImpl;

import bookstorebackend.Service.DemoWebService;

import javax.jws.WebService;
import java.util.Date;

@WebService(serviceName = "DemoWebService", // 与接口中指定的name一致
        targetNamespace = "http://service.mq.primeton.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "bookstorebackend.Service.DemoWebService"// 接口地址
)
public class DemoWebServiceImpl implements DemoWebService{
    @Override
    public String sayHello(String user) {
        return user+"，现在时间："+"("+new Date()+")";
    }

}
