package org.bookclub.config;

import org.bookclub.mybatis.BookClubMapper;
import org.bookclub.mybatis.BookMapper;
import org.bookclub.mybatis.ReaderMapper;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.cdi.SessionFactoryProvider;

//import jakarta.annotation.Resource;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.inject.Produces;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class MyBatisProducer {
    private static final Logger logger = Logger.getLogger(MyBatisProducer.class.getName());

    @Resource(lookup = "java:jboss/datasources/BookClubDS")
    private DataSource dataSource;

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    public SqlSessionFactory produceFactory() {
        try {
            TransactionFactory transactionFactory = new ManagedTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);

            Configuration configuration = new Configuration(environment);

            configuration.getTypeAliasRegistry().registerAlias("Book", org.bookclub.entity.Book.class);
            configuration.getTypeAliasRegistry().registerAlias("BookClub", org.bookclub.entity.BookClub.class);
            configuration.getTypeAliasRegistry().registerAlias("Reader", org.bookclub.entity.Reader.class);

//            configuration.addMappers("org.bookclub.mybatis");
            configuration.addMapper(ReaderMapper.class);
            configuration.addMapper(BookClubMapper.class);
            configuration.addMapper(BookMapper.class);


            logger.info("MyBatis SqlSessionFactory created successfully.");

            return new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            // Handle any exceptions that might occur during the factory creation
            logger.log(Level.SEVERE, "Error creating MyBatis SqlSessionFactory", e);
            throw new RuntimeException("Error creating MyBatis SqlSessionFactory", e);
        }
    }

//    @Produces
//    @ApplicationScoped
//    public ReaderMapper produceReaderMapper(SqlSessionFactory sessionFactory) {
//        // Create a session from the SqlSessionFactory
//        return sessionFactory.openSession().getMapper(ReaderMapper.class);
//    }
//
//    @Produces
//    @ApplicationScoped
//    public BookMapper produceBookMapper(SqlSessionFactory sessionFactory) {
//        // Create a session from the SqlSessionFactory
//        return sessionFactory.openSession().getMapper(BookMapper.class);
//    }
//
//    @Produces
//    @ApplicationScoped
//    public BookClubMapper produceBookClubMapper(SqlSessionFactory sessionFactory) {
//        // Create a session from the SqlSessionFactory
//        return sessionFactory.openSession().getMapper(BookClubMapper.class);
//    }
}
