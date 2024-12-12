package com.educandoweb.course.util;

import static com.educandoweb.course.util.Utils.logBuilder;

public class Constants {
    public static final String LOG_KEY_METHOD = "method={} ";
    public static final String LOG_KEY_REQUEST = "request{}";
    public static final String LOG_KEY_MESSAGE = "message={}";
    public static final String LOG_BASE = "class={} method={}";
    public static final String LOG_INFO = logBuilder(LOG_BASE, "step={}");
    public static final String LOG_ERROR = logBuilder(LOG_BASE, "exception={}");
    public static final String LOG_WARN = logBuilder(LOG_BASE, "error={} exception={}");

    public static final String STEP_END = "End";
    public static final String STEP_START = "Start";

    private static final String ID = "id";

    public static final String PROFILE_TEST = "test";

    public static final String TB_USER = "tb_user";
    public static final String TB_ORDER = "tb_order";
    public static final String TB_PAYMENT = "tb_payment";
    public static final String TB_PRODUCT = "tb_product";
    public static final String TB_CATEGORY = "tb_category";

    public static final String ENTITY_FOUND = "entity.found";
    public static final String ENTITY_EXISTS = "entity.exists";
    public static final String ENTITY_SAVING = "entity.saving";
    public static final String ENTITY_FINDING = "entity.finding";
    public static final String ENTITY_DELETING = "entity.deleting";
    public static final String ENTITY_NOT_FOUND = "entity.not_found";
    public static final String ENTITY_FINDING_ALL = "entity.finding_all";
    public static final String ENTITY_NOT_FOUND_BY_ID = "entity.not_found_by_id";
    public static final String ENTITY_CREATED_SUCCESS = "entity.created.success";
    public static final String ENTITY_UPDATED_SUCCESS = "entity.updated.success";
    public static final String ENTITY_DELETED_SUCCESS = "entity.deleted.success";

    public static final String OPERATION_END = "operation.end";
    public static final String OPERATION_START = "operation.start";
    public static final String OPERATION_SAVE_FAIL = "operation.save.fail";
    public static final String OPERATION_UPDATE_FAIL = "operation.update.fail";
    public static final String OPERATION_SEARCH_FAIL = "operation.search.fail";
    public static final String OPERATION_SAVE_COMPLETE = "operation.save.complete";
    public static final String OPERATION_SEARCH_COMPLETE = "operation.search.complete";
    public static final String OPERATION_UPDATE_COMPLETE = "operation.update.complete";
}
