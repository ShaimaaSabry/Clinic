package shaimaa.clinic.appointments.internal.shell.repositories;

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
        basePackages = "shaimaa.clinic.appointments",
        entityManagerFactoryRef = "appointmentsEntityManagerFactory",
        transactionManagerRef = "appointmentsTransactionManager"
)
class AppointmentsDbConfig {
//    @Primary
    @Bean(name = "appointmentsDataSource")
    @ConfigurationProperties("spring.datasource.appointments")
    DataSource appointmentsDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean appointmentsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("appointmentsDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("shaimaa.clinic.appointments")
                .persistenceUnit("appointments")
                .build();
    }

//    @Primary
    @Bean
    public PlatformTransactionManager appointmentsTransactionManager(
            @Qualifier("appointmentsEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
            (initMethod = "migrate")
    public Flyway appointmentsFlyway(@Qualifier("appointmentsDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration/appointments")
                .load();
//        flyway.migrate();
        return flyway;
    }
}
