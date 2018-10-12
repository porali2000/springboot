package com.porlabs.templates.config;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@ConfigurationProperties("database")
@EnableJpaRepositories(
        basePackages = "com...data.db",
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager"
)
@Setter
public class DatabaseConfig {
    protected static final Logger LOGGER = LogManager.getLogger(DatabaseConfig.class);

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    @NotNull
    private String dialect;

    @NotNull
    private String ddlauto;
    @NotNull
    protected String driver;

    @NotNull
    private String showsql;

    @NotNull
    private String usesqlcomments;

    @NotNull
    private String formatsql;

    @Bean("dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .password(password)
                .url(url)
                .username(username)
                .build();
    }

    @Bean("entityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManager() throws SQLException {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlauto);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", this.showsql);
        properties.put("hibernate.format_sql", this.formatsql);
        properties.put("hibernate.use_sql_comments", this.usesqlcomments);
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.dataSource());
        em.setPackagesToScan(
                "com.data.db");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean("jdbcTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(this.dataSource());
    }

    @Bean("transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new JpaTransactionManager(this.entityManager().getObject());
    }


    @PostConstruct
    private void postConstruct() {
        LOGGER.info("Oracle database Configuration complete");
    }

}