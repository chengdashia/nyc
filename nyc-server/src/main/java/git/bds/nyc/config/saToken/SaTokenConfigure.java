package git.bds.nyc.config.saToken;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/3/13 21:18
 */
@Slf4j
@Configuration
public class SaTokenConfigure {

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        List<String> urls = new ArrayList<>();
        //释放swagger 映射
        urls.add("/favicon.ico");
        urls.add("/error");
        urls.add("/swagger-resources/**");
        urls.add("/webjars/**");
        urls.add("/v2/**");
        urls.add("/doc.html");
        urls.add("**/swagger-ui.html");
        urls.add("/swagger-ui.html/**");

        return new SaServletFilter()

                // 指定 拦截路由 与 放行路由
                .addInclude("/**")
                .setExcludeList(urls)
                //.addExclude("/favicon.ico","/error","/swagger-resources/**","/webjars/**","/v2/**","/doc.html","**/swagger-ui.html","/swagger-ui.html/**")

                // 认证函数: 每次请求执行
                .setAuth(obj -> {
                    log.info("---------- 进入Sa-Token全局认证 -----------");

                    // //登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/**")
                            .notMatch("/applet/user/login")
                            .notMatch("/applet/index/advertisement/**")
                            .notMatch("/applet/index/**")
                            .notMatch("/applet/index/details/**")
                            .check(r-> StpUtil.checkLogin());

                    // 更多拦截处理方式，请参考“路由拦截式鉴权”章节
                    // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
                    SaRouter.match("/applet/mine/release-manage/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/mine/address/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/mine/collection/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/mine/history/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/mine/order-handler/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/mine/demand-handler/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/order/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    SaRouter.match("/applet/index/release/**", r -> StpUtil.checkRoleOr("corp", "farmer"));
                    //SaRouter.match("/coop/**", r -> StpUtil.checkRoleOr("coop"));
                    //SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin"));

                    // 权限认证 -- 不同模块认证不同权限
                    //SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
                    //SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                    //SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
                    //SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
                    //SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
                    //SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));

                })


                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    log.info("---------- 进入Sa-Token异常处理 -----------");
                    return SaResult.error(e.getMessage());
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                })
                ;
    }

    /**Sa-Token 整合 jwt (Simple 简单模式)*/
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

}
