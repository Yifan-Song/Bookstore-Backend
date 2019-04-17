package bookstorebackend.Service;


import javax.jws.WebService;

@WebService(name = "DemoWebService", // 暴露服务名称
        targetNamespace = "http://service.mq.primeton.com"// 命名空间,一般是接口的包名倒序
)
public interface DemoWebService {
    public String sayHello(String user);

}