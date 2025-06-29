package shaimaa.clinic.slots.internal.repositories;

import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "shaimaa.clinic.slots",
        entityManagerFactoryRef = "slotsEntityManagerFactory",
        transactionManagerRef = "slotsTransactionManager"
)
class SlotsDbConfig {
    @Bean(name = "slotsDataSource")
    @ConfigurationProperties("spring.datasource.slots")
    DataSource slotsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean slotsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("slotsDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("shaimaa.clinic.slots")
                .persistenceUnit("slots")
                .build();
    }

    @Bean
    public PlatformTransactionManager slotsTransactionManager(
            @Qualifier("slotsEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
            (initMethod = "migrate")
    public Flyway slotsFlyway(@Qualifier("slotsDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/slots")
                .load();
//        flyway.migrate();
        return flyway;
    }
}
