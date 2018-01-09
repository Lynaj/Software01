package administrator;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://miisu.database.windows.net:1433;database=HIS");
        dataSource.setUsername("ServerAdmin");
        dataSource.setPassword("Ristolainen555");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }

    @Bean
    public DatabaseService databaseDAO(){
        DatabaseServiceImpl empDao = new DatabaseServiceImpl();
        empDao.setJdbcTemplate(jdbcTemplate());
        return empDao;
    }
}
