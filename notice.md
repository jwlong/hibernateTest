 这个到getCurrentSession息息相关-
    ``<property name="current_session_context_class">thread</property>``
 这个是jdbc事务
      
      在集成Hibernate的环境下（例如Jboss），要在hibernate.cfg.xml中session-factory段加入：
      Xml代码  收藏代码
    <property name="current_session_context_class">jta</property>  
    哈哈，我的JBOSS学的终于有知道的应用的场景了
    
   
        SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx  =  session1.beginTransaction();
        News news = new News();
        news.setContent("this is session1 about content");
        news.setFullContent("this is full  content aout the world the message");
        news.setTitle("Session first cache!!");
        session1.save(news);
        session1.save(news); //这里就不发sql ,hibernate 从session 中取回这里news 这个new 此时就是持久化状态 
        tx.commit();
 若在这里Session 关闭
  `session1.load(News.class,1);// 在配置文件那里没有配置是否支撑延迟加载,默认好像是load=false
  session1.get(News.class,2); //这个get就直接去数据库里把数据拿回来了。`
  来看这个
  `session1.load(News.class,100);`
  在数据库里是没有标识ID为100的News表数据的
  而此时不会报错。这个就是利用了延迟加载的好处了。
  但是
  这样会报错
  `News news = session1.load(News.class,100);
  `
  <h5>默认情况下二级缓存只会对load get 之类的方法缓存， 想list iterator 之类的方法也使用缓存 必须跟查询缓存一起使用， 重写查询方法<br> 
 `criteria .setCacheable(true)`
 `criteria.setCacheable(true).list();`
 