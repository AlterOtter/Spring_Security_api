#JPA

FetchType  

지연 로딩 Lazy -  자식 객체에 대한 쿼리를 가지고 있다가 자식 객체를 가져올떄(GET 메서드) 가져오는 쿼리 작동 
              -  쿼리문을 많이 날리기 떄문에 네트워크적으로 손해
              -  Join 을 사용하여 조회하면 Proxy 객체가 조회된다 ( 자식 객체 )

즉시 로딩 Eager - 한번에 다 가져온다  Lazy와 달리 Proxy 객체가 아니라 실제 객체를 가져온다.
                - N+1 문제를 일으킬 수 있다. -> JPQL을 이용하자



#Spring Cloud

1. 다수의 서비스를 어떻게 찾아야 하는가?   
    - 서비스 디스커버리 사용
    - Spring Cloud 에서는 Eureka 
2. 사용하기 위한 다수 서비스의 인스턴스를 어떻게 결정해야 하는가?
    - 클라이언트 사이드 로드 밸런싱
    - Ribbon 사용
3. 개별적인 서비스가 응답하지 않을때 어떤 일이 발생 하는가?
    - 결함 허용
    - Circuit - Breaker / Hystrix
4. 보안,속도제한과 같은 서비스 접근을 어떻게 제어하는가?
    - 서비스 보안 
    - OAuth2.0
5. 다수의 서비스는 서로 어떻게 커뮤니 케이션 하는가?
    - Http/메시징
    - Feign/Spring Cloud Stream
6. 서비스간 ACID는 어떻게 달성하는가?
    - CQRS
    - Conductor/Camel/...