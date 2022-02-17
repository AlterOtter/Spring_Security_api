#JPA

FetchType  

지연 로딩 Lazy -  자식 객체에 대한 쿼리를 가지고 있다가 자식 객체를 가져올떄(GET 메서드) 가져오는 쿼리 작동 
              -  쿼리문을 많이 날리기 떄문에 네트워크적으로 손해
              -  Join 을 사용하여 조회하면 Proxy 객체가 조회된다 ( 자식 객체 )

즉시 로딩 Eager - 한번에 다 가져온다  Lazy와 달리 Proxy 객체가 아니라 실제 객체를 가져온다.
                - N+1 문제를 일으킬 수 있다. -> JPQL을 이용하자
