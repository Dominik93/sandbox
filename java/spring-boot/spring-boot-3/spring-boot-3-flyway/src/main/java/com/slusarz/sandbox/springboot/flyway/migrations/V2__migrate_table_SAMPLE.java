package com.slusarz.sandbox.springboot.flyway.migrations;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

@Slf4j
public class V2__migrate_table_SAMPLE extends BaseJavaMigration {

    private static final int VERSION = 1;

    @Override
    public void migrate(Context context) throws Exception {
        log.info("Migrate to version {}", VERSION);
    }

    @Override
    public Integer getChecksum() {
        return VERSION;
    }
}
