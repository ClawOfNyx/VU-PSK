package org.bookclub.config;

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

            // Add XML mappers
            configuration.addMappers("org.bookclub.mybatis");

            logger.info("MyBatis SqlSessionFactory created successfully.");
            return new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating MyBatis SqlSessionFactory", e);
            throw new RuntimeException("Error creating MyBatis SqlSessionFactory", e);
        }
    }
}