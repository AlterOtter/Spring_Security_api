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

7. API GateWay 
    - Zuul 사용


#Spring Cloud Config
    1. 각각의 MicroService들이 가지는 Config를 file 형태나 Git 형태로 가지고 있다.
        - 각 MicroService 마다 AppName-properties or yaml 파일을 가지고 있다.
        
==================================================


#Spring Cloud Config 이해 [1/2]

1. Cloud-native 어플리케이션의 중요한 이슈 중 하나는 분산된 서비스에 대한 설정과 관리입니다.
2. 개발자는 배포된 서비스와 서비스 환경 개수 만큼 설정을 변경하는데 많은 시간을 소비해야합니다.
3. 서비스가 수평적으로 확장되는 Auto Scaling이 될 때마다 서비스의 설정을 직접 변경해 줘야 합니다.
4. Spring Cloud Config는 분산되어 있는 여러 서비스의 설정을 관리할 수 있는 서버와 클라이언트를 제공합니다.


    MircoService -----------|
                            |
                            |                                               |---> git
    MircoService -----------|-------------> Spring Cloud Config -------->   | 
                            |                                               |---> file
                            |           <Spring Cloud Server가 존재>
    MircoService -----------|  <각 MicroService가 가지는 Config 들을 가지고 있다.>



#Spring Cloud Config 이해 [2/2]

1. 마이크로서비스가 시작할 때 Spring Cloud Config에서 관리하는 설정 정보를 요청합니다.
2. 마이크로서비스는 다양한 환경에서 실행될 수 있으므로 Spring Cloud Config 서버는 환경별로 설정을 분리할 수 있습니다.
3. 환경에 따른 설정 정보는 profile과 label로 구분할 수 있습니다.

        MircoService A1-----------|
                                |
                                |                                               |---> git
        MircoService A2-----------O -------------> Spring Cloud Config -------->  |
                                |                                               |---> file
                                |           
        MircoService A3-----------|        


4. http://localhost:9900/configtest/dev 실행시 
   {
	"name": "configtest",  <---프로젝트 이름
	"profiles": [          
		"dev"              <----DEV에 대한 프로파일을 가져온다
	],
	"label": null,         
	"version": "c72aa00e96e1db2b0614a5ada0e178f732616563",
	"state": null,
	"propertySources": [        <---- 소스 출처
		{       
			"name": "https://gitlab.com/kkjin/namoosori-config.git/file:C:\\Users\\User\\AppData\\Local\\Temp\\config-repo-18110895700045894093\\configtest-dev.yml",
			"source": {
				"namoosori.value": "dev-test-valeu second change~~"
			}
		}
	]
}


=============================================================
Spring Discovery[1/2]
1. 분산 시스템은 서로 다른 서버에서 호스팅 되는 여러 서비스로 구축되어 서로 커뮤니케이션 합니다.
2. 각 서비스는 호출 대상 서비스의 위치를 알고 있어야 하는데 Auto Scaling 과 함께 서비스의 위치도 갱신되어야 합니다.
3. Spring Cloud Config로 서비스의 위치 정보를 매번 변경해야 한다면 Auto Scaling은 더 이상 장점이 될 수 없습니다.
4. Neflix OSS에서 제공하는 다양한 컴포넌트 중 Eureka는 서비스의 위치를 관리해 줍니다.

        User-front---------->                                     auction-user
                    서비스를 사용하기 위해서 정상 서비스 중인       
                    인스턴스에 대한 서비스 탐색이 필요합니다.       
                                                                  auction-user
                                        
                    어느 인스턴스를 사용할 것인가?                  auction-user
        user-shareing------->
                    Auto scaling으로 인스턴스가
                    증가하면 어떤 서비스를 사용할 것인가?                    


Spring Discovery[2/2]
1. 하나의 서비스가 여러 개의 인스턴스로 서비스한다면 인스턴스의 위치 (IP,PORT)를 알고 있어야 합니다.
2. Auto Scaling되는 PaaS 환경 에서는 정해진 인스턴스의 위치가 없으므로 호출할 인스턴스도 결정해야 합니다.
3. Netfix OSS와 Service Discovery는 이런 문제를 해결하는 컴포넌트 입니다.
4. Eureka는 마이크로서비스를 이름과 인스턴스의 위치(IP,Port)의 쌍으로 관리합니다.


                                            (Eureka Server)
                                ----------------|Discovery|<---------------------
                                |                                               |
                   2.register   |  4.find                                       | 1.register
                                |                                               |
                                |               5.request(REST)                 |
        request (Rest) ===> Server <---------------------------------------> Client
                                                6.response(JSON)                        