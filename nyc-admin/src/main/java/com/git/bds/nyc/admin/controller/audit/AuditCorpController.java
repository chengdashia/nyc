package com.git.bds.nyc.admin.controller.audit;

import com.git.bds.nyc.communal.service.audit.AuditCorpDemandService;
import com.git.bds.nyc.communal.service.audit.AuditCorpProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/1/5 14:41
 */
@Api(tags = "经销社 审核公司发布的 ")
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/audit/corp")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpController {

    private final AuditCorpProductService auditCorpProductService;

    private final AuditCorpDemandService auditCorpDemandService;
}
