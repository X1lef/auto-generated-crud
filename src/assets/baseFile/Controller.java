package py.com.brosco.notifications.controllers;

import com.roshka.michi.acl.bean.APIPermissions;
import com.roshka.michi.annotation.AclAction;
import com.roshka.michi.bean.MichiContext;
import com.roshka.michi.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import py.com.brosco.commons.beans.BrosCoContext;
import py.com.brosco.commons.utils.PermissionsUtils;
import py.com.brosco.notifications.beans.ClassModel;
import py.com.brosco.notifications.beans.ClassModelResultWithPagination;
import py.com.brosco.notifications.beans.ClassModelResult;
import py.com.brosco.notifications.config.BroscoNotificationsConfig;
import py.com.brosco.notifications.services.ClassModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/class-model")
public class ClassModelAPI {
    private static final Logger logger = LoggerFactory.getLogger(ClassModelAPI.class);

    @Autowired
    private ClassModelService classModelService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PermissionsUtils permissionsUtils;

    @Autowired
    BroscoNotificationsConfig broscoNotificationsConfig;

    private static final boolean MANDATORY_ACCESS_TOKEN = true;
    private static final String CLASS_MODEL_FIND = "class.model.find";
    private static final String CLASS_MODEL_FIND_BY_ID = "class.model.find.by.id";
    private static final String CLASS_MODEL_UPDATE = "class.model.update";
    private static final String CLASS_MODEL_CREATE = "class.model.create";
    private static final String CLASS_MODEL_DELETE = "class.model.delete";

    /*
        DTO A CREAR:
        ClassModelResultWithPagination y ClassModelResult 
    */

    @GetMapping(value = "/find", produces = APPLICATION_JSON_VALUE)
    @AclAction(actionCode = CLASS_MODEL_FIND)
    public @ResponseBody ClassModelResultWithPagination find(@RequestBody ClassModel classModel) throws APIException {
        logger.info("Starting FIND [{}] with body => [{}]", request.getRequestURL(), request.getParameterMap());

        validateAccess(request, MANDATORY_ACCESS_TOKEN);

        return classModelService.find(classModel);
    }

    @GetMapping(value = "/findById/{iid}", produces = APPLICATION_JSON_VALUE)
    @AclAction(actionCode = CLASS_MODEL_FIND_BY_ID)
    public @ResponseBody ClassModelResult findById(@PathVariable("iid") Long iid) throws APIException {
        logger.info("Starting FIND_BY_ID [{}] with params => [{}]", request.getRequestURL(), request.getParameterMap());

        validateAccess(request, MANDATORY_ACCESS_TOKEN);

        return classModelService.findById(iid);
    }

    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    @AclAction(actionCode = CLASS_MODEL_CREATE)
    public @ResponseBody ClassModelResult create(@RequestBody ClassModel classModel) throws APIException {
        logger.info("Starting CREATE [{}] with body => [{}]", request.getRequestURL(), request.getParameterMap());

        validateAccess(request, MANDATORY_ACCESS_TOKEN);

        return classModelService.create(classModelResult);
    }

    @PatchMapping(value = "/update", consumes = APPLICATION_JSON_VALUE)
    @AclAction(actionCode = CLASS_MODEL_UPDATE)
    public @ResponseBody ClassModelResult update(@RequestBody ClassModel classModel) throws APIException {
        logger.info("Starting UPDATE [{}] with body => [{}]", request.getRequestURL(), request.getParameterMap());

        validateAccess(request, MANDATORY_ACCESS_TOKEN);

        return classModelService.update(classModelRequest);
    }

    @DeleteMapping(value = "/delete/{iid}", produces = APPLICATION_JSON_VALUE)
    @AclAction(actionCode = CLASS_MODEL_DELETE)
    public @ResponseBody ClassModelResult delete(@PathVariable("iid") Long iid) throws APIException {
        logger.info("Starting DELETE [{}] with params => [{}]", request.getRequestURL(), request.getParameterMap());

        validateAccess(request, MANDATORY_ACCESS_TOKEN);

        return classModelService.delete(iid);
    }

    private void validateAccess(HttpServletRequest request, boolean mandatoryAccessToken) {
        MichiContext ctx = MichiContext.getContext(request, mandatoryAccessToken);
        APIPermissions apiPermissions = permissionsUtils.getApiPermissions(ctx);
        BrosCoContext context = new BrosCoContext(MichiContext.getContext(request), broscoNotificationsConfig.getBroscoNotificationApiCode());
        PermissionsUtils.checkApiPermissions(apiPermissions, context.getAclAPICode());
    }
}
