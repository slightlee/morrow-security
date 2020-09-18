package com.morrow.modules.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.morrow.common.util.DateUtil;
import com.morrow.common.util.Result;
import com.morrow.modules.security.entity.SysUserToken;
import com.morrow.modules.security.mapper.SysUserTokenMapper;
import com.morrow.modules.security.oauth2.TokenGenerator;
import com.morrow.modules.security.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author morrow
 * @since 2020-09-13
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {

    @Autowired
    SysUserTokenMapper sysUserTokenMapper;

    /**
     * 8小时后过期
     */
    private final static int EXPIRE = 3600 * 8;


    @Override
    public Result createToken(Long userId) {

        //用户token
        String token;

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserToken userToken = sysUserTokenMapper.selectOne(new QueryWrapper<SysUserToken>().eq("user_id",userId));
        if(userToken == null){
            //生成一个token
            token = TokenGenerator.generateValue();

            userToken = new SysUserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateDate(DateUtil.asLocalDateTime(now));
            userToken.setExpireDate(DateUtil.asLocalDateTime(expireTime));

            //保存token
            sysUserTokenMapper.insert(userToken);
        }else{
            //判断token是否过期
            if(DateUtil.asDate(userToken.getExpireDate()).getTime() < System.currentTimeMillis()){
                //token过期，重新生成token
                token = TokenGenerator.generateValue();
            }else {
                token = userToken.getToken();
            }

            userToken.setToken(token);
            userToken.setUpdateDate(DateUtil.asLocalDateTime(now));
            userToken.setExpireDate(DateUtil.asLocalDateTime(expireTime));

            //更新token
            this.updateById(userToken);
        }

        Result result = Result.ok().put("token", token).put("expire", EXPIRE);
        return result;
    }

    @Override
    public void logout(Long userId) {

        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        sysUserTokenMapper.updateToken(userId, token);
    }
}
