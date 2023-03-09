package com.androsov.minecraftreactorcontrol.components;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
//public class FuelSchemeTrigger implements InitializingBean {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public FuelSchemeTrigger(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        jdbcTemplate.execute("CREATE OR REPLACE FUNCTION update_reactor_current_out() RETURNS TRIGGER AS $$ " +
//                "BEGIN " +
//                "IF NOT currently_working IN (SELECT currently_working FROM reactor_state JOIN fuels_scheme fs on reactor_state.id = NEW.reactor_state_id) THEN " +
//                "NEW.current_out := 0; " +
//                "ELSE " +
//                "SELECT energy_tick * COUNT(*) INTO NEW.current_out FROM fuels_scheme WHERE reactor_state_id = NEW.id; " +
//                "END IF; " +
//                "RETURN NEW; " +
//                "END; " +
//                "$$ LANGUAGE plpgsql;");
//
//        jdbcTemplate.execute("CREATE TRIGGER update_reactor_current_out_trigger " +
//                "AFTER INSERT ON fuels_scheme " +
//                "FOR EACH ROW " +
//                "EXECUTE FUNCTION update_reactor_current_out();");
//    }
//}
