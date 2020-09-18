package com.morrow.modules.security.contorller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.morrow.common.exception.CdException;
import com.morrow.common.exception.ErrorCode;
import com.morrow.common.util.AssertUtils;
import com.morrow.common.util.ConvertUtils;
import com.morrow.common.util.IpUtils;
import com.morrow.common.util.Result;
import com.morrow.modules.log.entity.SysLogLogin;
import com.morrow.modules.log.enums.LoginOperationEnum;
import com.morrow.modules.log.enums.LoginStatusEnum;
import com.morrow.modules.log.service.SysLogLoginService;
import com.morrow.modules.security.dto.LoginDto;
import com.morrow.modules.security.password.PasswordUtils;
import com.morrow.modules.security.service.CaptchaService;
import com.morrow.modules.security.service.SysUserTokenService;
import com.morrow.modules.sys.controller.AbstractController;
import com.morrow.modules.sys.dto.SysUserDTO;
import com.morrow.modules.sys.entity.SysUser;
import com.morrow.modules.sys.enums.UserStatusEnum;
import com.morrow.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 登录
 *
 * @Author Tomorrow
 * @Date 2020/9/6 1:07 上午
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "登录")
public class SysLoginController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        //生成图片验证码
        BufferedImage image = captchaService.create(uuid);

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Result login(HttpServletRequest request, @RequestBody LoginDto loginDto){

        String captcha = loginDto.getCaptcha();
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        //验证码是否正确
        boolean flag = captchaService.validate(loginDto.getUuid(), loginDto.getCaptcha());
        if (!flag) {
            return Result.error("验证码不正确");
        }

        SysLogLogin sysLog = new SysLogLogin();
        sysLog.setOperation(LoginOperationEnum.LOGIN.value());
        System.out.println(LocalDateTime.now());
        sysLog.setCreateDate(LocalDateTime.now());
        sysLog.setIp(IpUtils.getIpAddr(request));
        sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        // 用户信息
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username",username));
        SysUserDTO user = ConvertUtils.sourceToTarget(sysUser, SysUserDTO.class);

        //用户不存在
        if (user == null) {
            sysLog.setStatus(LoginStatusEnum.FAIL.value());
            sysLog.setCreatorName(loginDto.getUsername());
            sysLogLoginService.save(sysLog);
            throw new CdException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }


        //密码错误
        if (!PasswordUtils.matches(loginDto.getPassword(), user.getPassword())) {
            sysLog.setStatus(LoginStatusEnum.FAIL.value());
            sysLog.setCreator(user.getId());
            sysLog.setCreatorName(user.getUsername());
            sysLogLoginService.save(sysLog);

            throw new CdException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            sysLog.setStatus(LoginStatusEnum.LOCK.value());
            sysLog.setCreator(user.getId());
            sysLog.setCreatorName(user.getUsername());
            sysLogLoginService.save(sysLog);

            throw new CdException(ErrorCode.ACCOUNT_DISABLE);
        }

        //登录成功
        sysLog.setStatus(LoginStatusEnum.SUCCESS.value());
        sysLog.setCreator(user.getId());
        sysLog.setCreatorName(user.getUsername());
        sysLogLoginService.save(sysLog);
        Result token = sysUserTokenService.createToken(user.getId());

        return token;

    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

}
