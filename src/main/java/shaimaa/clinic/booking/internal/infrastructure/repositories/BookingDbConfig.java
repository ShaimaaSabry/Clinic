package shaimaa.clinic.booking.internal.infrastructure.repositories;

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
        basePackages = "shaimaa.clinic.booking",
        entityManagerFactoryRef = "bookingEntityManagerFactory",
        transactionManagerRef = "bookingTransactionManager"
)
class BookingDbConfig {
    @Bean(name = "bookingDataSource")
    @ConfigurationProperties("spring.datasource.booking")
    DataSource bookingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bookingEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bookingDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("shaimaa.clinic.booking")
                .persistenceUnit("booking")
                .build();
    }

    @Bean
    public PlatformTransactionManager bookingTransactionManager(
            @Qualifier("bookingEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
            (initMethod = "migrate")
    public Flyway bookingFlyway(@Qualifier("bookingDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/booking")
                .load();
//        flyway.migrate();
        return flyway;
    }
}
